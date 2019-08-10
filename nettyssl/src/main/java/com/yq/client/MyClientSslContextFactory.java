package com.yq.client;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Simple to Introduction
 * className: MyClientSslContextFactory
 *
 * @author EricYang
 * @version 2019/8/10 15:03
 */
@Slf4j
public class MyClientSslContextFactory {
    private static final String PROTOCOL = "TLS";

    private static SSLContext sslContext;

    public static SSLContext getClientContext(String caPath, String storepass){
        if(sslContext !=null) return sslContext;
        InputStream input = null;

        try{
            //信任库
            TrustManagerFactory tf = null;
            if (caPath != null) {
                //密钥库KeyStore
                KeyStore ks = KeyStore.getInstance("JKS");
                //加载客户端证书
                input = new FileInputStream(caPath);
                ks.load(input, storepass.toCharArray());
                tf = TrustManagerFactory.getInstance("SunX509");
                // 初始化信任库
                tf.init(ks);
            }

            sslContext = SSLContext.getInstance(PROTOCOL);
            //设置信任证书
            sslContext.init(null,tf == null ? null : tf.getTrustManagers(), null);

        }catch(Exception e){
            throw new Error("Failed to init the client-side SSLContext");
        }finally{
            if(input !=null){
                try {
                    input.close();
                } catch (IOException e) {
                    log.info("close InputStream.", e);
                }
            }
        }

        return sslContext;
    }
}
