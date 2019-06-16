# 本文所展示的代码是在官方基础上进行修改的，源代码主要是官方的，版权归官方


## 直接编译生成的jar无法运行
C:\E\IDEAWorkspace\springboot\rpcdemo\target>java -cp .;helloWorld-1.22.0-SNAPSHOT.jar  com.yq.helloworld.HelloWorldServer
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.NoClassDefFoundError: io/grpc/BindableService
        at java.lang.Class.getDeclaredMethods0(Native Method)
        at java.lang.Class.privateGetDeclaredMethods(Class.java:2701)
        at java.lang.Class.privateGetMethodRecursive(Class.java:3048)
        at java.lang.Class.getMethod0(Class.java:3018)
        at java.lang.Class.getMethod(Class.java:1784)
        at sun.launcher.LauncherHelper.validateMainClass(LauncherHelper.java:544)
        at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:526)
Caused by: java.lang.ClassNotFoundException: io.grpc.BindableService
        at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:357)

    com.yq.helloworld.HelloWorldServer
         com.yq.helloworld.HelloWorldClient

## 可以直接运行的jar
       java -jar  helloWorld-1.22.0-SNAPSHOT-jar-with-dependencies.jar
       java -cp .;helloWorld-1.22.0-SNAPSHOT-jar-with-dependencies.jar  com.yq.helloworld.HelloWorldClient