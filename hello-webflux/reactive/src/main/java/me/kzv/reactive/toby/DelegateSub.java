package me.kzv.reactive.toby;

import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;

public class DelegateSub implements Subscriber<Integer> {
    Subscriber sub;


    public DelegateSub(Subscriber sub) {
        this.sub = sub;
    }

    @Override
    public void onSubscribe(Subscription s) {
        sub.onSubscribe(s); // 받아온거 중계만
    }

    @Override
    public void onNext(Integer i) {
        sub.onNext(i);
    }

    @Override
    public void onError(Throwable t) {
        sub.onError(t);
    }

    @Override
    public void onComplete() {
        sub.onComplete();
    }
}
