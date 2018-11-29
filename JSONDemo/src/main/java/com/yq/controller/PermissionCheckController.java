

package com.yq.controller;

import com.yq.config.PermissionCheck;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/check")
@Slf4j
public class PermissionCheckController {

    @ApiOperation(value = "查询所有的controller类", notes="测试")
    @GetMapping(value = "/controllers", produces = "application/json;charset=UTF-8")
    public List<Class<?>> getController() {
        List<Class<?>> clsList = getClassesWithAnnotationFromPackage("com.yq.controller", RestController.class);

        return clsList;
    }

    @ApiOperation(value = "按controller查询所有拥有@PermissionCheck注解的方法", notes="测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "controller", defaultValue = "com.yq.controller.helloworld.HelloWorldRestController", value = "controller", required = true, dataType = "string", paramType = "path"),
    })
    @GetMapping(value = "/controllers/{controller}/method", produces = "application/json;charset=UTF-8")
    public Map<String, String> getMethod(@PathVariable String controller) {
        Map<String, String> checkIdMethodMap = new HashMap<String, String>();
        geMethodWithAnnotationFromFilePath(controller, checkIdMethodMap, PermissionCheck.class);
        return checkIdMethodMap;
    }

    private List<Class<?>> getClassesWithAnnotationFromPackage(String packageName, Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs = null;

        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        }
        catch (IOException e) {
            log.error("Failed to get resource", e);
        }

        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();//file:/D:/E/workspaceGitub/springboot/JSONDemo/target/classes/com/yq/controller
            String protocol = url.getProtocol();//file

            if ("file".equals(protocol)) {
                String filePath = null;
                try {
                    filePath = URLDecoder.decode(url.getFile(), "UTF-8");///D:/E/workspaceGitub/springboot/JSONDemo/target/classes/com/yq/controller
                }
                catch (UnsupportedEncodingException e) {
                    log.error("Failed to decode class file", e);
                }

                filePath = filePath.substring(1);
                getClassesWithAnnotationFromFilePath(packageName, filePath, classList, annotation);
            }
        }


        return classList;
    }
    //filePath is like this 'D:/E/workspaceGitub/springboot/JSONDemo/target/classes/com/yq/controller'
    private void getClassesWithAnnotationFromFilePath(String packageName, String filePath, List<Class<?>> classList,
                                           Class<? extends Annotation> annotation) {
        Path dir = Paths.get(filePath);//D:\E\workspaceGitub\springboot\JSONDemo\target\classes\com\yq\controller

        DirectoryStream<Path> stream = null;
        try{
            stream = Files.newDirectoryStream(dir);
            for(Path path : stream) {
                String fileName = String.valueOf(path.getFileName()); // for current dir , it is 'helloworld'
                //如果path是目录的话， 此处需要递归，
                boolean isDir = Files.isDirectory(path);
                if(isDir) {
                    getClassesWithAnnotationFromFilePath(packageName + "." + fileName , path.toString(), classList, annotation);
                }
                else  {
                    String className = fileName.substring(0, fileName.length() - 6);

                    Class<?> classes = null;
                    try {
                        String fullClassPath = packageName + "." + className;
                        log.info("fullClassPath={}", fullClassPath);
                        classes = Thread.currentThread().getContextClassLoader().loadClass(fullClassPath);
                    }
                    catch (ClassNotFoundException e) {
                        log.error("Failed to find class", e);
                    }

                    if (null != classes && null != classes.getAnnotation(annotation)) {
                        classList.add(classes);
                    }
                }
            }
        }
        catch (IOException e) {
            log.error("Failed to read class file", e);
        }
    }


    private void geMethodWithAnnotationFromFilePath(String fullClassPath, Map<String, String> checkIdMethodMap,
                                                             Class<? extends Annotation> methodAnnotation) {
        Class<?> classes = null;
        try {
            log.info("fullClassPath={}", fullClassPath);
            classes = Thread.currentThread().getContextClassLoader().loadClass(fullClassPath);

            Method[] methods = classes.getDeclaredMethods();

            for (Method method : methods) {
                PermissionCheck myAnnotation = method.getAnnotation(PermissionCheck.class);
                if (null != myAnnotation) {
                    checkIdMethodMap.put (myAnnotation.id(), method.getName() );
                }

//                RequestMapping myAnnotation = method.getAnnotation(RequestMapping.class);
//                if (null != myAnnotation) {
//                    checkIdMethodMap.put (myAnnotation.path().toString(), method.getName() );
//                }
            }
        }
        catch (ClassNotFoundException e) {
            log.error("Failed to find class={}", fullClassPath, e);
        }
    }
}