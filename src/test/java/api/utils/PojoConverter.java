package api.utils;

import api.models.request.Brands;
import api.models.request.Product;
import api.models.request.SearchProductRequest;
import api.models.request.Users;

import java.util.HashMap;
import java.util.Map;

public class PojoConverter {

    private PojoConverter() {}

    public static Map<String, String> userConvertToMap(Users user){

        Map<String, String> data = new HashMap<>();

        data.put("name", user.getName());
        data.put("email", user.getEmail());
        data.put("password", user.getPassword());

        data.put("title", user.getTitle());
        data.put("birth_date", user.getBirth_date());
        data.put("birth_month", user.getBirth_month());
        data.put("birth_year", user.getBirth_year());

        data.put("firstname", user.getFirstname());
        data.put("lastname", user.getLastname());

        data.put("company", user.getCompany());
        data.put("address1", user.getAddress1());
        data.put("address2", user.getAddress2());
        data.put("country", user.getCountry());
        data.put("zipcode", user.getZipcode());
        data.put("state", user.getState());
        data.put("city", user.getCity());

        data.put("mobile_number", user.getMobile_number());

        return data;

    }

    public static Map<String, String> productConvertToMap(Product product) {

        Map<String, String> data = new HashMap<>();

        data.put("id", String.valueOf(product.getId()));
        data.put("name", product.getName());
        data.put("price", product.getPrice());

        data.put("brand", product.getBrands());
        data.put("usertype", product.getCategory().getUsertype().getUsertype());
        data.put("category", product.getCategory().getCategory());

        return data;
    }

    public static Map<String, String> brandsConvertToMap(Brands brand) {

        Map<String, String> data = new HashMap<>();

        data.put("id", String.valueOf(brand.getId()));
        data.put("brand", brand.getName());

        return data;
    }

    public static Map<String, String> searchProductConvertToMap(SearchProductRequest search){

        Map<String, String> data = new HashMap<>();

        data.put("search_product", search.getSearch_product());

        return data;
    }

    // Used for login, delete account, and invalid login (all need email + password)
    public static Map<String, String> userLoginConvertToMap(Users user){

        Map<String, String> data = new HashMap<>();

        data.put("email", user.getEmail());
        data.put("password", user.getPassword());

        return data;
    }

    // For the "missing email" negative test — sends only the password field
    public static Map<String, String> userLoginWithPasswordConvertToMap(Users user){

        Map<String, String> data = new HashMap<>();

        data.put("password", user.getPassword());

        return data;
    }

    // For GET user detail by email — sends only the email as a query param
    public static Map<String, String> userEmailConvertToMap(Users user){

        Map<String, String> data = new HashMap<>();

        data.put("email", user.getEmail());

        return data;
    }
}
