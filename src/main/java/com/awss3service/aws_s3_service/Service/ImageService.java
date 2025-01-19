package com.awss3service.aws_s3_service.Service;

import com.awss3service.aws_s3_service.Entity.ImageEntity;
import com.awss3service.aws_s3_service.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService implements ImageServiceImpl {


    private final ImageRepository imageRepository;

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    //uploading image to Bucket
    public String uploadImage(MultipartFile file) throws Exception {

        String originalFilename = file.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(originalFilename)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            String url = String.format("http://%s.s3.amazonaws.com/%s", bucketName, originalFilename);

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImageName(originalFilename);
            imageEntity.setImageUrl(url);
            imageRepository.save(imageEntity);

            return url;
        } catch (Exception e) {
            throw new IOException("Something Went wrong");

        }

    }

    public String deleteImageById(Long id) {

        String imageName = imageRepository.fetchImageNameById(id);

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(imageName)
                .build();

        s3Client.deleteObject(deleteObjectRequest);

        imageRepository.deleteById(id);
        return "Deleted Successfully";
    }


}
