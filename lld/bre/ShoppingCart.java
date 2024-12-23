package lld.bre;

import java.util.List;


public class ShoppingCart {
    private List<Product> products;
    private String userId;

    public ShoppingCart(List<Product> products, String userId) {
        this.products = products;
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
