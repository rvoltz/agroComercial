package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.EntityDTOAssembler;
import com.totvs.agrocomercial.componente.domain.ComponenteMapper;
import com.totvs.agrocomercial.componente.domain.ComponentePreco;
import org.springframework.beans.factory.annotation.Autowired;

public class ComponentePrecoAssembler implements EntityDTOAssembler<ComponentePreco, ComponentePrecoRequestDTO, ComponentePrecoResponseDTO> {

    @Autowired
    private ComponenteMapper componenteMapper;


    @Override
    public ComponentePrecoResponseDTO fromEntity(ComponentePreco entity) {
        return componenteMapper.toDTO(entity);
        //return new ComponentePrecoResponseDTO(entity.getId(), entity.getCodigo(), entity.getDescricao());
    }

    @Override
    public ComponentePreco fromDTO(ComponentePrecoRequestDTO dto) {
        return componenteMapper.toModel(dto);
//        return new ComponentePreco(dto.getCodigo(), dto.getDescricao(), dto.getCodigoExterno(), dto.getUnidadeMedida(), dto.getEnumMoeda(),
//                dto.getTipo(), dto.getTabelaPreco(), dto.getEnumAplicacao(), dto.isHedge(), dto.isAtivo(), dto.getFinalidades(), dto.getTiposFrete(), dto.getComponentes(), dto.getItens());
//
    }
}
