package progs;

public class Driver {
    public static void main(String[] args) {
        Order order = OrderBuilder.createOrder().addQuantity(10).addDescription("ten").addDeliveryDate("2023-01-01").build();
        System.out.println(order.toString());
    }
}
