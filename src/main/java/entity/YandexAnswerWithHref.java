package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class YandexAnswerWithHref {

    @NonNull
    private String href;

    @NonNull
    private String method;

    @NonNull
    private boolean templated;
}
