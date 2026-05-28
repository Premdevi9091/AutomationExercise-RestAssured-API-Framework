package api.utils.PayloadBuilder;

import api.models.request.SearchProductRequest;

import java.util.List;
import java.util.Random;

public class SearchProductsBuilder {

    private static final Random random = new Random();

    private static final List<String> SEARCH_TERMS = List.of(
            "top", "tshirt", "jean", "dress", "shirt"
    );

    public static SearchProductRequest buildSearchProduct(){

        return SearchProductRequest.builder()
                .search_product(SEARCH_TERMS.get((random.nextInt(SEARCH_TERMS.size()))))
                .build();

    }
}
