package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.ResponseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class ComponentePrecoResponseDTO implements ResponseDTO {

    private UUID id;
    private String codigo;
    private String descricao;

    public ComponentePrecoResponseDTO(UUID id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
