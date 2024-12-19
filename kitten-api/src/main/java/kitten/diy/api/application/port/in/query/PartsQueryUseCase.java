package kitten.diy.api.application.port.in.query;

import kitten.diy.api.adapter.out.model.PartDetail;
import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;

import java.util.List;

public interface PartsQueryUseCase {

    List<PartsThemeData> getPartsByTheme(PartsSearchCommand command);

    PartDetail getPartDetail(Long parentPartsKey);

    List<PartDetail> getAllPartsDetails();
}
