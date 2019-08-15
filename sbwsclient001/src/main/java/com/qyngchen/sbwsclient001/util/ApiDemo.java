package com.qyngchen.sbwsclient001.util;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ApiDemo {

    //输入你的app_key
    private final static String app_key = "586949450e231c71";
    //输入你的app_secret
    private final static String app_secret = "e27c8593b933fe892969877941d729dd";
    //本机要上传的图片位置
    private final static String path = "C:/Users/chenqingyang/Pictures/0eb30f2442a7d933194dc98dad4bd11372f00162.jpg";
    //要使用的api的uri
    private final static String uri = "/api/v1/recognition/check";

    private final static String BOUNDARY = "----WebKitFormBoundarygrBcuHVTeNQcBtqn";
    private static String name;

    public static void main(String[] args) throws Exception {
        String url = "https://link.bi.sensetime.com" + uri;
        //表单文本参数
        Map<String, Object> params = new HashMap();
        //表单文件参数
        Map<String, byte[]> fileParams = new HashMap();

        File avatar_file = new File(path);
        name = avatar_file.getName();
        Long timestamp = getTimestamp();
        //添加表单文本参数
        {
            params.put("app_key", app_key);
            params.put("timestamp", String.valueOf(timestamp));
            params.put("sign", getSign(String.valueOf(timestamp)));
        }
        //添加表单文件参数
        {
            fileParams.put("face_avatar", getBytes(avatar_file));
        }
        String result = new String(doPost(url, params, fileParams));
        System.out.println(result);

    }

    public static byte[] doPost(String strUrl, Map<String, Object> params, Map<String, byte[]> fileParams) throws Exception {
        URL url = new URL(strUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json, text/plain, */*"); // 设置接收数据的格式
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY); // 设置发送数据的格式
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            out.writeBytes("--" + BOUNDARY + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"");
            out.writeBytes("\r\n\r\n");
            out.writeBytes(value + "\r\n");
        }
        if (fileParams != null && fileParams.size() > 0) {
            Iterator fileIt = fileParams.entrySet().iterator();
            while (fileIt.hasNext()) {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIt.next();
                out.writeBytes("--" + BOUNDARY + "\r\n");
                out.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + name + "\"");
                out.writeBytes("\r\n");
                out.writeBytes("Content-Type: image/jpeg");//此处很关键
                out.writeBytes("\r\n\r\n");
                out.write(fileEntry.getValue());
                out.writeBytes("\r\n");
            }
        }
        out.writeBytes("--" + BOUNDARY + "--");
        out.flush();
        out.close();
        InputStream in = null;
        int code = connection.getResponseCode();
        try {
            if (code == 200) {
                in = connection.getInputStream();
            } else {
                in = connection.getErrorStream();
            }
        } catch (SSLException e) {
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len;
        while ((len = in.read(buff)) != -1) {
            baout.write(buff, 0, len);
        }
        byte[] bytes = baout.toByteArray();
        in.close();
        return bytes;
    }


    public static Long getTimestamp() {
        return System.currentTimeMillis();
    }

    public static String getSign(String timestamp) {
        String code = timestamp + "#" + app_secret;
        return getMD5(code);
    }

    private static String getMD5(String sourceStr) {
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

    public static byte[] getBytes(File f) {
        try {
            InputStream in = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            in.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            System.out.println("***请设置文件路径***");
        }
        return null;
    }
}
