package com.fernandez.results.basketball.service.impl;
import com.fernandez.results.basketball.dao.BasketballConfigPKDAO;
import com.fernandez.results.basketball.domain.BasketballDomainService;
import com.fernandez.results.basketball.dto.BasketballConfigDTO;
import com.fernandez.results.basketball.entity.MyEntityNotFoundException;
import com.fernandez.results.basketball.mapper.BasketballMapper;
import com.fernandez.results.basketball.service.BasketballService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BasketballServiceImpl implements BasketballService {

    @Autowired
    private BasketballDomainService basketballDomainService;
    @Autowired
    private BasketballMapper basketballMapper;

    @Override
    public Page<BasketballConfigDTO> findAllByDynamicCriteria(Map<String, String> params, int page, int size) {
        return basketballDomainService.findAllByDynamicCriteria(params,page,size);
    }

    @Override
    public BasketballConfigDTO findConfigById(BasketballConfigDTO id) {
        return basketballDomainService.findById(id);
    }

    @Override
    public List<BasketballConfigDTO> saveAll(List<BasketballConfigDTO> basketballConfigDTOList) {
        return basketballDomainService.saveAll(basketballMapper.mapListToDAO(basketballConfigDTOList));
    }

    @Override
    public List<BasketballConfigDTO> updateAll(List<BasketballConfigDTO> fixturesList) {
        return basketballDomainService.updateAll(basketballMapper.mapListToDAO(fixturesList));
    }

    @Override
    public void deleteById(BasketballConfigDTO basketballConfigDTO) {
        try {
            basketballDomainService.deleteByIds(basketballConfigDTO);
        } catch (MyEntityNotFoundException e) {
            // Log the exception and rethrow or handle as needed
            log.error("MyEntityNotFoundException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Log the exception and rethrow or handle as needed
            log.error("Exception during deleteById: {}", e.getMessage());
            throw new RuntimeException("Error deleting configuration", e);
        }
    }
}
