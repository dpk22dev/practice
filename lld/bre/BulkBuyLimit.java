package lld.bre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkBuyLimit implements BusinessRule {
    private static int ANYPRODUCT_LIMIT = 10;
    private Map<Category, Integer> productCatQuantityMap = new HashMap<>();

    @Override
    public RuleEngineResponse apply(ShoppingCart shoppingCart) {
        RuleEngineResponse response = RuleEngineResponse.MET;
        List<Product> productList = shoppingCart.getProducts();
        for (Product p : productList) {
            productCatQuantityMap.computeIfAbsent(p.getCategory(), (k) -> 0);
            productCatQuantityMap.computeIfPresent(p.getCategory(), (k, q) -> q + p.getQuantity());
        }

        for (Map.Entry<Category, Integer> entry : productCatQuantityMap.entrySet()) {
            if (entry.getValue() > ANYPRODUCT_LIMIT) {
                response = RuleEngineResponse.BREACHED;
                break;
            }
        }
        return response;
    }
}
