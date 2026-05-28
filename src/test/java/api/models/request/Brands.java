package api.models.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brands {

    private int id;
    private String name;

}
