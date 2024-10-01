package kitten.diy.api.application.port.in.query;

import kitten.diy.api.application.port.in.query.data.MyPageData;

public interface MyPageQueryUseCase {

    MyPageData getMyPageInfo(String userEmail);
}
