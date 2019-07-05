package com.qyngchen.sbeasyrule.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

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
    public Boolean isFlag() {

        return true;
    }

    /**
     * 命中规则之后采取的操作
     */
    @Action
    public void doSomething() {

    }

    /**
     * 优先级
     */
    @Priority
    public int priority() {
        return 1;
    }
}
