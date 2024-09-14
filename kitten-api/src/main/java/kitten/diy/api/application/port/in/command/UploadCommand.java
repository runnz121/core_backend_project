package kitten.diy.api.application.port.in.command;


import kitten.core.coredomain.utils.DateUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Objects;

public record UploadCommand(

        MultipartFile file,

        String userEmail,

        String sort
) {

    private static final String IMAGE_PREFIX = "image";

    public InputStream getFileStream() {
        try {
            return file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getContentLength() {
        return file.getSize();
    }

    public String getContentType() {
        return file.getContentType();
    }


    public String getOriginalFileExtension() {
        return Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf('.') + 1);
    }

    public String getUploadKey(String randomPath) {
        return "%s/%s/%s/%s.%s".formatted(
                IMAGE_PREFIX,
                sort,
                DateUtil.toString(LocalDateTime.now(), DateUtil.YYMMDD_STRING),
                randomPath,
                getOriginalFileExtension());
    }
}
