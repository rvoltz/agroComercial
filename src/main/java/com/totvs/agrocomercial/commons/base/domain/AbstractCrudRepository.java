package com.totvs.agrocomercial.commons.base.domain;

import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AbstractCrudRepository<ENTITY extends EntityBase> extends JpaRepository<ENTITY, UUID>, ApiJpaRepository<ENTITY> {
}
