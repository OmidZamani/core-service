package com.boxi.ruleEngine.conf;


import com.boxi.ruleEngine.entity.RuleModel;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.mvel.MVELRule;
import org.springframework.stereotype.Component;



import java.util.List;

@Component
@RequiredArgsConstructor
public class RuleConverter {

    private final List<CustomRuleAction> ruleActions;

    public Rule convertToRule(RuleModel ruleModel) {
        return new MVELRule()
                .name(ruleModel.getName())
                .description(ruleModel.getDescription())
                .priority(ruleModel.getPriority())
                .when(ruleModel.getCondition())
                .then(defineActionMethod(ruleModel.getAction()));
    }

    private String defineActionMethod(String action) {
        return ruleActions.stream()
                .filter(ruleAction -> ruleAction.getAction().equals(action))
                .map(CustomRuleAction::getActionMethod)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Undefined : " + action));
    }

}