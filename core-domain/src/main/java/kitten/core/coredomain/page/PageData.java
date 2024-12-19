package kitten.core.coredomain.page;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * 페이지 객체
 *
 * number 페이지 번호
 * size 페이지 사이즈
 * first 처음 여부
 * last 마지막 여부
 * totalPages 총 페이지 개수
 * totalElements 총 요소수
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageData {

    private Integer number;
    private Integer size;
    private Boolean isFirst;
    private Boolean isLast;
    private Integer totalPages;
    private Long totalElements;

    public static PageData of(Page page) {
        return PageData.builder()
                .number(page.getNumber())
                .size(page.getSize())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
