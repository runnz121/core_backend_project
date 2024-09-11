package kitten.core.coredomain.config.aws;

import kitten.core.coredomain.config.aws.impl.S3RepositoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@AutoConfiguration(after = {AwsAutoConfiguration.class})
@ConditionalOnClass(S3Client.class)
@ConditionalOnProperty(prefix = "aws.s3", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(S3Properties.class)
public class S3AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public S3Repository s3repository(S3Client client,
                                     S3Properties s3Properties) {
        return new S3RepositoryImpl(client, s3Properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public S3Client s3Client(S3Properties properties,
                             AwsCredentialsProvider credentialsProvider) {
        return S3Client.builder()
                .region(Region.of(properties.getAwsDefaultRegion()))
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
