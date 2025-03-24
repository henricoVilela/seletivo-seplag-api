package br.gov.br.seplag_api.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {
    
    @Value("${minio.endpoint}")
    private String endpoint;
    
    @Value("${minio.public.endpoint}")
    private String publicEndpoint;
    
    @Value("${minio.accessKey}")
    private String accessKey;
    
    @Value("${minio.secretKey}")
    private String secretKey;
    
    @Bean
    @Qualifier("internalMinioClient")
    MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
    
    @Bean
    @Qualifier("publicMinioClient")
    MinioClient publicMinioClient() {
        return MinioClient.builder()
            .endpoint(publicEndpoint)
            .credentials(accessKey, secretKey)
            .build();
    }
}
