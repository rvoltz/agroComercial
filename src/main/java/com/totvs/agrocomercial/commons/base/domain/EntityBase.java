package com.totvs.agrocomercial.commons.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.UUID;

public interface EntityBase extends Serializable {
    @ApiModelProperty(hidden = true)
    UUID getId();
    void setId(UUID uuid);

    @JsonIgnore
    default String getIdAsString() {
        return getId().toString();
    }

}
