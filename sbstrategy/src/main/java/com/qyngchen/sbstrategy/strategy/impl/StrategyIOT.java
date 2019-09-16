package com.qyngchen.sbstrategy.strategy.impl;

import com.qyngchen.sbstrategy.strategy.Strategy;
import org.springframework.stereotype.Component;

@Component(value = "iot")
public class StrategyIOT extends Strategy {
    @Override
    public String doOperation() {
        return "iot";
    }
}
