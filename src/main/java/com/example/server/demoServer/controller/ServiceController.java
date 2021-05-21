package com.example.server.demoServer.controller;

import com.example.server.demoServer.beans.Bean;
import com.example.server.demoServer.beans.ResponseBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class ServiceController {
    //logger

    @PostMapping("/api/image")
    public ResponseEntity getResponse(@RequestBody Bean bean1) throws JsonProcessingException {
        byte[] decodedBytes = Base64.getDecoder().decode(bean1.getImage());
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);

        ObjectMapper objectMapper = new ObjectMapper();
        Bean bean = objectMapper.readValue(decodedString, Bean.class);


        ResponseBean responseBean = new ResponseBean();
        responseBean.setEncodedString(bean1.getImage());
        responseBean.setOriginalString(bean.getImage());

        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED);

    }
}
