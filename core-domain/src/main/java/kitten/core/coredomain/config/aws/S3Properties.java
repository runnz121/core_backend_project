package kitten.core.coredomain.config.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aws.s3")
public class S3Properties {

    private boolean enabled = true;

    private String awsDefaultRegion = "ap-northeast-2";

    private String awsBucketName;

    private String awsAccessKeyId;

    private String awsSecretAccessKey;

}
