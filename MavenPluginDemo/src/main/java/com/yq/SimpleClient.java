package com.yq;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.RequestEntity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yangqian on 2018/9/26.
 */
public class SimpleClient {
    final static String url= "http://www.baidu.com";
    public static void main(String[] args) throws IOException
    {

        CloseableHttpClient client = HttpClients.createDefault();
        //2.使用get方法
        HttpGet httpGet = new HttpGet(url);
        InputStream inputStream = null;
        CloseableHttpResponse response = null;

        try {

            httpGet.addHeader("aaa", "bbb");
            response = client.execute(httpGet);

            System.out.println(response.getStatusLine().getStatusCode());

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity, "utf-8"));
            }
            EntityUtils.consume(entity);


        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

