package kitten.diy.api.application.port.in.query;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.application.port.in.command.command.MyArtSearchCommand;
import kitten.diy.api.application.port.in.query.data.MyPageArtData;
import kitten.diy.api.application.port.in.query.data.MyPageData;

import java.util.List;

public interface MyPageQueryUseCase {

    MyPageData getMyPageInfo(String userEmail);

    PageableData<List<MyPageArtData>> getMyArtInfos(MyArtSearchCommand command);
}
