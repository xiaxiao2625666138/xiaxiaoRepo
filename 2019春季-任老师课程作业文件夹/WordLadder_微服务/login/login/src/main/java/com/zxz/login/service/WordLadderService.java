package com.zxz.login.service;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import java.io.IOException;


@Service
public class WordLadderService {

    public String getPath(String str1, String str2) {
        //1.获得一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.生成一个get请求
        String url = "http://wordladder:8089/getladder?word1=" + str1 + "&word2=" + str2;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            //3.执行get请求并返回结果
            response = httpClient.execute(httpGet);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            //4.处理结果，这里将结果返回为字符串
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
