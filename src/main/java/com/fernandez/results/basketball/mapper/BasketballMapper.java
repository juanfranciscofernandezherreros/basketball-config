package com.fernandez.results.basketball.mapper;

import com.fernandez.results.basketball.dao.BasketballConfigDAO;
import com.fernandez.results.basketball.dto.BasketballConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasketballMapper {

    BasketballMapper INSTANCE = Mappers.getMapper(BasketballMapper.class);

    @Mapping(source = "basketballConfigPKDAO.country", target = "country")
    @Mapping(source = "basketballConfigPKDAO.competition", target = "competition")
    @Mapping(source = "basketballConfigPKDAO.seasson", target = "seasson")
    BasketballConfigDTO mapToDTO(BasketballConfigDAO basketballConfigDAO);

    List<BasketballConfigDTO> mapListToDTO(List<BasketballConfigDAO> basketballConfigDAOS);

    @Mapping(source = "country", target = "basketballConfigPKDAO.country")
    @Mapping(source = "competition", target = "basketballConfigPKDAO.competition")
    @Mapping(source = "seasson", target = "basketballConfigPKDAO.seasson")
    BasketballConfigDAO mapToDAO(BasketballConfigDTO basketballConfigDTO);

    List<BasketballConfigDAO> mapListToDAO(List<BasketballConfigDTO> basketballConfigDTOList);

    default Page<BasketballConfigDTO> mapToPageDTO(Page<BasketballConfigDAO> basketballConfigDAOPage) {
        List<BasketballConfigDTO> basketballConfigDTOList = mapListToDTO(basketballConfigDAOPage.getContent());
        return new PageImpl<>(basketballConfigDTOList, basketballConfigDAOPage.getPageable(), basketballConfigDAOPage.getTotalElements());
    }
}
