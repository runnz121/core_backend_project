package kitten.core.coredomain.config.aws.impl;

import kitten.core.coredomain.config.aws.S3Properties;
import kitten.core.coredomain.config.aws.S3Repository;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class S3RepositoryImpl implements S3Repository {

    private final S3Client s3;
    private final S3Properties s3Properties;

    public S3RepositoryImpl(S3Client s3, S3Properties s3Properties) {
        this.s3 = s3;
        this.s3Properties = s3Properties;
    }

    @Override
    public void getObject(String keyName, String path) {

        try {
            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .key(keyName)
                    .bucket(s3Properties.getAwsBucketName())
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();

            // Write the data to a local file.
            File s3Object = new File(path);
            try (OutputStream os = new FileOutputStream(s3Object)) {
                os.write(data);
            }
        } catch (IOException | S3Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putObject(String objectKey, String objectPath) {

        try {
            // 추가적인 메타데이터가 필요하면 여기 추가하면 된다.
            Map<String, String> metadata = new HashMap<>();

            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(s3Properties.getAwsBucketName())
                    .key(objectKey)
                    .metadata(metadata)
                    .build();

            s3.putObject(putOb, RequestBody.fromFile(new File(objectPath)));
        } catch (S3Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putObject(String objectKey, InputStream inputStream, String contentType, long contentLength) {
        try {
            // 추가적인 메타데이터가 필요하면 여기 추가하면 된다.
            Map<String, String> metadata = new HashMap<>();

            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(s3Properties.getAwsBucketName())
                    .key(objectKey)
                    .contentType(contentType)
                    .contentLength(contentLength)
                    .metadata(metadata)
                    .build();

            s3.putObject(putOb, RequestBody.fromInputStream(inputStream, contentLength));
        } catch (S3Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteObject(String objectKey) {

        try {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(s3Properties.getAwsBucketName())
                    .key(objectKey)
                    .build();

            s3.deleteObject(request);
        } catch (AwsServiceException | SdkClientException e) {
            throw new RuntimeException(e);
        }
    }
}
