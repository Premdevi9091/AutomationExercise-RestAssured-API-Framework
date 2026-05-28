package api.utils;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProductDataGenerator {

    private  static final Random random = new Random();

    private static  final List<String> brands = List.of(
            "Polo",
            "H&M",
            "Madame",
            "Mast & Harbour",
            "Babyhug",
            "Allen Solly Junior",
            "Cookie Kids",
            "Biba"
    );

    private static final Map<String, List<String>> cateogryMap =
            Map.of(
                    "Women",
                    List.of("Dress", "Tops", "Saree"),
                    "Men",
                    List.of("Tshirts", "Jeans"),
                    "Kids",
                    List.of("Dress", "Tops & Shirts")
            );

    public static String getRandomBrand(){
        return brands.get(random.nextInt(brands.size()));
    }


    public static String getRandomUserType(){
        List<String> userTypes = List.of("Women", "Men", "Kids");
        return userTypes.get(random.nextInt(userTypes.size()));
    }

    public static String getRandomCategory(String userType){
        List<String> categories = cateogryMap.get(userType);

        return categories.get(random.nextInt(categories.size()));
    }
}
