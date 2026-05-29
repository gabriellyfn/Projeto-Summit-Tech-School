package br.com.summit.school.infra.exception.exceptionAcesso;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

//Criando o Formato Json
public class ErrorResponse{

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
         private LocalDateTime timestamp;
         private int status;
         private String error;
         private String message;
         private String path;

         public ErrorResponse(LocalDateTime timestamp, int status,String error,String message,String path) {
             this.timestamp = timestamp;
             this.status = status;
             this.error = error;
             this.message = message;
             this.path = path;
         }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
