package np.prabin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripeOrderResponse {
    private String sessionId;
    private String paymentStatus;
    private Long amountTotal;
    private String currency;
    private Date createdAt;
}
