package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

//@Getter
//@Setter
@AllArgsConstructor
@Data
public class YandexDownloadUrl {

    @NonNull
    private String href;

    @NonNull
    private String method;

    @NonNull
    private boolean templated;
}
