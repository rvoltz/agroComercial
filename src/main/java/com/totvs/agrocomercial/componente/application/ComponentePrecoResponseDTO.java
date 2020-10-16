package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.ResponseDTO;
import com.totvs.agrocomercial.componente.domain.ComponentePreco;
import com.totvs.agrocomercial.componente.domain.Finalidade;
import com.totvs.agrocomercial.componente.domain.Item;
import com.totvs.agrocomercial.componente.domain.TiposFrete;
import com.totvs.agrocomercial.componente.domain.utils.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
public class ComponentePrecoResponseDTO implements ResponseDTO {

    private UUID id;
    private String codigo;
    private String descricao;
    private EnumUnidadeMedida unidadeMedida;
    private EnumTipo tipo;
    private EnumMoeda moeda;
    private EnumTabelaPreco tabelaPreco;
    private EnumAplicacao aplicacao;
    private boolean hedge;
    private boolean ativo;
    private Set<Item> itens;
    private Set<Finalidade> finalidades;
    private Set<TiposFrete> tiposFrete;
    private Set<ItemComponentePrecoDTO> idsComponentes;
    private String codigoExterno;

    public ComponentePrecoResponseDTO(UUID id, String codigo, String descricao, EnumUnidadeMedida unidadeMedida, EnumMoeda moeda,
                                      EnumTipo tipo, EnumTabelaPreco tabelaPreco, EnumAplicacao aplicacao, boolean hedge, boolean ativo, Set<Item> itens, Set<Finalidade> finalidades, Set<TiposFrete> tiposFrete, Set<ComponentePreco> componentes, String codigoExterno) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.moeda = moeda;
        this.tipo = tipo;
        this.tabelaPreco = tabelaPreco;
        this.aplicacao = aplicacao;
        this.ativo = ativo;
        this.hedge = hedge;
        this.itens = itens;
        this.finalidades = finalidades;
        this.tiposFrete = tiposFrete;
        this.codigoExterno = codigoExterno;

        Set<ItemComponentePrecoDTO> idsDTO = new HashSet<ItemComponentePrecoDTO>();
        for (ComponentePreco componente : componentes){
            idsDTO.add(new ItemComponentePrecoDTO(componente.getId(), componente.getCodigo(),componente.getDescricao()));
        }

        this.idsComponentes = idsDTO;
    }

}
