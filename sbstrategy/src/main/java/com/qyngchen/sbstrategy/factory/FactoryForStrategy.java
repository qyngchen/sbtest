package com.qyngchen.sbstrategy.factory;

import com.qyngchen.sbstrategy.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FactoryForStrategy {

    @Autowired
    Map<String, Strategy> strategyMap = new ConcurrentHashMap<>();

    public Strategy getStrategy(String compant) throws Exception {
        Strategy strategy = strategyMap.get(compant);
        if (strategy == null) {
            throw new RuntimeException();
        }
        return strategy;
    }
}
