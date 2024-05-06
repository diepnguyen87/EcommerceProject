package test_data.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingAddress {
    private String phoneNum;
    private String address1;
    private String address2;
    private String city;
    private String zipCode;
    private String country;
}
