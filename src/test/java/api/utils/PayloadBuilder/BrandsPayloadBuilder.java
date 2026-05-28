package api.utils.PayloadBuilder;

import api.models.request.Brands;
import api.models.request.Users;
import api.utils.FakerUtils;

public class BrandsPayloadBuilder {

    public static Brands brandsPayload(){

        return Brands.builder()
                .id(FakerUtils.getId())
                .name(FakerUtils.getProductName())
                .build();

    }
}
