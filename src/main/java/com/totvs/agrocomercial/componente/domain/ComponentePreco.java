package com.totvs.agrocomercial.componente.domain;

import com.totvs.agrocomercial.commons.base.domain.EntityBase;
import com.totvs.agrocomercial.componente.domain.utils.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "componente_preco")
public class ComponentePreco implements EntityBase  {

    @Id
    @GeneratedValue
    @Setter
    @Column(name="ID")
    private UUID id;

    @Column(name="CODIGO")
    private String codigo;

    @Column(name="DESCRICAO")
    private String descricao;

    @Column(name="CODIGO_EXTERNO")
    private String codigoExterno;

    @Column(name="UNIDADE_MEDIDA")
    private EnumUnidadeMedida unidadeMedida;

    @Column(name="MOEDA")
    private EnumMoeda moeda;

    @Column(name="TIPO")
    private EnumTipo tipo;

    @Column(name="TABELA_PRECO")
    private EnumTabelaPreco tabelaPreco;

    @Column(name="APLICACAO")
    private EnumAplicacao aplicacao;

    @Column(name="HEDGE")
    private boolean hedge;

    @Column(name="ATIVO")
    private boolean ativo;

    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Item> itens;

    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Finalidade> finalidades;

    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<TipoFrete> tiposFrete;

    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<ItemComponentePrecoDTO> componentes;

    public ComponentePreco(String codigo, String descricao, String codigoExterno, EnumUnidadeMedida unidadeMedida, EnumMoeda enumMoeda,
                           EnumTipo tipo, EnumTabelaPreco tabelaPreco, EnumAplicacao aplicacao, boolean hedge, boolean ativo, List<Finalidade> finalidades, List<TipoFrete> tiposFrete, List<ItemComponentePrecoDTO> componentes, List<Item> itens){
        this.codigo = codigo;
        this.descricao = descricao;
        this.codigoExterno = codigoExterno;
        this.unidadeMedida = unidadeMedida;
        this.moeda = enumMoeda;
        this.tipo = tipo;
        this.tabelaPreco = tabelaPreco;
        this.aplicacao = aplicacao;
        this.ativo = ativo;
        this.hedge = hedge;
        this.finalidades = finalidades;
        this.tiposFrete = tiposFrete;
        this.componentes = componentes;
        this.itens = itens;
    }

}
