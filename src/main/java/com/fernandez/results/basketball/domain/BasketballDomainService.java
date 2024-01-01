package com.fernandez.results.basketball.domain;

import com.fernandez.results.basketball.dao.BasketballConfigDAO;
import com.fernandez.results.basketball.dao.BasketballConfigPKDAO;
import com.fernandez.results.basketball.dto.BasketballConfigDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BasketballDomainService {

    List<BasketballConfigDTO> updateAll(List<BasketballConfigDAO> mapListToDAO);

    List<BasketballConfigDTO> saveAll(List<BasketballConfigDAO> mapListToDAO);

    BasketballConfigDTO findById(BasketballConfigDTO id);

    Page<BasketballConfigDTO> findAllByDynamicCriteria(Map<String, String> params, int page, int size);

    void deleteByIds(BasketballConfigDTO basketballConfigDTO);
}
