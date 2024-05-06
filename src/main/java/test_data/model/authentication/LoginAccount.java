package test_data.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginAccount {
    private String email;
    private String password;
}
