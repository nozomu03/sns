package com.example.sns;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentP {
    private String storedPath;
    private String originalName;

    public AttachmentP(String storedPath, String originalName) {
        this.storedPath = storedPath;
        this.originalName = originalName;
    }
}
