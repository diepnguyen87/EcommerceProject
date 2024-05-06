package test_data.model.product_item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductItem {
    private String name;
    private String price;
}
