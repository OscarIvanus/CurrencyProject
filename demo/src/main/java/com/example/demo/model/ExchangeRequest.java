package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class ExchangeRequest {
    private CurrencyPair currencyPair;
    private double amount;
}
