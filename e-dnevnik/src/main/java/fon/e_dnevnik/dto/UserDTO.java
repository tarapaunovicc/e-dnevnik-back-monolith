package fon.e_dnevnik.dto;

import fon.e_dnevnik.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Data
public class UserDTO {

    private String username;

    private String password;

    private Role role;

}
