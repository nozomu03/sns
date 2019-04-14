package com.example.sns;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface CommentService {
    boolean add(String writer, String info, String path, String imageN);
    List<Comment> getAll();
}
