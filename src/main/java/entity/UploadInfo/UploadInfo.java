package entity.UploadInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UploadInfo {
    private String breed;
    private String folderName;
    List<String> images;
}
