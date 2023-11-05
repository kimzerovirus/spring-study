package me.kzv.reactive.toby;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.Flow.*;

/**
 * Reactive Streams - Operators
 * <p>
 * Publisher -> [Data1] -> Op1 -> [Data2] -> op2 -> [Data3] -> Subscriber
 * <p>
 * pub -> [Data1] -> mapPub -> [Data2] -> logSub
 * <- subscribe(logSub)
 * -> onSubscribe(s)
 * -> onNext
 * -> onNext
 * -> onComplete
 * 1. map (d1 -> f -> d2)
 */

@Slf4j
public class PubSub2 {
    public static void main(String[] args) {
        Publisher<Integer> pub = iterPub(Stream.iterate(1, a -> a + 1).limit(10).toList());
        Publisher<Integer> mapPub = mapPub(pub, s -> s * 10);
        Publisher<Integer> map2Pub = mapPub(mapPub, s -> s + 1);
        map2Pub.subscribe(logSub());
    }

    private static Publisher<Integer> mapPub(Publisher<Integer> pub, Function<Integer, Integer> fn) {
        return sub -> pub.subscribe(new DelegateSub(sub) {
            @Override
            public void onNext(Integer i) {
                super.onNext(fn.apply(i));
            }
        });
    }

    private static Publisher<Integer> iterPub(Iterable<Integer> iter) {
        return new Publisher<>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                sub.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        try {
                            iter.forEach(sub::onNext);
                            sub.onComplete();
                        } catch (Throwable t) {
                            sub.onError(t);
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };
    }

    private static Subscriber<Integer> logSub() {
        return new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription sub) {
                log.debug("onSubscribe");
                sub.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer i) {
                log.debug("onNext: {}", i);
            }

            @Override
            public void onError(Throwable t) {
                log.debug("onError: {}", t.getMessage());
            }

            @Override
            public void onComplete() {
                log.debug("onComplete");
            }
        };
    }
}
