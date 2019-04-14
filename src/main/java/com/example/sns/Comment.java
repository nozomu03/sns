package com.example.sns;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String information;
    private String location;

    private String imageN;

    @CreationTimestamp
    private LocalDateTime time;

    public Comment(String userName, String information, String location, String imageN) {
        this.userName = userName;
        this.information = information;
        this.location = location;
        this.imageN = imageN;
    }
}
