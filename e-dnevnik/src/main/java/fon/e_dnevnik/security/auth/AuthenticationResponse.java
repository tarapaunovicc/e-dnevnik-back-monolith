package fon.e_dnevnik.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String refreshToken;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String role;

}
