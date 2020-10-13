package com.totvs.agrocomercial.commons.base.application;


import com.totvs.agrocomercial.commons.base.domain.EntityBase;

public interface EntityDTOAssembler<ENTITY extends EntityBase, REQUEST extends RequestDTO, RESPONSE extends ResponseDTO> {
    RESPONSE fromEntity(ENTITY entity);
    ENTITY fromDTO(REQUEST dto);
}
