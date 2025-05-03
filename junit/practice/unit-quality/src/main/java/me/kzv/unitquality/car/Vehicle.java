package me.kzv.unitquality.car;

// Vehicle 객체가 생성될 때 Driver 객체가 같이 생성된다.
// Driver 객체와 강하게 결합되어 의존하게 되는 문제점이 발생 >>> Vehicle 객체를 생성할 때 전달해줘도 충분하다. (의존성 주입)

class Vehicle {

    Driver driver = new Driver();
    boolean hasDriver = true;

    private void setHasDriver(boolean hasDriver) {
        this.hasDriver = hasDriver;
    }
}