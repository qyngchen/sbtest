package com.qyngchen.sbstrategy.strategy.impl;

import com.qyngchen.sbstrategy.strategy.Strategy;
import org.springframework.stereotype.Component;

@Component(value = "senseLink")
public class StrategySenseLink extends Strategy {
    @Override
    public String doOperation() {
        return "senseLink";
    }
}
