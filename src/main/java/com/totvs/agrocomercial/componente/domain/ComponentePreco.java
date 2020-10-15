package com.totvs.agrocomercial.componente.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.totvs.agrocomercial.commons.base.domain.EntityBase;
import com.totvs.agrocomercial.componente.domain.utils.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="item_id")
    @CollectionTable(name = "componente_preco_itens", joinColumns = @JoinColumn( name = "componente_preco_id"))
    private Set<EnumItem> itens;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="finalidades_id")
    @CollectionTable(name = "componente_preco_finalidades", joinColumns = @JoinColumn( name = "componente_preco_id"))
    private Set<EnumFinalidade> finalidades;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="tipo_frete_id")
    @CollectionTable(name = "componente_preco_tipo_fretes", joinColumns = @JoinColumn( name = "componente_preco_id") )
    private Set<EnumTipoFrete> tiposFrete;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="componente_preco_componentes", joinColumns=
            {@JoinColumn(name="componente_preco_id")}, inverseJoinColumns=
            {@JoinColumn(name="componentes_id")})
    @Setter
    @Column(name="componentes_id")
    private List<ComponentePreco> componentes;

    @Transient
    private List<ItemComponentePrecoDTO> idsComponente;

    public ComponentePreco(String codigo, String descricao, String codigoExterno, EnumUnidadeMedida unidadeMedida, EnumMoeda enumMoeda,
                           EnumTipo tipo, EnumTabelaPreco tabelaPreco, EnumAplicacao aplicacao, boolean hedge, boolean ativo, Set<EnumFinalidade> finalidades, Set<EnumTipoFrete> tiposFrete, Set<EnumItem> itens, List<ItemComponentePrecoDTO> idsComponente ){
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
        this.itens = itens;
        this.idsComponente = idsComponente;
    }

}
