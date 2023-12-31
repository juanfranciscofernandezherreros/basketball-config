package com.fernandez.results.basketball.domain;

import com.fernandez.results.basketball.dao.BasketballConfigDAO;
import com.fernandez.results.basketball.dto.BasketballConfigDTO;
import com.fernandez.results.basketball.entity.MyEntityNotFoundException;
import com.fernandez.results.basketball.mapper.BasketballMapper;
import com.fernandez.results.basketball.repository.BasketballRepository;
import com.fernandez.results.basketball.service.BasketballService;
import com.fernandez.results.basketball.service.impl.BasketballRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class BasketballDomainServiceImpl implements BasketballDomainService {

    @Autowired
    private BasketballRepository basketballRepository;

    @Autowired
    private BasketballRepositoryImpl basketballRepositoryImpl;
    @Autowired
    private BasketballMapper basketballMapper;

    @Override
    public Page<BasketballConfigDTO> findAllByDynamicCriteria(Map<String, String> params, int page, int size) {
        log.info("Finding configs by dynamic criteria: {}, page: {}, size: {}", params, page, size);
        return basketballMapper.mapToPageDTO(basketballRepositoryImpl.findAllByDynamicCriteriaWithPagination(params, page, size));
    }

    @Override
    public List<BasketballConfigDTO> saveAll(List<BasketballConfigDAO> basketballConfigDTOList) {
        log.info("Saving list of configs...");
        return basketballMapper.mapListToDTO(basketballRepository.saveAll(basketballConfigDTOList));
    }

    @Override
    public BasketballConfigDTO findById(Long id) {
        log.info("Finding findById by id: {}", id);
        Optional<BasketballConfigDAO> optionalFixturesDAO = basketballRepository.findById(id);
        // Use orElseThrow to throw EntityNotFoundException if the entity is not present
        BasketballConfigDAO basketballConfigDAO = optionalFixturesDAO.orElseThrow(() ->
                new MyEntityNotFoundException("Fixtures with matchId " + id + " not found"));
        // Map the FixturesDAO to a FixturesDTO and return it
        return basketballMapper.mapToDTO(basketballConfigDAO);
    }

    @Override
    public List<BasketballConfigDTO> updateAll(List<BasketballConfigDAO> fixturesList) {
        return null;
    }

    @Override
    public void deleteByIds(List<String> ids) {
        log.info("Deleting config by ids: {}", ids);
        basketballRepository.deleteByIds(ids);
    }
}
