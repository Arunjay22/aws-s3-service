package com.awss3service.aws_s3_service.Repository;

import com.awss3service.aws_s3_service.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
}
