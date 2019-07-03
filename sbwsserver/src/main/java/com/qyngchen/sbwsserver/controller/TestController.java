package com.qyngchen.sbwsserver.controller;

import com.qyngchen.sbwsserver.controller.vo.Message;
import com.qyngchen.sbwsserver.wbserver.MyWebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/test")
@RestController
@Api(description = "ceshi")
@Slf4j
public class TestController {

    @ApiOperation("sssss")
    @PostMapping("/send")
    public void sendMessage(@RequestBody Message message) {
        log.info("message {}", message);
        try {
            MyWebSocketServer.sendInfo(message.getMessage(), message.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
