package com.totvs.agrocomercial.commons.base.documentation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.totvs.agrocomercial.commons.base.documentation.DefaultMessagesHttpStatus.*;

@ApiResponses(value = {@ApiResponse(code = 200, message = DefaultMessagesHttpStatus.OPERATION_COMPLETED_SUCCESSFULLY),
        @ApiResponse(code = 500, message = UNEXPECTED_SYSTEM_FAILURE),
        @ApiResponse(code = 401, message = AUTHENTICATION_FAILURE_UNAUTHENTICATED_REQUEST),
        @ApiResponse(code = 400, message = FAILED_TO_VALIDATE_BUSINESS_RULES),
        @ApiResponse(code = 403, message = AUTHORIZATION_FAILURE_UNAUTHORIZED_REQUEST),
        @ApiResponse(code = 406, message = UNSUPPORTED_CONTENT_TYPE)})
@ResponseStatus(code = HttpStatus.OK)
@Retention( RetentionPolicy.SOURCE )
@Target( ElementType.METHOD )
public @interface DefaultResponseDocumentation {
}
