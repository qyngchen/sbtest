package com.qyngchen.sbeasyrule.rule;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 * 测试规则类
 *
 * @author chenqingyang
 */
@Rule
public class RuleTest {

    private int input;

    public void setInput(Integer input) {
        this.input = input;
    }

    /**
     * 判断是否命中规则
     */
    @Condition
    public Boolean isFlag(@Fact("rain") Boolean rain) {
        return rain;
    }

    /**
     * 命中规则之后采取的操作
     */
    @Action
    public void doSomething(Facts facts) {
        System.out.println(facts);
    }

    /**
     * 优先级
     */
    @Priority
    public int priority() {
        return 1;
    }
}
