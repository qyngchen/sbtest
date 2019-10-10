package com.qyngchen.sbfactory.common.product.impl;

import com.qyngchen.sbfactory.common.product.Car;

public class FerrariCar extends Car{
    @Override
    public void desc() {
        System.out.println("法拉利");
    }

    @Override
    public void price() {
        System.out.println("3000000");
    }
}
