package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.EntityDTOAssembler;
import com.totvs.agrocomercial.componente.domain.ComponenteMapper;
import com.totvs.agrocomercial.componente.domain.ComponentePreco;
import com.totvs.agrocomercial.componente.domain.utils.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ComponentePrecoAssembler implements EntityDTOAssembler<ComponentePreco, ComponentePrecoRequestDTO, ComponentePrecoResponseDTO> {

    //@Autowired
    //private final ComponenteMapper componenteMapper = ComponenteMapper.INSTANCE;


    public ComponentePrecoAssembler() {
        super();
    }

    @Override
    public ComponentePrecoResponseDTO fromEntity(ComponentePreco entity) {
        //System.out.println(componenteMapper.toDTO(entity));
        return new ComponentePrecoResponseDTO(entity.getId(), entity.getCodigo(), entity.getDescricao(),
                entity.getUnidadeMedida(), entity.getMoeda(),
                entity.getTipo(), entity.getTabelaPreco(), entity.getAplicacao(), entity.isHedge(), entity.isAtivo(), entity.getItens(), entity.getFinalidades(), entity.getTiposFrete(), entity.getComponentes());
    }

    @Override
    public ComponentePreco fromDTO(ComponentePrecoRequestDTO dto) {
        //System.out.println(componenteMapper.toModel(dto));
        return new ComponentePreco(dto.getCodigo(), dto.getDescricao(), dto.getCodigoExterno(), dto.getUnidadeMedida(), dto.getMoeda(),
                dto.getTipo(), dto.getTabelaPreco(), dto.getAplicacao(), dto.isHedge(), dto.isAtivo(), dto.getFinalidades(), dto.getTiposFrete(), dto.getItens(), dto.getIdsComponentes());

    }
}
