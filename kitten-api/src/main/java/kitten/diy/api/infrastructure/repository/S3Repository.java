package kitten.diy.api.infrastructure.repository;


import java.io.InputStream;

public interface S3Repository {

    /**
     * S3 객체 조회
     *
     * @param keyName S3 객체 키
     * @param path 로컬 파일 저장 경로 (파일명으로 포함한 풀 경로)
     */
    void getObject(String keyName, String path);

    /**
     * S3 객체 업로드
     *
     * @param objectKey S3 객체 키
     * @param objectPath 로컬 업로드용 파일 위치 (파일명으로 포함한 풀 경로)
     */
    void putObject(String objectKey, String objectPath);

    /**
     * S3객체 업로드
     *
     * @param objectKey     - S3객체 키
     * @param inputStream   - 파일 스트림
     * @param contentType
     * @param contentLength - 파일 사이즈
     */
    void putObject(String objectKey, InputStream inputStream, String contentType, long contentLength);

    /**
     * S3 객체 삭제
     *
     * @param objectKey S3 객체 키
     */
    void deleteObject(String objectKey);

}
