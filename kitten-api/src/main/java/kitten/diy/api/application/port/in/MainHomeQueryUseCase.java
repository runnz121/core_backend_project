package kitten.diy.api.application.port.in;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.application.port.in.command.HomeInfoSearchCommand;
import kitten.diy.api.application.port.in.query.MainHomeInfoData;

import java.util.List;

public interface MainHomeQueryUseCase {

    PageableData<List<MainHomeInfoData>> getMainHomeInfos(HomeInfoSearchCommand command);
}
