package me.kzv.reactive;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Flow.Publisher;
import static java.util.concurrent.Flow.Subscriber;

@Slf4j
public class PubSub {
    public static void main(String[] args) throws InterruptedException {
        Iterable<Integer> itr = List.of(1, 2, 3, 4, 5);
        ExecutorService es = Executors.newSingleThreadExecutor();

        // lambda
//        Publisher p = subscriber -> {
//
//        };


        Publisher p = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {
                Iterator<Integer> it = itr.iterator();

                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) { // n은 요청 개수 ~ n개만 해줘
                        es.execute(() -> {
                            int i = 0;
                            try {
                                while (i++ < n) {
                                    if (it.hasNext()) {
                                        subscriber.onNext(it.next());
                                    } else {
                                        subscriber.onComplete();
                                        break;
                                    }
                                }
                            } catch (RuntimeException e) {
                                subscriber.onError(e);
                            }
                        });
                    }

                    @Override
                    public void cancel() { // 지금 상태가 어찌되었는지는 됐고 일단 취소 시켜줘

                    }
                });
            }
        };

        Subscriber<Integer> s = new Subscriber<>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                logger("onSubscribe");
//                subscription.request(2);
                this.subscription = subscription;
                this.subscription.request(1); // 첫번째 하나 요청
            }

            @Override
            public void onNext(Integer item) {
                logger("onNext - {}", item);
                this.subscription.request(1); // 다음 하나 요청
            }

            @Override
            public void onError(Throwable throwable) {
                logger("onError", throwable.getMessage());
            }

            @Override
            public void onComplete() {
                logger("onComplete");
            }
        };

        p.subscribe(s);

        es.awaitTermination(5, TimeUnit.SECONDS);
        es.shutdown();
    }

    public static void logger(String str) {
        logger(str, null);
    }

    public static void logger(String str, Object o){
        System.out.println("-------------------");
        if (o == null) {
            log.info(str);
        } else {
            log.info(str, o);
        }
        System.out.println("-------------------");
    }
}
