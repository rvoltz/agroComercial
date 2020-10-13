package com.totvs.agrocomercial.componente.domain;

import com.totvs.agrocomercial.commons.base.domain.AbstractCrudRepository;
import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComponentePrecoRepository extends AbstractCrudRepository<ComponentePreco>, JpaRepository<ComponentePreco, UUID>, ApiJpaRepository<ComponentePreco> {
    Page<ComponentePreco> findByCodigoLikeOrDescricaoIgnoreCaseLike(String codigo, String descricao, Pageable pageable);
}
