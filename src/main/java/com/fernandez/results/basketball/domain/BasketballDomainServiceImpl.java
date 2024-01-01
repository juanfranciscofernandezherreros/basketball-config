package com.fernandez.results.basketball.domain;

import com.fernandez.results.basketball.dao.BasketballConfigDAO;
import com.fernandez.results.basketball.dao.BasketballConfigPKDAO;
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
    public void deleteByIds(BasketballConfigDTO basketballConfigDTO) {
        BasketballConfigPKDAO basketballConfigPKDAO = new BasketballConfigPKDAO();
        basketballConfigPKDAO.setCompetition(basketballConfigDTO.getCompetition());
        basketballConfigPKDAO.setCountry(basketballConfigDTO.getCountry());
        basketballConfigPKDAO.setSeasson(basketballConfigDTO.getSeasson());
        try {
            // Perform a findById operation
            Optional<BasketballConfigDAO> optionalConfigDAO = basketballRepository.findById(basketballConfigPKDAO);

            // Check if the entity exists before attempting deletion
            if (optionalConfigDAO.isPresent()) {
                // Your existing logic for deletion
                basketballRepository.deleteById(basketballConfigPKDAO);
            } else {
                // Log the exception and rethrow or handle as needed
                throw new MyEntityNotFoundException("Configuration not found for " + basketballConfigPKDAO);
            }
        } catch (MyEntityNotFoundException e) {
            // Log the exception and rethrow or handle as needed
            log.error("MyEntityNotFoundException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Log the exception and rethrow or handle as needed
            log.error("Exception during deleteByIds: {}", e.getMessage());
            throw new RuntimeException("Error deleting configuration", e);
        }
    }




    @Override
    public List<BasketballConfigDTO> saveAll(List<BasketballConfigDAO> basketballConfigDTOList) {
        log.info("Saving list of configs...");
        return basketballMapper.mapListToDTO(basketballRepository.saveAll(basketballConfigDTOList));
    }

    @Override
    public BasketballConfigDTO findById(BasketballConfigDTO basketballConfigDTO) {
        BasketballConfigPKDAO basketballConfigPKDAO = new BasketballConfigPKDAO();
        basketballConfigPKDAO.setCompetition(basketballConfigDTO.getCompetition());
        basketballConfigPKDAO.setCountry(basketballConfigDTO.getCountry());
        basketballConfigPKDAO.setSeasson(basketballConfigDTO.getSeasson());
        Optional<BasketballConfigDAO> optionalFixturesDAO = basketballRepository.findById(basketballConfigPKDAO);
        // Use orElseThrow to throw EntityNotFoundException if the entity is not present
        BasketballConfigDAO basketballConfigDAO = optionalFixturesDAO.orElseThrow(() ->
                new MyEntityNotFoundException("Fixtures with matchId " + basketballConfigPKDAO + " not found"));
        // Map the FixturesDAO to a FixturesDTO and return it
        return basketballMapper.mapToDTO(basketballConfigDAO);
    }

    @Override
    public List<BasketballConfigDTO> updateAll(List<BasketballConfigDAO> basketballConfigDAOS) {
        return basketballMapper.mapListToDTO(basketballRepository.saveAll(basketballConfigDAOS));
    }


}
