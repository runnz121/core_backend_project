package kitten.diy.api.adapter.out.infra;

import kitten.core.coredomain.config.aws.S3Repository;
import kitten.core.coredomain.utils.IdGenerator;
import kitten.diy.api.application.port.in.command.command.UploadCommand;
import kitten.diy.api.application.port.out.ImageInfraPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ImageInfraAdapter implements ImageInfraPort {

    private static final String S3_HOST_URL = "https://mium-eunsun.s3.ap-northeast-2.amazonaws.com/";
    private static final int GENERATE_LENGTH = 6;

    private final IdGenerator idGenerator;

    private final S3Repository s3Repository;

    @Override
    public String uploadImage(UploadCommand command) {
        String randomId = idGenerator.generate(GENERATE_LENGTH);
        String objectKey = command.getUploadKey(randomId);
        s3Repository.putObject(objectKey, command.getFileStream(), command.getContentType(), command.getContentLength());
        return "%s%s".formatted(S3_HOST_URL, objectKey);
    }
}
