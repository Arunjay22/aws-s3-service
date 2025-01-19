package com.awss3service.aws_s3_service.Service;

import com.awss3service.aws_s3_service.Repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements ImageServiceImpl {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
