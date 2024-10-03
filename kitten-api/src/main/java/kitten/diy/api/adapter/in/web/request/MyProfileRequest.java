package kitten.diy.api.adapter.in.web.request;

import kitten.diy.api.application.port.in.command.command.MyProfileCommand;

public record MyProfileRequest(

        String nickName,

        String profileImgUrl
) {

    public MyProfileCommand toCommand(String userEmail) {
        return MyProfileCommand.builder()
                .nickName(nickName)
                .profileImgUrl(profileImgUrl)
                .userEmail(userEmail)
                .build();
    }
}
