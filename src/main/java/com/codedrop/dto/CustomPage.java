package com.codedrop.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {

    List<T> content;
    CustomPageable pageable;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pageable = new CustomPageable(
            page.getPageable().getPageNumber(),
            page.getPageable().getPageSize(),
            page.getTotalPages(),
            page.getTotalElements()
        );
    }

    @Data
    static class CustomPageable {
        int pageNumber;
        int pageSize;
        long totalPages;
        long totalElements;

        public CustomPageable(int pageNumber, int pageSize, long totalPages, long totalElements) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.totalPages = totalPages;
            this.totalElements = totalElements;
        }
    }
}
