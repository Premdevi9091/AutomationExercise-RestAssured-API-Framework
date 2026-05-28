package api.models.response;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private int responseCode;
    private String message;

}

