package com.qyngchen.sbstrategy.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FactoryForStrategyTest {

    @Autowired
    private FactoryForStrategy factoryForStrategy;

    @Test
    public void getStrategy() {

        try {
            String senseLink = factoryForStrategy.getStrategy("senseLink").doOperation();
            System.out.println(senseLink);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}