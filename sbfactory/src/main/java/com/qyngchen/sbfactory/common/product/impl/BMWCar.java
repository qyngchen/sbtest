package com.qyngchen.sbfactory.common.product.impl;

import com.qyngchen.sbfactory.common.product.Car;

public class BMWCar extends Car {
    @Override
    public void desc() {
        System.out.println("宝马汽车");
    }

    @Override
    public void price() {
        System.out.println("2000000");
    }
}
