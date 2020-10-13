package com.totvs.agrocomercial.componente.application;

import com.totvs.agrocomercial.commons.base.application.RequestDTO;
import com.totvs.agrocomercial.componente.domain.Finalidade;
import com.totvs.agrocomercial.componente.domain.Item;
import com.totvs.agrocomercial.componente.domain.TipoFrete;
import com.totvs.agrocomercial.componente.domain.utils.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode(of = "codigo")
@ToString
public class ComponentePrecoRequestDTO implements RequestDTO {
    private String codigo;
    private String descricao;
    private String codigoExterno;
    private EnumUnidadeMedida unidadeMedida;
    private EnumMoeda enumMoeda;
    private EnumTipo tipo;
    private EnumAplicacao enumAplicacao;
    private EnumTabelaPreco tabelaPreco;
    private boolean hedge;
    private boolean ativo;
    private List<Finalidade> finalidades;
    private List<TipoFrete> tiposFrete;
    private List<ItemComponentePrecoDTO> componentes;
    private List<Item> itens;


    public ComponentePrecoRequestDTO(String codigo, String descricao, EnumUnidadeMedida unidadeMedida, EnumMoeda enumMoeda,
                                     String codigoExterno, EnumTipo tipo, EnumTabelaPreco tabelaPreco, EnumAplicacao enumAplicacao, boolean ativo, boolean hedge, List<Finalidade> finalidades, List<TipoFrete> tiposFrete, List<ItemComponentePrecoDTO> componentes, List<Item> itens ) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.enumMoeda = enumMoeda;
        this.codigoExterno = codigoExterno;
        this.tipo = tipo;
        this.tabelaPreco = tabelaPreco;
        this.enumAplicacao = enumAplicacao;
        this.ativo = ativo;
        this.hedge = hedge;
        this.finalidades = finalidades;
        this.tiposFrete = tiposFrete;
        this.componentes = componentes;
        this.itens = itens;
    }


}
