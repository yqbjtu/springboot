package com.yq.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yq.demo.other.Quote;

@RestController
public class AccessExternalRestController {
    private static final Logger log = LoggerFactory.getLogger(AccessExternalRestController.class);

    @RequestMapping("/qutoe")
    public Quote quote() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
        return quote;
    }
}