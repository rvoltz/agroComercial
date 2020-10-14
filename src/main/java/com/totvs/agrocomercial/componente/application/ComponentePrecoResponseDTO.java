package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.ResponseDTO;
import com.totvs.agrocomercial.componente.domain.utils.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
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
    private List<EnumItem> itens;
    private List<EnumFinalidade> finalidades;
    private List<EnumTipoFrete> tiposfrete;

    public ComponentePrecoResponseDTO(UUID id, String codigo, String descricao, EnumUnidadeMedida unidadeMedida, EnumMoeda moeda,
                                      EnumTipo tipo, EnumTabelaPreco tabelaPreco, EnumAplicacao aplicacao, boolean hedge, boolean ativo, List<EnumItem> itens, List<EnumFinalidade> finalidades, List<EnumTipoFrete> tiposFrete) {
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
        this.tiposfrete = tiposFrete;
    }

}
