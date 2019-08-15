package com.qyngchen.sbminio.controller;


import com.qyngchen.sbminio.service.MinioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class MinioController {

    @Autowired
    private MinioService minioService;

    @CrossOrigin
    @ApiOperation(value = "上传图片")
    @PostMapping("/imgUpload")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "success"),
            @ApiResponse(code = 2000, message = "not_found"),
            @ApiResponse(code = 3000, message = "invalid_input"),
            @ApiResponse(code = 5000, message = "inner_error")})
    public void tets(MultipartFile file) {
        try {
            if (file.isEmpty()) {
            }
            String folder = System.getProperty("java.io.tmpdir");
            //文件名8
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf('.') + 1);
            String newFimeName = UUID.randomUUID().toString().replace("-", "");
            String targetFileName = newFimeName + "." + suffix;
            File dest = new File(folder + File.separator + targetFileName);
            file.transferTo(dest);

            minioService.uploadFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/get")
    public void imgae(HttpServletResponse response) {
        InputStream in = null;
        ServletOutputStream out = null;
        response.setContentType("image/gif");
        try {
            in = minioService.downloadFiel();
//            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] bytes = new byte[1024 * 10];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
