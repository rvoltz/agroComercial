package com.totvs.agrocomercial.commons.base.application;

import com.totvs.agrocomercial.commons.base.documentation.DefaultResponseDocumentation;
import com.totvs.agrocomercial.commons.base.domain.EntityBase;
import com.totvs.agrocomercial.commons.base.domain.IService;
import com.totvs.agrocomercial.exceptions.RecordNotFoundException;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.QueryParam;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
public interface AbstractCrudController<ENTITY extends EntityBase, REQUEST extends RequestDTO, RESPONSE extends ResponseDTO> {

    IService<ENTITY> service();

    default Optional<EntityDTOAssembler<ENTITY, REQUEST, RESPONSE>> assembler() {
        return Optional.empty();
    }

    @PostMapping
    @DefaultResponseDocumentation
    @ApiOperation("Create an item")
    default ResponseEntity<RESPONSE> create(@RequestBody REQUEST item) {
        ENTITY entity = entityFromDTO(item);
        ENTITY created = service().save(entity);
        RESPONSE dto = dtoFromEntity(created);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/")
                        .path(created.getIdAsString()).build().toUri())
                .body(dto);
    }

    @GetMapping(path = "/{id}")
    @DefaultResponseDocumentation
    @ApiOperation("Retrieve an item by ID")
    default ResponseEntity<RESPONSE> findBy(@PathVariable String id) {
        ENTITY entity = service().findById(UUID.fromString(id))
                .orElseThrow(() -> new RecordNotFoundException(id));
        RESPONSE result = dtoFromEntity(entity);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @DefaultResponseDocumentation
    @ApiOperation("Retrieve all items")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filter", value = "Filter item.",
                    dataType = "string", paramType = "query")
    })
    default ApiCollectionResponse<RESPONSE> findAll(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort, @ApiParam(hidden = true) @QueryParam("filter") String filter) {
        ApiCollectionResponse<ENTITY> entityResult = service().findAll(field, page, sort, filter);

        List<RESPONSE> dtoResult = assembler()
                .map(assembler -> entityResult.getItems()
                        .stream()
                        .map(assembler::fromEntity)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>((Collection<? extends RESPONSE>) entityResult.getItems()));

        return ApiCollectionResponse.<RESPONSE>builder()
                .hasNext(entityResult.hasNext())
                .items(dtoResult)
                .build();
    }

    @PutMapping("/{id}")
    @DefaultResponseDocumentation
    @ApiOperation("Modify an existing item")
    default ResponseEntity<RESPONSE> update(@PathVariable String id, @RequestBody REQUEST field) {
        System.out.println("---------------------------------------------");
        System.out.println("Requisição " + field.toString());
        ENTITY entity = entityFromDTO(field);
        System.out.println("---------------------------------------------");
        System.out.println("entityFromDTO" + entity.toString());

        ENTITY modified = service().update(UUID.fromString(id), entity);
        System.out.println("---------------------------------------------");
        System.out.println("Pós-update" + modified.toString());
        System.out.println("---------------------------------------------");
        //RESPONSE dto = dtoFromEntity(modified);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @DefaultResponseDocumentation
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiOperation("Remove an existing item")
    default void delete(@PathVariable String id) {
        service().delete(UUID.fromString(id));
    }

    private RESPONSE dtoFromEntity(ENTITY entityResult) {
        final Optional<EntityDTOAssembler<ENTITY, REQUEST, RESPONSE>> assembler = assembler();

        return assembler.isPresent() ? assembler.get().fromEntity(entityResult) : (RESPONSE) entityResult;
    }

    private ENTITY entityFromDTO(@RequestBody REQUEST item) {
        final Optional<EntityDTOAssembler<ENTITY, REQUEST, RESPONSE>> assembler = assembler();
        return assembler.isPresent() ? assembler.get().fromDTO(item) : (ENTITY) item;
    }
}
