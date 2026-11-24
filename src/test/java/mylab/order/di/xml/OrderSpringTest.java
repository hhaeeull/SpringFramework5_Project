package mylab.order.di.xml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    // ShoppingCart Bean 주입
    @Autowired
    private ShoppingCart shoppingCart;

    // OrderService Bean 주입
    @Autowired
    private OrderService orderService;


    // 1. ShoppingCart Bean 테스트
    @Test
    public void testShoppingCart() {

        // shoppingCart 객체가 null이 아닌지 확인
        assertNotNull(shoppingCart);

        // 상품 개수가 2개인지 확인
        assertEquals(2, shoppingCart.getProducts().size());

        // 첫 번째 상품 이름 확인
        assertEquals("노트북",
                shoppingCart.getProducts().get(0).getName());

        // 두 번째 상품 이름 확인
        assertEquals("스마트폰",
                shoppingCart.getProducts().get(1).getName());
    }


    // 2. OrderService Bean 테스트
    @Test
    public void testOrderService() {

        // orderService 객체가 null이 아닌지 확인
        assertNotNull(orderService);

        // OrderService 내부의 ShoppingCart가 null이 아닌지 확인
        assertNotNull(orderService.getShoppingCart());

        // 주문 총액 계산 결과 확인
        assertEquals(950000.0,
                orderService.calculateOrderTotal(), 0.001);
    }
}
