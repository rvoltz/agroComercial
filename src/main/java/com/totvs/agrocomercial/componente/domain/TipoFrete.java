package com.totvs.agrocomercial.componente.domain;

import com.totvs.agrocomercial.commons.base.domain.EntityBase;
import com.totvs.agrocomercial.componente.domain.utils.EnumTipoFrete;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "tipo_frete")
public class TipoFrete implements EntityBase {

    @Id
    @GeneratedValue
    @Setter
    @Column(name="ID")
    private UUID id;

    @Enumerated
    private EnumTipoFrete tipoFrete;


}
