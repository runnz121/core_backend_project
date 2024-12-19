package kitten.diy.api.adapter.in.web;


import kitten.core.corecommon.annotation.Description;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.application.port.in.command.FileCommandUseCase;
import kitten.diy.api.application.port.in.command.command.UploadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileCommandUseCase fileCommandUseCase;

    @Description("파일 업로드")
    @PostMapping("/upload")
    public String upload(@RequestParam("uploadFile") MultipartFile file,
                         @RequestParam("imageSort") String imageSort)  {
        return fileCommandUseCase.upload(new UploadCommand(file, imageSort));
    }
}
