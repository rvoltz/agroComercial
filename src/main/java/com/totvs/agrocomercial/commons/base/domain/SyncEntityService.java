package com.totvs.agrocomercial.commons.base.domain;

import com.google.common.base.Strings;
import com.totvs.agrocomercial.exceptions.ApiConstraintViolationException;
import com.totvs.agrocomercial.exceptions.RecordNotFoundException;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import com.totvs.tjf.api.jpa.complexfilter.ComplexFilterRepository;
import com.totvs.tjf.core.validation.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class SyncEntityService<T extends EntityBase> implements IService<T> {

    @Autowired
    private ValidatorService validator;

    @Autowired
    private ComplexFilterRepository complexFilterRepos;

    @Override
    @Transactional
    public T save(T obj){
        T persisted = abstractSave(obj);
        return persisted;
    }

    @Override
    public void beforeSave(T entity) {
    }

    @Override
    public void afterSave(T entity) {
    }

    public T abstractSave(T entity){
        validator.validate(entity).ifPresent(violations -> {
            throw new ApiConstraintViolationException(violations);
        });

        beforeSave(entity);

        entity = repository().save(entity);

        afterSave(entity);

        return entity;
    }

    @Override
    public void afterUpdate(T entity) {
    }

    @Override
    public Optional<T> findById(UUID id) {
        return repository().findById(id);
    }

    @Override
    public ApiCollectionResponse<T> findAll(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sortRequest, String filter) {
        if (Strings.isNullOrEmpty(filter)) {
            return repository().findAllProjected(field, page, sortRequest);
        } else {
            List<Sort.Order> orders = sortRequest.getOrder()
                    .stream()
                    .map(Sort.Order::by)
                    .collect(Collectors.toList());
            Sort sort = Sort.by(orders);
            PageRequest pageable = PageRequest.of(page.getPage() - 1, page.getPageSize(), sort);
            Page<T> filtered = findFiltered(pageable, "%".concat(filter).concat("%"));
            return ApiCollectionResponse.<T>builder().items(filtered.getContent())
                    .hasNext(filtered.hasNext()).build();
        }
    }

    @Override
    public Page<T> findFiltered(Pageable pageable, String filter) {
        throw new UnsupportedOperationException("Essa funcionalidade precisa ser implementada");
    }

    @Override
    @Transactional
    public T update(UUID id, T obj){
        T persisted = abstractUpdate(id, obj);
        return persisted;
    }

    public T abstractUpdate(UUID id, T entity) {
        AbstractCrudRepository<T> repository = repository();
        T oldEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id.toString()));

        entity.setId(oldEntity.getId());

        beforeSave(entity);

        entity = repository.save(entity);

        afterUpdate(entity);

        return entity;
    }

    @Override
    public void beforeDelete(T entity) {
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<T> optionalResult = findById(id);
        T obj = optionalResult.orElseThrow(() -> new RecordNotFoundException(id.toString()));

        abstractDelete(id);
    }

    public void abstractDelete(UUID id) {
        AbstractCrudRepository<T> repository = repository();
        final T existingEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id.toString()));
        beforeDelete(existingEntity);
        repository.delete(existingEntity);
    }
}
