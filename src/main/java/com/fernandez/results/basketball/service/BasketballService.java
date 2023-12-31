package com.fernandez.results.basketball.service;

import com.fernandez.results.basketball.dto.BasketballConfigDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BasketballService {
    Page<BasketballConfigDTO> findAllByDynamicCriteria(Map<String, String> params, int page, int size);

    BasketballConfigDTO findConfigById(Long id);

    List<BasketballConfigDTO> saveAll(List<BasketballConfigDTO> fixturesList);

    List<BasketballConfigDTO> updateAll(List<BasketballConfigDTO> fixturesList);

    void deleteByIds(List<String> matchIds);
}
