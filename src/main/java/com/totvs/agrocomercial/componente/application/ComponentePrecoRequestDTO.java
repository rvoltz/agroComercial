package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.RequestDTO;
import com.totvs.agrocomercial.componente.domain.ComponentePreco;
import com.totvs.agrocomercial.componente.domain.utils.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "codigo")
@ToString
@NoArgsConstructor
public class ComponentePrecoRequestDTO implements RequestDTO {
    private String codigo;
    private String descricao;
    private String codigoExterno;
    private EnumUnidadeMedida unidadeMedida;
    private EnumMoeda moeda;
    private EnumTipo tipo;
    private EnumAplicacao aplicacao;
    private EnumTabelaPreco tabelaPreco;
    private boolean hedge;
    private boolean ativo;
    private Set<EnumFinalidade> finalidades;
    private Set<EnumTipoFrete> tiposFrete;
    private List<ItemComponentePrecoDTO> idsComponentes;
    private Set<EnumItem> itens;

    public ComponentePrecoRequestDTO(String codigo, String descricao, EnumUnidadeMedida unidadeMedida, EnumMoeda moeda,
                                     String codigoExterno, EnumTipo tipo, EnumTabelaPreco tabelaPreco, EnumAplicacao aplicacao, boolean ativo, boolean hedge, Set<EnumFinalidade> finalidades, Set<EnumTipoFrete> tiposFrete, Set<EnumItem> itens, List<ItemComponentePrecoDTO> idsComponentes ) {

        this.codigo = codigo;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.moeda = moeda;
        this.codigoExterno = codigoExterno;
        this.tipo = tipo;
        this.tabelaPreco = tabelaPreco;
        this.aplicacao = aplicacao;
        this.ativo = ativo;
        this.hedge = hedge;
        this.finalidades = finalidades;
        this.tiposFrete = tiposFrete;
        this.idsComponentes = idsComponentes;
        this.itens = itens;
    }


}
