package api.models.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SearchProductRequest {

    private String search_product;

}
