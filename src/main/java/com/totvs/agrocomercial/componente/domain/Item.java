package com.totvs.agrocomercial.componente.domain;

import com.totvs.agrocomercial.commons.base.domain.EntityBase;
import com.totvs.agrocomercial.componente.domain.utils.EnumItem;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "item")
public class Item implements EntityBase {

    @Id
    @GeneratedValue
    @Setter
    @Column(name="ID_ITEM")
    private UUID id;

    @Enumerated
    private EnumItem item;

}
