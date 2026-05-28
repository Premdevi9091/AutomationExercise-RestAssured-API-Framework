package api.utils.PayloadBuilder;

import api.models.request.Users;
import api.utils.FakerUtils;

public class UserPayloadBuilder {

    public static Users createUserPayload(){

        String firstName = FakerUtils.getFirstName();
        String lastName  = FakerUtils.getLastName();

        return Users.builder()
                .name(firstName)
                .email(FakerUtils.getEmail())
                .password(FakerUtils.getPassword())
                .title("Mr")
                .birth_date("10")
                .birth_month("May")
                .birth_year("1999")
                .firstname(firstName)
                .lastname(lastName)
                .company(FakerUtils.getCompany())
                .address1(FakerUtils.getAddress())
                .address2(FakerUtils.getAddress())
                .country("India")
                .zipcode(FakerUtils.getZipCode())
                .state(FakerUtils.getState())
                .city(FakerUtils.getCity())
                .mobile_number(FakerUtils.getPhoneNumber())
                .build();
    }

    public static Users createEmailPasswordPayload(String email, String password){

       return Users.builder()
               .email(email)
               .password(password)
               .build();

    }

    public static Users createLoginWithPasswordPayload(String password){

        return Users.builder()
                .password(password)
                .build();

    }

    public static Users createLoginInvalidPayload(){

        return Users.builder()
                .email(FakerUtils.getEmail())
                .password(FakerUtils.getPassword())
                .build();
    }

    public static Users createAccountUpdatePayload(Users existingUser){

        return Users.builder()
                .name(existingUser.getName())
                .email(existingUser.getEmail())
                .password(existingUser.getPassword())
                .title("Mr")
                .birth_date("10")
                .birth_month("May")
                .birth_year("1999")
                .firstname(existingUser.getFirstname())
                .lastname(existingUser.getLastname())
                .company(FakerUtils.getCompany())
                .address1(FakerUtils.getAddress())
                .address2(FakerUtils.getAddress())
                .country("India")
                .zipcode(FakerUtils.getZipCode())
                .state(FakerUtils.getState())
                .city(FakerUtils.getCity())
                .mobile_number(FakerUtils.getPhoneNumber())
                .build();

    }
}
