package com.mkowalski.nb.etl.scheduler.dto;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("JobSearchCriteria")
public class SearchDto {

    private String search;

    public Predicate toQueryDslPredicate(List<Predicate> predicates) {
        return ExpressionUtils.anyOf(predicates);
    }
}
