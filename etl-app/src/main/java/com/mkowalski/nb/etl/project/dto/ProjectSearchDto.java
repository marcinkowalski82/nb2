package com.mkowalski.nb.etl.project.dto;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.util.Optional;

import static com.mkowalski.nb.etl.project.entity.QProject.project;

@Data
@ApiModel("JobSearchCriteria")
public class ProjectSearchDto {

    private String name;
    private String type;

    public Predicate toQueryDslPredicate() {
        return ExpressionUtils.allOf(
                nameEquals(),
                typeEquals()
        );
    }

    private Predicate nameEquals() {
        return Optional.ofNullable(name)
                .map(project.name::eq)
                .orElse(null);
    }

    private Predicate typeEquals() {
        return Optional.ofNullable(type)
                .map(project.type::eq)
                .orElse(null);
    }
}
