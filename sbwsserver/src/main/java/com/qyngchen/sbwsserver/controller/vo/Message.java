package com.qyngchen.sbwsserver.controller.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Message {

    private String id;

    private String message;

}
