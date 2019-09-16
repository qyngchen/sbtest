package com.qyngchen.sbwsclient001.wbclient;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author chenqingyang
 * @Auther: liaoshiyao
 * @Date: 2019/1/11 17:38
 * @Description: 配置websocket后台客户端
 */
@Slf4j
@Order(2)
@Component
public class MyWebSocketClient {

    @Value("${senselink.app_key}")
    private String appKey;

    @Value("${senselink.app_secret}")
    private String appSecret;

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            Long l = System.currentTimeMillis();
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://192.168.1.109:9090/"+appKey+"/"+l+"/"+this.getSign(l)), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("[websocket] 连接成功");
                }

                @Override
                public void onMessage(String message) {
                    log.info("[websocket] 收到消息={}", message);

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("[websocket] 退出连接");
                }

                @Override
                public void onError(Exception ex) {
                    log.info("[websocket] 连接错误={}", ex.getMessage());
                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 创建基础map
     *
     * @param map
     * @return
     */
    private Map<String, Object> buildBaseMap(Map<String, Object> map) {
        long timestamp = System.currentTimeMillis();
        map.put("app_key", appKey);
        map.put("sign", this.getSign(timestamp));
        map.put("timestamp", String.valueOf(timestamp));
        return map;
    }

    /**
     * 获取签名
     *
     * @param timestamp 当前时间戳
     * @return
     */
    private String getSign(Long timestamp) {
        String code = timestamp + "#" + appSecret;
        return this.getMD5(code);
    }

    /**
     * md5加密
     *
     * @param sourceStr
     * @return
     */
    private String getMD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}