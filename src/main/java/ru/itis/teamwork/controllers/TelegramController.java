package ru.itis.teamwork.controllers;

import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TelegramController {
    private String number = null;

    @GetMapping("/telegram/connect")
    public String getConnect() {
        return "telegram/formTel";
    }

    @SneakyThrows
    @PostMapping("/telegram/connect")
    public String connect(@RequestParam("phoneNumber") String phoneNumber) {
        URIBuilder uriBuilder = new URIBuilder("http://35.204.168.100");
        uriBuilder.setPort(8000);
        uriBuilder.setPath("/connect");
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader("Content-Type", "application/json");

        this.number = phoneNumber;

        StringEntity stringEntity = new StringEntity("{\"phone\":\"" + phoneNumber + "\"}");
        httpPost.setEntity(stringEntity);
        HttpClient httpClient = HttpClients.createDefault();
        httpClient.execute(httpPost);
        return "telegram/formCode";
    }

    @GetMapping("/telegram/code")
    public String getCode() {
        return "telegram/formCode";
    }

    @SneakyThrows
    @PostMapping("/telegram/code")
    public String code(@RequestParam("code") String code) {
        URIBuilder uriBuilder = new URIBuilder("http://35.204.168.100");
        uriBuilder.setPort(8000);
        uriBuilder.setPath("/sign");
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader("Content-Type", "application/json");

        StringEntity stringEntity = new StringEntity("{\"phone\":\"" +
                this.number + "\",\"code\":\"" +
                code + "\"}");
        httpPost.setEntity(stringEntity);
        HttpClient httpClient = HttpClients.createDefault();
        httpClient.execute(httpPost);
        return "telegram/formSendMessage";
    }

    @GetMapping("/telegram/sendMessage")
    public String getSendMessagePage() {
        return "telegram/formSendMessage";
    }

    @SneakyThrows
    @PostMapping("/telegram/sendMessage")
    public String sendMessage(@RequestParam("toPhone") String toPhone,
                              @RequestParam("textMessage") String textMessage,
                              Model model) {
        URIBuilder uriBuilder = new URIBuilder("http://35.204.168.100");
        uriBuilder.setPort(8000);
        uriBuilder.setPath("/send_message");
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader("Content-Type", "application/json");

        StringEntity stringEntity = new StringEntity("{\"phone\":\"" +
                this.number + "\",\"text\":\"" +
                textMessage + "\",\"to\":\"" +
                toPhone + "\"}");
        httpPost.setEntity(stringEntity);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        model.addAttribute("statusCode", statusCode);
        return "telegram/formSendMessage";
    }
}