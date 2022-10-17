package com.example.demo.service;

import com.example.demo.model.ExchangeRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
@Service
public class ExchangeService {
    public double getExchange(ExchangeRequest exchangeRequest) throws IOException {

            DecimalFormat f = new DecimalFormat("00.00");
            String GET_URL = "https://v6.exchangerate-api.com/v6/ff72628f33f12861e528b9f0/pair/" + exchangeRequest.getCurrencyPair().getFromCode() + "/" + exchangeRequest.getCurrencyPair().getToCode();
            URL url = new URL(GET_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject obj = new JSONObject(response.toString());
                Double rate = obj.getDouble("conversion_rate");
                return Double.parseDouble(f.format(rate*exchangeRequest.getAmount()));
            } else {
                return 0;
            }

        }
    }
