package com.totvs.agrocomercial.commons.base.domain;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IService<T extends EntityBase> {

    AbstractCrudRepository<T> repository();

    Optional<T> findById(UUID id);
    ApiCollectionResponse<T> findAll(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sortRequest, String filter);
    Page<T> findFiltered(Pageable pageable, String filter);

    void beforeSave(T entity);
    void afterSave(T entity);
    void afterUpdate(T entity);
    void beforeDelete(T entity);

    T save(T entity);
    T update(UUID id, T entity);
    void delete(UUID id);

}
