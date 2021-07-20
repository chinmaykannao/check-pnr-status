package com.pnr.checker.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pnr.checker.model.PNRStatus;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PNRRequestExecutor {

    private static PNRRequestExecutor requestExecutor = new PNRRequestExecutor();
    private OkHttpClient client;

    private PNRRequestExecutor() {
        client = new OkHttpClient();
    }

    public static PNRRequestExecutor getExecutor() {
        return requestExecutor;
    }

    public Request createRequestForPNR(String pnr) {
        if (StringUtils.isEmpty(pnr)) throw new IllegalArgumentException("PNR status can not be null or empty");
        return new Request.Builder()
                .url("https://pnr-status-indian-railway.p.rapidapi.com/rail/" + pnr)
                .get()
                .addHeader("x-rapidapi-host", "pnr-status-indian-railway.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "0205477d56msh684b781512ac06bp1288d9jsndb4292a5adf2")
                .build();
    }

    public PNRStatus executeRequest(Request request) {
        PNRStatus pnrStatus = null;
        try {
            client.setConnectTimeout(10, TimeUnit.SECONDS);
            client.setReadTimeout(10, TimeUnit.SECONDS);
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                pnrStatus = objectMapper.readValue(response.body().bytes(), PNRStatus.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pnrStatus;
    }
}