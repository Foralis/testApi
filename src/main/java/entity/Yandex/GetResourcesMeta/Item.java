package entity.Yandex.GetResourcesMeta;

import java.util.ArrayList;

public class Item {
    private String antivirus_status;
    private float size;
    Comment_ids Comment_idsObject;
    private String name;
    Exif ExifObject;
    private String created;
    private String resource_id;
    private String modified;
    private String mime_type;
    ArrayList<Object> sizes = new ArrayList<>();
    private String file;
    private String media_type;
    private String preview;
    private String path;
    private String sha256;
    private String type;
    private String md5;
    private float revision;


    // Getter Methods

    public String getAntivirus_status() {
        return antivirus_status;
    }

    public float getSize() {
        return size;
    }

    public Comment_ids getComment_ids() {
        return Comment_idsObject;
    }

    public String getName() {
        return name;
    }

    public Exif getExif() {
        return ExifObject;
    }

    public String getCreated() {
        return created;
    }

    public String getResource_id() {
        return resource_id;
    }

    public String getModified() {
        return modified;
    }

    public String getMime_type() {
        return mime_type;
    }

    public String getFile() {
        return file;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getPreview() {
        return preview;
    }

    public String getPath() {
        return path;
    }

    public String getSha256() {
        return sha256;
    }

    public String getType() {
        return type;
    }

    public String getMd5() {
        return md5;
    }

    public float getRevision() {
        return revision;
    }

    // Setter Methods

    public void setAntivirus_status( String antivirus_status ) {
        this.antivirus_status = antivirus_status;
    }

    public void setSize( float size ) {
        this.size = size;
    }

    public void setComment_ids( Comment_ids comment_idsObject ) {
        this.Comment_idsObject = comment_idsObject;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setExif( Exif exifObject ) {
        this.ExifObject = exifObject;
    }

    public void setCreated( String created ) {
        this.created = created;
    }

    public void setResource_id( String resource_id ) {
        this.resource_id = resource_id;
    }

    public void setModified( String modified ) {
        this.modified = modified;
    }

    public void setMime_type( String mime_type ) {
        this.mime_type = mime_type;
    }

    public void setFile( String file ) {
        this.file = file;
    }

    public void setMedia_type( String media_type ) {
        this.media_type = media_type;
    }

    public void setPreview( String preview ) {
        this.preview = preview;
    }

    public void setPath( String path ) {
        this.path = path;
    }

    public void setSha256( String sha256 ) {
        this.sha256 = sha256;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public void setMd5( String md5 ) {
        this.md5 = md5;
    }

    public void setRevision( float revision ) {
        this.revision = revision;
    }
}
