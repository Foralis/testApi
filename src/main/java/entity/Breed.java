package entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
public class Breed {
    @NonNull
    private String name;
    List<String> subBreed = new ArrayList<>();

    public Breed() {
    }

    public Breed(String name, List<String> subBreed) {
        this.name = name;
        this.subBreed = subBreed;
    }

    public boolean isHasSubBreed() {
        return subBreed.isEmpty();
    }
}
