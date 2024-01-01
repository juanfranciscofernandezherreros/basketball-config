package com.fernandez.results.basketball.repository;

import com.fernandez.results.basketball.dao.BasketballConfigDAO;
import com.fernandez.results.basketball.dao.BasketballConfigPKDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketballRepository extends MongoRepository<BasketballConfigDAO, BasketballConfigPKDAO> {

}
