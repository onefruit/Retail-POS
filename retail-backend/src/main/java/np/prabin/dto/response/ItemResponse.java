package np.prabin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ItemResponse {
    private String itemId;       // your unique code
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
    private String categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
