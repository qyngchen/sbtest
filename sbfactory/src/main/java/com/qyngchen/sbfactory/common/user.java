package com.qyngchen.sbfactory.common;

import com.qyngchen.sbfactory.common.factory.CommonFactory;
import com.qyngchen.sbfactory.common.product.Car;

public class user {

    public static void main(String[] args) {
        Car bmw = CommonFactory.buildCar("BMW");
        Car ferrari = CommonFactory.buildCar("Ferrari");
        bmw.desc();
        bmw.price();
        ferrari.desc();
        ferrari.price();
    }
}
