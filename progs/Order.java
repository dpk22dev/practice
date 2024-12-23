package progs;

/*
Usage pattern:
const order = OrderBuilder.createOrder().addQuantity(10).addDescription("Test order").addDeliveryDate("31-06-2078").build();
order.quantity = 30; //error
 */
public class Order {

    private Integer quantity;
    private String description;
    private String deliveryDate;

    public Order(Integer quantity, String description, String deliveryDate) {
        this.quantity = quantity;
        this.description = description;
        this.deliveryDate = deliveryDate;
    }
}

class OrderBuilder {
    private Integer quantity;
    private String description;
    private String deliveryDate;

    public static OrderBuilder createOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder addQuantity(Integer q) {
        this.quantity = q;
        return this;
    }

    public OrderBuilder addDescription(String description) {
        this.description = description;
        return this;
    }

    public OrderBuilder addDeliveryDate(String date) {
        this.deliveryDate = date;
        return this;
    }

    public Order build() {
        return new Order(quantity, description, deliveryDate);
    }

}



