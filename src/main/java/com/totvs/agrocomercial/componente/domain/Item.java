package com.totvs.agrocomercial.componente.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue
    @Setter
    @Column(name="ID")
    private UUID id;

    @Column(name="DESCRICAO")
    private String descricao;
}
