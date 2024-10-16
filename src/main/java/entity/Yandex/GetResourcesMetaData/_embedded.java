package entity.Yandex.GetResourcesMetaData;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class _embedded {
    private String sort;
    ArrayList<Item> items;
    private float limit;
    private float offset;
    private String path;
    private float total;
}
