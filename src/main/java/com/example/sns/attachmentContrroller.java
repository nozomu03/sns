package com.example.sns;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
public class attachmentContrroller {
    @Autowired
    private UserService us;

    @Autowired
    UserRepository ur;

    @Autowired
    CommmentRepository cr;

    @PostMapping("/attachment")
    public AttachmentP upload(@RequestPart MultipartFile srcFile){
        String destFilename = "D:/download/tete/"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY/MM/DD")) +"/"+ UUID.randomUUID().toString()+"_"+srcFile.getOriginalFilename();
        try{
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            return new AttachmentP(destFilename, srcFile.getOriginalFilename());
        }catch (IOException e) {
            return null;
        }
    }

    @GetMapping(value = "/attachment/{type}/{id}")
    public @ResponseBody byte[] Download(@PathVariable int type, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            String filePath = "D:\\tete\\2019\\04\\95\\5eb0a2ef-2a31-42de-a427-65bc32d0adb6_mei.jpg";
            String fileName = "mei.jpg";
            if (type == 1) {
                List<Comment> commentList = this.cr.findAll();
                for(Comment temp : commentList){
                    if(temp.getId() == id){
                        filePath=temp.getLocation();
                    }
                }
            }else if (type == 2) {
                List<User> userList = this.ur.findAll();
                for (User temp : userList) {
                    if (temp.getId() == id) {
                        filePath = temp.getLocation();
                    }
                }
            }
        }catch(Exception e){
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('N/A'); self.close();</script>");
                System.out.print(e.getMessage());
            }catch(Exception ex){
                System.out.print(e.getMessage());
            }
        }
        return null;
    }
}
