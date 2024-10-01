package kitten.diy.api.application.port.out;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.application.port.in.command.command.MyArtSearchCommand;
import kitten.diy.api.application.port.in.query.data.MyPageArtData;
import kitten.diy.api.application.port.in.query.data.MyPageData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MyPageFetchPort {

    MyPageData getMyPageData(String userEmail);

    PageableData<List<MyPageArtData>> getMyArtInfos(MyArtSearchCommand command);
}
