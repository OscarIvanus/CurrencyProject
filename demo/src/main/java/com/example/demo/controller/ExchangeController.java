package com.example.demo.controller;

import com.example.demo.model.CurrencyPair;
import com.example.demo.model.ExchangeRequest;
import com.example.demo.service.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@RestController

public class  ExchangeController {
    private ExchangeService exchangeService;
    @GetMapping("exchange")
    public double exchange(@RequestParam String fromCode,@RequestParam String toCode,@RequestParam double amount){
        try {
            return  exchangeService.getExchange(new ExchangeRequest(new CurrencyPair(fromCode,toCode),amount));
        } catch (IOException e) {
          return 0 ;
        }

    }
}
