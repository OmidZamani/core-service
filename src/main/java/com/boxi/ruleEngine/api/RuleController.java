package com.boxi.ruleEngine.api;

import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.service.RuleModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/core-service/rules")
@RequiredArgsConstructor
public class RuleController {

    private final RuleModelService ruleService;

    @GetMapping("/{id}")
    public RuleModel get(@PathVariable("id") Long id) {
        return ruleService.findById(id);
    }

    @GetMapping
    public Iterable<RuleModel> getAll() {
        return ruleService.findAll();
    }

    @PostMapping("/save")
    public RuleModel save(@RequestBody @Valid RuleModel ruleModel) {
        return ruleService.save(ruleModel);
    }


    @PostMapping("/update/{id}")
    public RuleModel update(@PathVariable Long id,@RequestBody @Valid RuleModel ruleModel) {
        return ruleService.edit(id,ruleModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        ruleService.delete(id);
    }
}