package ru.itis.teamwork.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.teamwork.models.WebSocketMessage;
import ru.itis.teamwork.models.WebSocketOutputMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public WebSocketOutputMessage send(WebSocketMessage message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return WebSocketOutputMessage.builder()
                .dateTime(time)
                .text(message.getText())
                .userName(message.getUserName())
                .build();
    }

    @GetMapping("/chat")
    public String get(){
        return "chat";
    }
}
