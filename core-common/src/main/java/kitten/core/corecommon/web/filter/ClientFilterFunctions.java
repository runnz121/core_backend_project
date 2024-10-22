package kitten.core.corecommon.web.filter;

import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ClientFilterFunctions implements Consumer<List<ExchangeFilterFunction>> {

    ExchangeFilterFunction[] filterFunctions;

    public ClientFilterFunctions(ExchangeFilterFunction...filterFunctions) {
        this.filterFunctions = filterFunctions;
    }

    @Override
    public void accept(List<ExchangeFilterFunction> exchangeFilterFunctions) {
        exchangeFilterFunctions.addAll(getFilterFunctions());
    }

    private List<ExchangeFilterFunction> getFilterFunctions() {
        return Arrays.stream(filterFunctions).toList();
    }
}
