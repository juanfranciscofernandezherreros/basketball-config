package com.fernandez.results.basketball.service.impl;

import com.fernandez.results.basketball.dao.BasketballConfigDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class BasketballRepositoryImpl {
    private final MongoTemplate mongoTemplate;
    public BasketballRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<BasketballConfigDAO> findAllByDynamicCriteriaWithPagination(Map<String, String> dynamicCriteria, int page, int size) {
        Query query = buildQuery(dynamicCriteria);
        long count = mongoTemplate.count(query, BasketballConfigDAO.class);
        query.with(PageRequest.of(page, size));
        List<BasketballConfigDAO> resultList = mongoTemplate.find(query, BasketballConfigDAO.class);
        return new PageImpl<>(resultList, PageRequest.of(page, size), count);
    }

    private Query buildQuery(Map<String, String> dynamicCriteria) {
        Query query = new Query();
        return query;
    }
}
