package kitten.diy.api.application.port.out;

import kitten.diy.api.adapter.out.model.MainHomeQueryData;
import kitten.diy.api.application.port.in.command.HomeInfoSearchCommand;
import org.springframework.data.domain.Page;

public interface MainHomePort {

    Page<MainHomeQueryData> getMainHomeBoardDatas(HomeInfoSearchCommand command);
}
