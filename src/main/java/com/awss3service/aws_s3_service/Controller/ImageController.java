package com.awss3service.aws_s3_service.Controller;

import com.awss3service.aws_s3_service.Service.ImageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


}
