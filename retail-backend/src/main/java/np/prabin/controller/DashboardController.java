package np.prabin.controller;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.response.DashboardResponse;
import np.prabin.dto.response.OrderResponse;
import np.prabin.service.impl.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final OrderService orderService;

    @GetMapping
    public DashboardResponse getDashboardData() {
        LocalDate date = LocalDate.now();
        Double todaySale = orderService.sumSalesByDate(date);
        List<OrderResponse> recentOrders = orderService.findRecentOrders();
        Long orders = orderService.countByOrderDate(date);

        return new DashboardResponse(
                todaySale != null ? todaySale : 0.0,
                orders != null ? orders : 0,
                recentOrders
        );
    }
}
