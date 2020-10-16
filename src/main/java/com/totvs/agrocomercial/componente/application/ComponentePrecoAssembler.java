package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.EntityDTOAssembler;
import com.totvs.agrocomercial.componente.domain.ComponenteMapper;
import com.totvs.agrocomercial.componente.domain.ComponentePreco;
import com.totvs.agrocomercial.componente.domain.utils.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ComponentePrecoAssembler implements EntityDTOAssembler<ComponentePreco, ComponentePrecoRequestDTO, ComponentePrecoResponseDTO> {

    public ComponentePrecoAssembler() {
        super();
    }

    @Override
    public ComponentePrecoResponseDTO fromEntity(ComponentePreco entity) {
//        ComponentePrecoResponseDTO componentePrecoResponseDTO2 = ComponenteMapper.INSTANCE.toDTO(entity);
//        System.out.println(componentePrecoResponseDTO2.toString());
        //return componentePrecoResponseDTO2;

        return new ComponentePrecoResponseDTO(entity.getId(), entity.getCodigo(), entity.getDescricao(),
                entity.getUnidadeMedida(), entity.getMoeda(),
                entity.getTipo(), entity.getTabelaPreco(), entity.getAplicacao(), entity.isHedge(), entity.isAtivo(), entity.getItens(), entity.getFinalidades(), entity.getTiposFrete(), entity.getComponentes(), entity.getCodigoExterno());
    }

    @Override
    public ComponentePreco fromDTO(ComponentePrecoRequestDTO dto) {
//        ComponentePreco componentePreco2 = ComponenteMapper.INSTANCE.toModel(dto);
//        System.out.println(componentePreco2.toString());
//        return componentePreco2;

        return new ComponentePreco(dto.getCodigo(), dto.getDescricao(), dto.getCodigoExterno(), dto.getUnidadeMedida(), dto.getMoeda(),
                dto.getTipo(), dto.getTabelaPreco(), dto.getAplicacao(), dto.isHedge(), dto.isAtivo(), dto.getFinalidades(), dto.getTiposFrete(), dto.getItens(), dto.getIdsComponentes());

    }
}
