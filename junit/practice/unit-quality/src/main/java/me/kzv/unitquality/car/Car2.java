package me.kzv.unitquality.car;

class Car2 {
    private Driver driver;

    // bad case: 데메테르 법칙 위반 (가까운 친구와 이야기하자)
    Car2(Context context) {
        this.driver = context.getDriver();
    }

    // good case
    Car2(Driver driver) {
        this.driver = driver;
    }
}