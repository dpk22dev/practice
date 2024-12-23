package lld.bre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkButLimitCategory implements BusinessRule {

    public static final int DEFAULT_VALUE = 50;
    private Map<Category, Integer> productCatQuantityMap = new HashMap<>();
    private Map<Category, Integer> productQuantityLimit = new HashMap<>();

    public BulkButLimitCategory(Map<Category, Integer> productQuantityLimit) {
        this.productQuantityLimit = productQuantityLimit;
    }

    @Override
    public RuleEngineResponse apply(ShoppingCart shoppingCart) {
        RuleEngineResponse response = RuleEngineResponse.MET;
        List<Product> productList = shoppingCart.getProducts();
        for (Product p : productList) {
            productCatQuantityMap.computeIfAbsent(p.getCategory(), (k) -> 0);
            productCatQuantityMap.computeIfPresent(p.getCategory(), (k, q) -> q + p.getQuantity());
        }

        for (Map.Entry<Category, Integer> entry : productCatQuantityMap.entrySet()) {
            if (entry.getValue() > productQuantityLimit.getOrDefault(entry.getKey(), DEFAULT_VALUE)) {
                response = RuleEngineResponse.BREACHED;
                break;
            }
        }
        return response;
    }
}
