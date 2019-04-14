package com.example.sns;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Service
public class CommentServiceimpl implements CommentService{
    @Autowired
    CommmentRepository cr;

    @PostConstruct
    public void Init(){
        this.cr.save(new Comment("abc", "테스트 코멘트", "D:\\tete\\2019\\04\\98\\3e115b24-5e9c-442c-a165-5e988e61102d_1901___687308122.jpg", "3e115b24-5e9c-442c-a165-5e988e61102d_1901___687308122.jpg"));
    }

    @Override
    public boolean add(String writer, String info, String path, String imageN) {
        try{
            this.cr.save(new Comment(writer, info , path, imageN));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> commentList = this.cr.findAll();
        try {
            for(int i=0; i<commentList.size(); i++){
                File file = new File(commentList.get(i).getLocation());
                OutputStream out = new BufferedOutputStream(new FileOutputStream(file));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}
