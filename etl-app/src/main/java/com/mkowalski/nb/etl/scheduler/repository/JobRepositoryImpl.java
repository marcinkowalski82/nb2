package com.mkowalski.nb.etl.scheduler.repository;

import com.mkowalski.nb.etl.scheduler.entity.Job;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyPath;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.mkowalski.nb.etl.scheduler.entity.QJob.job;

public class JobRepositoryImpl implements CustomJobRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private PathBuilder<Job> pathBuilder = new PathBuilder<>(Job.class, "job");

    @Override
    public Page<Job> fetchPaged(Pageable pageRequest) {
        return fetchPaged(pageRequest, null);
    }

    @Override
    public Page<Job> fetchPaged(Pageable pageRequest, Predicate predicate) {
        checkNotNull(pageRequest, "Parameter pageRequest mustn't be null");

        int pageSize = pageRequest.getPageSize();
        int offset = getOffset(pageRequest);

        JPQLQuery<Job> jobQuery = new JPAQueryFactory(entityManager)
                .selectFrom(job)
                .offset(offset)
                .limit(pageSize);

        if (predicate != null) {
            jobQuery = jobQuery.where(predicate);
        }

        if(pageRequest.getSort() != null){
            jobQuery = addOrderByFrom(pageRequest.getSort(), jobQuery);
        }

        List<Job> jobs = jobQuery.fetch()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        long total = new JPAQueryFactory(entityManager)
                          .from(job)
                          .where(predicate)
                          .fetchCount();

        return new PageImpl<>(jobs, pageRequest, total);
    }

    public void clear() {
        entityManager.clear();
    }

    protected int getOffset(Pageable pageRequest) {
        int pageNo = pageRequest.getPageNumber();
        int pageSize = pageRequest.getPageSize();
        return pageNo * pageSize;
    }

    private JPQLQuery addOrderByFrom(Sort sort, JPQLQuery query) {
        for (Sort.Order order : sort) {
            query.orderBy(toOrderSpecifier(order));
        }
        return query;
    }

    private OrderSpecifier toOrderSpecifier(Sort.Order order) {
        return new OrderSpecifier(
                order.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC,
                buildOrderPropertyPathFrom(order), OrderSpecifier.NullHandling.NullsLast);
    }

    private Expression<?> buildOrderPropertyPathFrom(Sort.Order order) {
        PropertyPath path = PropertyPath.from(order.getProperty(), pathBuilder.getType());
        Expression<?> sortPropertyExpression = pathBuilder;

        while (path != null) {
            if (!path.hasNext() && path.getType().isAssignableFrom(String.class)) {
                sortPropertyExpression = Expressions.stringPath((Path<?>) sortPropertyExpression, path.getSegment()).lower();
            } else {
                sortPropertyExpression = Expressions.path(path.getType(), (Path<?>) sortPropertyExpression, path.getSegment());
            }
            path = path.next();
        }

        return sortPropertyExpression;
    }
}
