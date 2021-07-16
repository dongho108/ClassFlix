package dongho.classflix.controller.dto;

import lombok.Getter;

@Getter
public class PageDto {
    private final int PAGENUM = 5;
    private int startPage;
    private int endPage;
    private boolean prev, next;

    private int total;

    public PageDto() {
    }

    public PageDto(int total) {
        this.total = total;

        this.endPage = (int) (Math.ceil(PAGENUM / 10.0)) * 10;
        this.startPage = this.endPage - (PAGENUM - 1);

        int realEnd = (int) (Math.ceil((total * 1.0) / PAGENUM));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
