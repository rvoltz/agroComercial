package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.AbstractCrudController;
import com.totvs.agrocomercial.commons.base.application.EntityDTOAssembler;
import com.totvs.agrocomercial.commons.base.domain.IService;
import com.totvs.agrocomercial.componente.domain.ComponentePreco;
import com.totvs.agrocomercial.componente.domain.ComponentePrecoService;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/componente")
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.v1)
@CrossOrigin
public class ComponentePrecoController implements AbstractCrudController<ComponentePreco, ComponentePrecoRequestDTO, ComponentePrecoResponseDTO> {
    @Autowired
    private ComponentePrecoService service;

    @Override
    public IService<ComponentePreco> service() {
        return service;
    }

    @Override
    public Optional<EntityDTOAssembler<ComponentePreco, ComponentePrecoRequestDTO,ComponentePrecoResponseDTO>> assembler() {
         return Optional.of(new ComponentePrecoAssembler());
    }
//Wg
}
