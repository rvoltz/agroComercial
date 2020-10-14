package com.totvs.agrocomercial.componente.domain;

import com.totvs.agrocomercial.componente.application.ComponentePrecoRequestDTO;
import com.totvs.agrocomercial.componente.application.ComponentePrecoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComponenteMapper {

    ComponenteMapper INSTANCE = Mappers.getMapper(ComponenteMapper.class);

    ComponentePreco toModel(ComponentePrecoRequestDTO componentePrecoRequestDTO);

    ComponentePrecoResponseDTO toDTO(ComponentePreco componentePreco);

}
