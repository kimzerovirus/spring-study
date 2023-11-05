package me.kzv.reactive.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    public static Exercise exercise = new Exercise();

    @Test
    public void test() throws Exception {
//        exercise.subscribeTest();
//        exercise.functionalSubscribe();
        exercise.mySubscriber();
    }
}