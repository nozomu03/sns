package com.example.sns;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService cs;

    @PostMapping("/comment")
    public boolean addComment(@RequestParam String name, @RequestParam String info, @RequestParam String loca, @RequestParam String imageN){
        return this.cs.add(name, info, loca, imageN);
    }

    @GetMapping("/comment")
    public List<Comment> getAll(){
        return this.cs.getAll();
    }

    @GetMapping("/image/{id}")
    public void GetImage(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        String filePath = "D:\\tete\\2019\\04\\95\\5eb0a2ef-2a31-42de-a427-65bc32d0adb6_mei.jpg";
        String fileName = "mei.jpg";
        List<Comment> commentList = this.cs.getAll();
        for(Comment temp : commentList) {
            if(temp.getId() == id){
                filePath= temp.getLocation();
                fileName= temp.getImageN();
                break;
            }
        }
        try {
            File file = new File(filePath);
            String memeType = URLConnection.guessContentTypeFromName(file.getName());
            if(memeType == null)
                memeType="application/octet-stream";

            response.setContentType(memeType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
