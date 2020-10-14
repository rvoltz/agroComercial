package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.EntityDTOAssembler;
import com.totvs.agrocomercial.componente.domain.ComponenteMapper;
import com.totvs.agrocomercial.componente.domain.ComponentePreco;
//import org.springframework.beans.factory.annotation.Autowired;

public class ComponentePrecoAssembler implements EntityDTOAssembler<ComponentePreco, ComponentePrecoRequestDTO, ComponentePrecoResponseDTO> {

    //private final ComponenteMapper componenteMapper = ComponenteMapper.INSTANCE;

    @Override
    public ComponentePrecoResponseDTO fromEntity(ComponentePreco entity) {
        //System.out.println(componenteMapper.toDTO(entity));
        return new ComponentePrecoResponseDTO(entity.getId(), entity.getCodigo(), entity.getDescricao());
    }

    @Override
    public ComponentePreco fromDTO(ComponentePrecoRequestDTO dto) {
        //System.out.println(componenteMapper.toModel(dto));
        return new ComponentePreco(dto.getCodigo(), dto.getDescricao(), dto.getCodigoExterno(), dto.getUnidadeMedida(), dto.getEnumMoeda(),
                dto.getTipo(), dto.getTabelaPreco(), dto.getEnumAplicacao(), dto.isHedge(), dto.isAtivo(), dto.getFinalidades(), dto.getTiposFrete(), dto.getComponentes(), dto.getItens());

    }
}
