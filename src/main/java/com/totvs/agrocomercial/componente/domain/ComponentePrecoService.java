package com.totvs.agrocomercial.componente.domain;

import com.totvs.agrocomercial.commons.base.domain.AbstractCrudRepository;
import com.totvs.agrocomercial.commons.base.domain.SyncEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ComponentePrecoService extends SyncEntityService<ComponentePreco> {

    @Autowired
    private ComponentePrecoRepository repository;

    @Override
    public AbstractCrudRepository<ComponentePreco> repository() {
        return repository;
    }

    @Override
    public Page<ComponentePreco> findFiltered(Pageable pageable, String filter) {
        return repository.findByCodigoLikeOrDescricaoIgnoreCaseLike(filter, filter, pageable);
    }
}
