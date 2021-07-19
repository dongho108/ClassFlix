package dongho.classflix.controller.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class PageDto {
    private final int PAGENUM = 10; // 페이지 몇개로 구성할건지
    private int pageSize; // 페이지당 몇개 표시할건지
    private int startPage;
    private int endPage;
    private int curPage;
    private boolean prev, next;

    private long total;

    public PageDto() {
    }

    public PageDto(long total, Pageable pageable) {
        this.total = total;
        this.curPage = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();

        this.endPage = (int) (Math.ceil((curPage+1) / 10.0)) * 10;
        this.startPage = this.endPage - (PAGENUM - 1);

        int realEnd = (int) (Math.ceil((total * 1.0) / pageSize));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = (curPage+1) > 1; // view에서는 1부터 시작이므로
        this.next = (curPage+1) < realEnd; // view에서는 1부터 시작이므로
    }
}
