package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.query.data.MyPageData;

public interface MyPageFetchPort {

    MyPageData getMyPageData(String userEmail);
}
