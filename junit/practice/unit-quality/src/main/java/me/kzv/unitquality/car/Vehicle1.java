package me.kzv.unitquality.car;

class Vehicle1 {

    Driver driver;
    boolean hasDriver = true;

    Vehicle1(Driver driver) {
        this.driver = driver;
    }

    private void setHasDriver(boolean hasDriver) {
        this.hasDriver = hasDriver;
    }
}