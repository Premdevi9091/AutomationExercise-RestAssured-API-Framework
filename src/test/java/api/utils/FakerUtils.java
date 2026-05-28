package api.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

public class FakerUtils {

    public static final Faker faker = new Faker();

    public static String getFirstName(){
        return faker.name().firstName();
    }

    public static String getLastName(){
        return faker.name().lastName();
    }

    public static String getEmail(){
        return faker.internet().emailAddress();
    }

    public static String getPassword(){
        return faker.internet().password(8, 12, true, true);
    }

    public static String getCompany(){
        return faker.company().name();
    }

    public static String getAddress(){
        return faker.address().streetAddress();
    }

    public static String getCity(){
        return faker.address().city();
    }

    public static String getState(){
        return faker.address().state();
    }

    public static String getZipCode(){
        return faker.address().zipCode();
    }

    public static String getPhoneNumber(){
        return faker.phoneNumber().cellPhone();
    }

    public static int getId(){
        return faker.number().numberBetween(1, 50);
    }

    public static String getProductName(){
        return faker.commerce().productName();
    }

    public static String getProductPrice(){
        return "Rs. " + faker.number().numberBetween(100, 5000);
    }
}
