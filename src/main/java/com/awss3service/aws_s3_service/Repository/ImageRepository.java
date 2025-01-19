package com.awss3service.aws_s3_service.Repository;

import com.awss3service.aws_s3_service.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {


    @Query(value = "SELECT image_name FROM image_table WHERE id = :id", nativeQuery = true)
    String fetchImageNameById(@Param("id") Long id);
}
