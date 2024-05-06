package test_data.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private String name;
    private int price;
    private int quality;
}
