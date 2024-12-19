package kitten.core.coredomain.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageableData<T extends Collection<?>> {

    private T data;
    private PageData page;
}
