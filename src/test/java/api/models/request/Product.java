package api.models.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    private int id;
    private String name;
    private String price;
    private String brands;
    private Category category;

}
