package com.bycoders.resource;

import com.bycoders.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadResource {

    @Autowired
    private UploadService uploadService;

    @GetMapping
    public String displayForm() {
        return "fileUploadForm";
    }

    @PostMapping(value = "/uploadFile")
    public String upload(@RequestParam MultipartFile file){
        uploadService.readFile(file);
        return "fileUploadView";
    }

}
