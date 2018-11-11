package com.yq.service.impl;

import com.yq.service.IEnDecryptionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Simple to Introduction
 * className: EnDecryptionServiceImpl
 *
 * @author EricYang
 * @version 2018/11/10 23:16
 */
@Service
@Slf4j
public class EnDecryptionServiceImpl implements IEnDecryptionService{
    //@Value("${mySecretKey}")
    //Autowired annotation is not supported on static fields
    // 因此通过SetMySecretKey完成secretKey的设置，如果不读取配置文件就不需要这样做。 必须使用静态变量因为我们在初始化中用到了改变了
    private static String mySecretKey;

    private static String salt = "xiantaibeiwuhan";
    private static IvParameterSpec ivspec;

    private static  Cipher cipher;
    private static KeyGenerator keygenerator;
    private static SecretKeySpec secretKeySpec;
    static {
        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ivspec = new IvParameterSpec(iv);
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }
        catch (NoSuchPaddingException ex) {
            log.error("no PKCS5Padding in the jvm", ex);
        }
        catch (NoSuchAlgorithmException ex) {
            log.error("no AES algorithm in the jvm", ex);
        }
        catch (Exception ex) {
            log.error("generic exception", ex);
        }
    }

    @Autowired
    public void SetMySecretKey(String secretKey) {
        mySecretKey = secretKey;
        log.info("set mySecretKey={}", mySecretKey);
        init();
    }

    private void init() {
        log.info("init mySecretKey={}", mySecretKey);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(mySecretKey.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
        }
        catch (Exception ex) {
            log.error("generic exception", ex);
        }

        log.info("initialization completes.");
    }
    /*
    加密失败返回明文原文
     */
    @Override
    public String encrypt(String plaintext) {
        byte[] text = plaintext.getBytes(StandardCharsets.UTF_8);
        String retStr = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);

            byte[] textEncrypted = cipher.doFinal(text);

            retStr = Base64.getEncoder().encodeToString(textEncrypted);;
        }
        catch (Exception ex) {
            log.warn("encrypt exception", ex);
        }

        return retStr;
    }

    /*
    解密失败返回密文原文
     */
    @Override
    public String decrypt(String ciphertext) {
        String retStr = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);
            byte[] text = Base64.getDecoder().decode(ciphertext);
            byte[] textDecrypted = cipher.doFinal(text);
            retStr = new String(textDecrypted, StandardCharsets.UTF_8);
        }
        catch (Exception ex) {
            log.warn("encrypt exception", ex);
        }

        return retStr;
    }
}
