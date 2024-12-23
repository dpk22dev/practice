package lld.bre;

import java.util.List;

public class BusinessRuleEngine {
    List<BusinessRule> businessRuleList;

    public BusinessRuleEngine(List<BusinessRule> businessRuleList) {
        this.businessRuleList = businessRuleList;
    }

    public RuleEngineResponse applyList(ShoppingCart shoppingCart) {
        for (BusinessRule businessRule : businessRuleList) {
            if (businessRule.apply(shoppingCart) == RuleEngineResponse.BREACHED) {
                return RuleEngineResponse.BREACHED;
            }
        }

        return RuleEngineResponse.MET;
    }

}
