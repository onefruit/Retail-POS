package np.prabin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@Getter
public class CategoryResponse {
    private String categoryId;
    private String name;
    private String description;
    private String bgColor;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String imgUrl;
    private Integer items;
}
