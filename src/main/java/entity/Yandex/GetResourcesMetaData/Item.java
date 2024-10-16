package entity.Yandex.GetResourcesMetaData;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Item {
    private String antivirus_status;
    private float size;
    private Comment_ids Comment_ids;
    private String name;
    private Exif Exif;
    private String created;
    private String resource_id;
    private String modified;
    private String mime_type;
    private ArrayList<Object> sizes;
    private String file;
    private String media_type;
    private String preview;
    private String path;
    private String sha256;
    private String type;
    private String md5;
    private float revision;
}
