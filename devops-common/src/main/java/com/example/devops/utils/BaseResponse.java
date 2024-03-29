package com.example.devops.utils;

import com.example.devops.utils.exception.ApplicationException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseResponse<T> {
    private Meta meta;
    private T data;

    public BaseResponse(T data) {
        this.meta = new Meta();
        this.data = data;
    }

    public BaseResponse(String message) {
        this.meta = new Meta(message);
    }

    public BaseResponse(String message, T data) {
        this.meta = new Meta(message);
        this.data = data;
    }

    public BaseResponse(ApplicationException applicationException) {
        this.meta = new Meta(applicationException.getErrorCode(), applicationException.getMessage());
    }

    public static <T> ResponseEntity<?> success(T body) {
        return ResponseEntity.ok(new BaseResponse<T>(body));
    }

    public static <T> ResponseEntity<?> success(String message, T body) {
        return ResponseEntity.ok(new BaseResponse<>(message, body));
    }

    public static <T> ResponseEntity<?> error(ApplicationException applicationException) {
        BaseResponse<T> baseResponse = new BaseResponse<>(applicationException);
        return ResponseEntity.status(baseResponse.getMeta().getStatus()).body(baseResponse);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @AllArgsConstructor
    @Getter
    @Setter
    private static class Meta {
        private Integer code;
        @JsonIgnore
        private int status;
        private String message;

        public Meta() {
            this.code = 200;
        }

        public Meta(String message) {
            this.code = 200;
            this.message = message;
        }

        public Meta(Integer errorCode, String message) {
            this.status = (errorCode == null) ? 200 : (errorCode / 100000);
            this.code = errorCode;
            this.message = message;
        }
    }
}
