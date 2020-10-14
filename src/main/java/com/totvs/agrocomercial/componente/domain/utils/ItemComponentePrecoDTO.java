package com.totvs.agrocomercial.componente.domain.utils;

import com.totvs.agrocomercial.commons.base.application.ResponseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class ItemComponentePrecoDTO implements ResponseDTO {

    @Id
    @GeneratedValue
    @Setter
    @Column(name="ID")
    private UUID id;
    private String codigo;
    private String descricao;

    public ItemComponentePrecoDTO(UUID id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
