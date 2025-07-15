package fon.e_dnevnik.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /**
     * Value of HTTP status of exception. Can be NOT_FOUND,
     * BAD_REQUEST etc.
     */
    private int status;

    /**
     * Map of message that exception throw.
     */
    private Map<String,String> message;

    /**
     * Time when exception occurred in seconds.
     */
    private long timeStamp;
}