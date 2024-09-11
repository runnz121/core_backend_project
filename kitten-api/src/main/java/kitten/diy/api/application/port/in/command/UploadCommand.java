package kitten.diy.api.application.port.in.command;

import org.springframework.web.multipart.MultipartFile;

public record UploadCommand(

        MultipartFile file,

        String userEmail
) {
}
