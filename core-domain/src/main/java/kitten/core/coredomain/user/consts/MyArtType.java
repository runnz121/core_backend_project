package kitten.core.coredomain.user.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public enum MyArtType {

    MY_ARTS("내 작품"),
    MY_LIKE_ARTS("내가 찜한 작품"),
    MY_PARTS("내가 업로드한 파츠")
    ;

    private final String desc;

    public static MyArtType of(String type) {
        if (StringUtils.hasText(type) == false) return null;
        return MyArtType.valueOf(type);
    }
}
