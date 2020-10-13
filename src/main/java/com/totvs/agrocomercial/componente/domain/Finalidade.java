package com.totvs.agrocomercial.componente.domain;

import com.totvs.agrocomercial.commons.base.domain.EntityBase;
import com.totvs.agrocomercial.componente.domain.utils.EnumFinalidade;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "finalidade")
public class Finalidade implements EntityBase  {

    @Id
    @GeneratedValue
    @Setter
    @Column(name="ID_FINALIDADE")
    private UUID id;

    @Enumerated
    private EnumFinalidade finalidade;


}
