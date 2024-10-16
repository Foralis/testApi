package entity.Yandex.GetResourcesMetaData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerResponseWithMetaData {
    private _embedded _embedded;
    private String name;
    private Exif Exif;
    private String resource_id;
    private String created;
    private String modified;
    private String path;
    private Comment_ids Comment_ids;
    private String type;
    private float revision;
}
