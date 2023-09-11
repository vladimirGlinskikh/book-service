package com.kado.kpbookservice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageDto {
    String message;
    String details;
    List<Error> fieldErrors;

    @Data
    public static class Error {
        private String field;
        private String message;
    }
}
