package api.utils.PayloadBuilder;

import api.models.request.Category;
import api.models.request.Product;
import api.models.request.UserType;
import api.utils.FakerUtils;
import api.utils.ProductDataGenerator;

public class ProductPayloadBuilder {

    public static Product buildProduct(){

        String userTypeStr  = ProductDataGenerator.getRandomUserType();
        String categoryStr = ProductDataGenerator.getRandomCategory(userTypeStr);

        UserType userType = UserType.builder()
                .usertype(userTypeStr)
                .build();


        Category category = Category.builder()
                        .usertype(userType)
                        .category(categoryStr)
                        .build();

        return Product.builder()
                .id(FakerUtils.getId())
                .name(FakerUtils.getProductName())
                .price(FakerUtils.getProductPrice())
                .brands(ProductDataGenerator.getRandomBrand())
                .category(category)
                .build();

    }
}
