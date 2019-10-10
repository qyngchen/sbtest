package com.qyngchen.sbfactory.common.factory;

import com.qyngchen.sbfactory.common.product.Car;
import com.qyngchen.sbfactory.common.product.impl.BMWCar;
import com.qyngchen.sbfactory.common.product.impl.FerrariCar;

public class CommonFactory {

    public static Car buildCar(String type) {
        if ("BMW".equals(type)) {
            return new BMWCar();
        } else if ("Ferrari".equals(type)) {
            return new FerrariCar();
        } else {
            return null;
        }
    }
}
