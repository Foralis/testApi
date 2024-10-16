package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

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
