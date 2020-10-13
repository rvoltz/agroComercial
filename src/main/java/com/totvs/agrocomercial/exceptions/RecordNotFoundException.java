package com.totvs.agrocomercial.exceptions;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;
import com.totvs.tjf.api.context.stereotype.error.ApiNotFound;
import lombok.Getter;

@ApiNotFound(value = "RecordNotFound")
public class RecordNotFoundException extends RuntimeException  {

    @Getter
    @ApiErrorParameter
    private final String id;

    public RecordNotFoundException(String id) {
        this.id = id;
    }

}

