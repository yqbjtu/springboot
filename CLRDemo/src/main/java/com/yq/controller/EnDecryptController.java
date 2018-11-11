

package com.yq.controller;

import com.alibaba.fastjson.JSONObject;
import com.yq.domain.User;
import com.yq.service.IEnDecryptionService;
import com.yq.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;


@RestController
@RequestMapping("/crypt")
@Slf4j
public class EnDecryptController {

    @Autowired
    @Qualifier("enDecryptionServiceImpl")
    IEnDecryptionService enDecryptionSvc;

    @Autowired
    @Qualifier("twoEnDecryptionServiceImpl")
    IEnDecryptionService twoEnDecryptionSvc;

    /*
    get请求不能使用body，因此必须修改为其他method
     */
    @ApiOperation(value = "加密")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plaintext", defaultValue = "qazwsxedc", value = "plaintext", required = true, dataType = "string", paramType = "body"),
    })
    @PostMapping(value = "/encrypt/", produces = "application/json;charset=UTF-8")
    public String encryptRest(@RequestBody String plaintext) {
        String ciphertext = enDecryptionSvc.encrypt(plaintext);
        log.info("plaintext={}, ciphertext={}", plaintext, ciphertext);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("ciphertext", ciphertext);
        return jsonObj.toString();
    }

    /*
     get请求不能使用body，因此必须修改为其他method
     */
    @ApiOperation(value = "加密byTwo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plaintext", defaultValue = "qazwsxedc", value = "plaintext", required = true, dataType = "string", paramType = "body"),
    })
    @PostMapping(value = "/encryptByTwo/", produces = "application/json;charset=UTF-8")
    public String encryptRestByTwo(@RequestBody String plaintext) {
        String ciphertext = twoEnDecryptionSvc.encrypt(plaintext);
        log.info("plaintext={}, ciphertext={}", plaintext, ciphertext);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("ciphertext", ciphertext);
        return jsonObj.toString();
    }

    @ApiOperation(value = "解密")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ciphertext", defaultValue = "qazwsxedc", value = "ciphertext", required = true, dataType = "string", paramType = "body"),
    })
    @PostMapping(value = "/decrypt", produces = "application/json;charset=UTF-8")
    public String decryptRest(@RequestBody String ciphertext) {
        String plaintext =  enDecryptionSvc.decrypt(ciphertext);
        log.info("ciphertext={}, plaintext={}",ciphertext, plaintext);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("plaintext", plaintext);
        return jsonObj.toString();
    }
}