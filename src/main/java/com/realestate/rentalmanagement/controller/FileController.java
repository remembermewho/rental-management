package com.realestate.rentalmanagement.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URLConnection;

@RestController
@RequestMapping("/uploads/photos")
public class FileController {

    @GetMapping("/{date}/{filename:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String date, @PathVariable String filename) {
        String basePath = System.getProperty("user.dir") + "/uploads/photos/" + date + "/";
        File file = new File(basePath + filename);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(file);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    }
}
