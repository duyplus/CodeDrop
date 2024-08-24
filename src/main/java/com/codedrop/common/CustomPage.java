package com.codedrop.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {

    List<T> content;
    CustomPageable pagination;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pagination = new CustomPageable(
            page.getPageable().getPageNumber(),
            page.getPageable().getPageSize(),
            page.getTotalPages(),
            page.getTotalElements()
        );
    }

    @Data
    static class CustomPageable {
        int page;
        int pageSize;
        long totalPages;
        long totalItems;

        public CustomPageable(int page, int pageSize, long totalPages, long totalItems) {
            this.page = page;
            this.pageSize = pageSize;
            this.totalPages = totalPages;
            this.totalItems = totalItems;
        }
    }
}
