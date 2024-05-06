package test_data.model.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationAccount {
    private String email;
    private String name;
    private String phone;
    private String password;
}
