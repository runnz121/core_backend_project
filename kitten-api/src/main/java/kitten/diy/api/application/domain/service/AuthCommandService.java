package kitten.diy.api.application.domain.service;

import kitten.core.corecommon.utils.AuthUtil;
import kitten.core.corecommon.utils.CookieUtils;
import kitten.diy.api.application.port.in.command.AuthCommandUseCase;
import kitten.diy.api.application.port.in.command.command.LogoutCommand;
import kitten.diy.api.application.port.in.query.data.LogoutResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCommandService implements AuthCommandUseCase {

    @Override
    public LogoutResultData logOut(LogoutCommand command) {
        CookieUtils.deleteCookie(command.request(), command.response(), AuthUtil.KITTEN_COOKIE_NAME);
        return LogoutResultData.success();
    }
}
