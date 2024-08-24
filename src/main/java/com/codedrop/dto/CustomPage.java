package com.codedrop.dto;

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
            page.getPageable().getPageSize()
        );
    }

    @Data
    static class CustomPageable {
        int page;
        int pageSize;

        public CustomPageable(int page, int pageSize) {
            this.page = page;
            this.pageSize = pageSize;
        }
    }
}
