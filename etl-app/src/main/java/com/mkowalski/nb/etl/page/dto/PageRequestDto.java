package com.mkowalski.nb.etl.page.dto;

import io.swagger.annotations.ApiModel;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;

@ApiModel("PageRequest")
public class PageRequestDto {

    @NonNull
    @Min(value = 0, message = "Page number can't be less than 0")
    private Integer pageNumber;

    @NonNull
    @Min(value = 0, message = "Page size can't be less than 0")
    private Integer pageSize;

    private String sortProperty;

    private Boolean ascending;

    public PageRequestDto() {
        //empty constructor
    }

    public PageRequestDto(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageRequestDto(Integer pageNumber, Integer pageSize, String sortProperty, Boolean ascending) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortProperty = sortProperty;
        this.ascending = ascending;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

}
