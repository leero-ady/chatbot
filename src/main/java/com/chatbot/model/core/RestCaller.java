package com.chatbot.model.core;

import org.springframework.web.client.RestTemplate;

/**
 * Created by welcome on 12/06/2016.
 */
public class RestCaller extends RestTemplate{
    static String url="http://localhost:8080/createSession/User1";
    public  String call(){
        return this.getForObject(url, String.class);
    }
}
