package np.prabin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryId; // link to category
}
