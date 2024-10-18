package entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
@AllArgsConstructor
@Data
public class Breed {

    @NonNull
    private String name;

    private List<String> subBreed;

    public boolean isHasSubBreed() {
        return subBreed.isEmpty();
    }
}
