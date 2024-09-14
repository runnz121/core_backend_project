package kitten.diy.api.adapter.out.persistence.query;

import kitten.diy.api.adapter.out.model.MainHomeQueryData;
import kitten.diy.api.application.port.in.command.HomeInfoSearchCommand;
import org.springframework.data.domain.Page;

public interface MainHomeQueryFetch {

    Page<MainHomeQueryData> getMainHomeDatas(HomeInfoSearchCommand command);
}
