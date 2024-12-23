package lld.bre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Item-1 -> productid=1, category=Paracetamol, quantity=3
Item-2 -> productid=2, category=analgesic, quantity=3
Item-3 -> productid=3, category=chocolate, quantity=8
Item-4 -> productid=4, category= Paracetamol, quantity=2
 */
public class Driver {
    public static void main(String[] args) {
        Product p1 = new Product(1, 30, Category.PARACETAMOL);
        Product p2 = new Product(2, 3, Category.ANALGESIC);
        Product p3 = new Product(3, 8, Category.CHOCOLATE);
        Product p4 = new Product(4, 25, Category.PARACETAMOL);
        List<Product> productList = Arrays.asList(p1, p2, p3, p4);
        ShoppingCart shoppingCart = new ShoppingCart(productList, "101");

        BusinessRule buyLimitRule = new BulkBuyLimit();
        Map<Category, Integer> catLimitMap = new HashMap<>();
        catLimitMap.put(Category.PARACETAMOL, 500);

        BusinessRule buyCatLimitRule = new BulkButLimitCategory(catLimitMap);
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(Arrays.asList(buyLimitRule, buyCatLimitRule));

        RuleEngineResponse ruleEngineResponse = businessRuleEngine.applyList(shoppingCart);
        System.out.println(ruleEngineResponse.toString());
    }
}
