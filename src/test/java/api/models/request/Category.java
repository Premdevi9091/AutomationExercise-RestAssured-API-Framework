package api.models.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Category {

    private UserType usertype;
    private String category;

}
