package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.annotation.Description;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.adapter.in.web.request.AvatarRequest;
import kitten.diy.api.application.port.in.command.ItemCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ItemCommandUseCase itemCommandUseCase;

    @Description("유저 아트 정보 업로드 및 게시글 (모루 + 파츠 + 공간 + 게시글)")
//    @Secured(value = "ROLE_USER")
    @PostMapping("/moru/arts")
    public void registerAvatar(@AccessAccount CurrentAccount account,
                               @RequestBody AvatarRequest avatarRequest) {
        account = CurrentAccount.defaultValue();
        itemCommandUseCase.saveAvatar(avatarRequest.toCommand(account.getUserEmail()));
    }
}
