package com.fernandez.results.basketball.controller;

import com.fernandez.results.basketball.dao.BasketballConfigPKDAO;
import com.fernandez.results.basketball.dto.BasketballConfigDTO;
import com.fernandez.results.basketball.entity.MyEntityNotFoundException;
import com.fernandez.results.basketball.service.BasketballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/basketball/config")
public class BasketballController {

    @Autowired
    private BasketballService basketballService;

    @GetMapping
    public ResponseEntity<Page<BasketballConfigDTO>> findAllByDynamicCriteria(@RequestParam Map<String, String> params, @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {
        Page<BasketballConfigDTO> partidos = basketballService.findAllByDynamicCriteria(params, page, size);
        return new ResponseEntity<>(partidos, HttpStatus.OK);
    }

    @GetMapping("/{country}/{competition}/{season}")
    public ResponseEntity<?> findById(
            @PathVariable("country") String country,
            @PathVariable("competition") String competition,
            @PathVariable("season") String season) {
        try {
            BasketballConfigDTO basketballConfigDTO = new BasketballConfigDTO();
            basketballConfigDTO.setCountry(country);
            basketballConfigDTO.setCompetition(competition);
            basketballConfigDTO.setSeasson(season);
            BasketballConfigDTO config = basketballService.findConfigById(basketballConfigDTO);
            return new ResponseEntity<>(config, HttpStatus.OK);
        } catch (MyEntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{country}/{competition}/{seasson}")
    public ResponseEntity<String> deleteById(@PathVariable("country") String country,
                                             @PathVariable("competition") String competition,
                                             @PathVariable("seasson") String seasson) {
        try {
            BasketballConfigDTO basketballConfigDTO = new BasketballConfigDTO();
            basketballConfigDTO.setCountry(country);
            basketballConfigDTO.setCompetition(competition);
            basketballConfigDTO.setSeasson(seasson);
            basketballService.deleteById(basketballConfigDTO);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (MyEntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping
    public ResponseEntity<List<BasketballConfigDTO>> saveAll(@RequestBody List<BasketballConfigDTO> fixturesList) {
        List<BasketballConfigDTO> fixturesDTOList = basketballService.saveAll(fixturesList);
        return new ResponseEntity<List<BasketballConfigDTO>>(fixturesDTOList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<BasketballConfigDTO>> updateAll(@RequestBody List<BasketballConfigDTO> fixturesList) {
        List<BasketballConfigDTO> fixturesDTOList = basketballService.updateAll(fixturesList);
        return new ResponseEntity<List<BasketballConfigDTO>>(fixturesDTOList, HttpStatus.OK);
    }




}