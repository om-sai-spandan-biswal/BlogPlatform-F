package com.om.projects_F.backend.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private T data ;
    private ApiException error ;
    @JsonFormat(pattern = "ss:mm:hh DD-MM-YYYY")
    private LocalDateTime timeStamp ;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now() ;
    }

    public ApiResponse(T data) {
        this() ;
        this.data = data ;
    }

    public ApiResponse(ApiException apiException) {
        this() ;
        this.error = apiException ;
    }
}
