package com.javaweb.demo4.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@WebServlet("/uploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("fileToUpload"); // 从请求中获取文件部分
        String cd = filePart.getHeader("Content-Disposition");
//截取不同类型的文件需要自行判断
        String fileName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
        System.out.println(fileName);

        // 指定上传文件在服务器上的存储位置
        String uploadDir = "C:\\Users\\asus\\Desktop\\BMS-master\\BookManagerSystem\\web\\images\\";
        File file = new File(uploadDir + fileName);

        // 如果目录不存在，则创建它
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // 将文件保存到指定位置
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
    }
}