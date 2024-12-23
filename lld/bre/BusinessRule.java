package lld.bre;

public interface BusinessRule {
    RuleEngineResponse apply(ShoppingCart shoppingCart);
}
