package me.kzv.reactive.exercise;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Slf4j
public class Exercise {
    private static List<Integer> list = List.of(1, 2, 3, 4, 5);

    /**
     * subscribe 하지 않으면 아무일도 일어나지 않는다.
     */
    public void subscribeTest() {
        Flux.fromIterable(list)
                .doOnNext(value -> log.info("value: {}", value)) //doOnNext 파이프라인에 영향을 주지 않고 지나가는 값을 확인한다.
//                .subscribe()
        ;
    }

    public void functionalSubscribe() {
//        Flux.fromIterable(list)
//                .subscribe(integer -> {
//                    log.info("value: {}", integer);
//                }, throwable -> {
//                    log.error("error: {}", throwable.getMessage());
//                }, () -> {
//                    log.info("complete");
//                }, Context.empty());

        Flux.fromIterable(list)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        log.info("value: {}", integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        log.error("error: {}", throwable.getMessage());
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        log.info("complete");
                    }
                }, Context.empty())
        ;
    }

    public void mySubscriber() {
        Flux.fromIterable(list)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        log.info("value: {}", integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        log.error("error: {}", t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        log.info("complete");
                    }
                });
    }

    /**
     * subscriber 를 한번 감싼 BaseSubscriber
     * <p>
     * buffer(N) 호출시 N개만큼 모아서 List로 전달
     * • buffer(3) 호출 후 request(2)를 하는 경우, 3개가 담긴 List 2개가 Subscriber에게 전달, 즉 6개의 item을 전달
     */
    public void baseSubscriber() {
        var subscriber = new BaseSubscriber<List<Integer>>() {
            private Integer count = 0;

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(2); //back pressure 2개만 요청
            }

            @Override
            protected void hookOnNext(List<Integer> value) {
                log.info("value: {}", value);
                if (++count == 2) cancel(); // onNext 이벤트가 발생하면 cancel을 실행
            }

            @Override
            protected void hookOnComplete() {
                log.info("complete");
            }
        };

        Flux.fromStream(IntStream.range(0, 10).boxed())
                .buffer(3)
                .subscribe(subscriber);
    }

    /**
     * take(n, limitRequest)
     * <p>
     * subscriber 외부에서 연산자를 통해서 최대 개수를 제한
     * • limitRequest가 true인 경우, 정확히 n개만큼
     * 요청 후 complete 이벤트를 전달
     * • BaseSubscriber의 기본 전략이 unbounded
     * request이지만 take(5, true)로 인해서 5개 전달 후 complete 이벤트
     */

    public void take() {
        var subscriber = new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnNext(Integer value) {
                log.info("value: {}", value);
            }

            @Override
            protected void hookOnComplete() {
                log.info("complete");
            }
        };

        Flux.fromStream(IntStream.range(0, 10).boxed())
                .take(5, true)
                .subscribe(subscriber);
    }

    /**
     * just
     * <p>
     * Mono.just 혹은 Flux.just 를 통해서 주어진 객체를 subscriber에게 전달
     */
    public void just() {
        Mono.just(1)
                .subscribe(value -> {
                    log.info("value: {}", value);
                });

        Flux.just(1, 2, 3, 4, 5)
                .subscribe(value -> {
                    log.info("value: {}", value);
                });
    }

    /**
     * error
     * <p>
     * Mono.error 혹은 Flux.error 를 통해서 subscriber에게 onError 이벤트만 전달
     */
    public void error() {
        Mono.error(new RuntimeException("mono error"))
                .subscribe(value -> {
                    log.info("value: {}", value);
                }, error -> {
                    log.error("error: {}", error.getMessage());
                });
        Flux.error(new RuntimeException("flux error"))
                .subscribe(value -> {
                    log.info("value: {}", value);
                }, error -> {
                    log.error("error: {}", error.getMessage());
                });
    }

    /**
     * empty
     * <p>
     * Mono.empty 혹은 Flux.empty 를 통해서 subscriber에게 onComplete 이벤트만 전달
     */
    public void empty() {
        Mono.empty()
                .subscribe(value -> {
                    log.info("value: {}", value);
                }, null, () -> {
                    log.info("complete");
                });

        Flux.empty()
                .subscribe(value -> {
                    log.info("value: {}", value);
                }, null, () -> {
                    log.info("complete");
                });
    }

    /**
     * mono from
     * <p>
     * • fromCallable: Callable 함수형 인터페이스를 실행하고 반환값을 onNext로 전달
     * • fromFuture: Future를 받아서 done 상태가되면 반환값을 onNext로 전달
     * • fromSupplier: Supplier 함수형 인터페이스를 실행하고 반환값을 onNext로 전달
     * • fromRunnable: Runnable 함수형 인터페이스를 실행하고 끝난후 onComplete 전달
     */
    public void monoFrom() {
        Mono.fromCallable(() -> 1)
                .subscribe(value -> {
                    log.info("value fromCallable: {}", value);
                });

        Mono.fromFuture(CompletableFuture.supplyAsync(() -> 1))
                .subscribe(value -> {
                    log.info("value fromFuture: {}", value);
                });

        Mono.fromSupplier(() -> 1)
                .subscribe(value -> {
                    log.info("value fromSupplier: {}", value);
                });

        Mono.fromRunnable(() -> {
            /** do nothing */
        }).subscribe(null, null, () -> {
            log.info("complete fromRunnable");
        });
    }

    /**
     * flux from
     * <p>
     * • fromIterable: Iterable를 받아서 각각의 item을 onNext로 전달
     * • fromStream: Stream를 받아서 각각의 item을 onNext로 전달
     * • fromArray: Array를 받아서 각각의 item을 onNext로 전달
     * • range(start, n): start부터 시작해서 한개씩 커진 값을 n개만큼 onNext로 전달
     */

    public void fluxFrom() {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .subscribe(value -> {
                    log.info("value: {}", value);
                });

        Flux.fromStream(IntStream.range(1, 6).boxed())
                .subscribe(value -> {
                    log.info("value: {}", value);
                });

        Flux.fromArray(new Integer[]{1, 2, 3, 4, 5})
                .subscribe(value -> {
                    log.info("value: {}", value);
                });

        Flux.range(1, 5)
                .subscribe(value -> {
                    log.info("value: {}", value);
                });
    }

    /**
     * generate
     * <p>
     * • 초기값을 0으로 세팅
     * • generator에서 현재 state를 next로 전달
     * • 만약 state가 9라면 complete 이벤트를 전달
     * • state + 1을 반환
     */
    public void generate() {
        Flux.generate(() -> 0,
                (state, sink) -> {
                    sink.next(state);
//                    sink.next(state) // - 하나의 generate 에서 next 를 2번 호출하면 에러가 발생한다
                    if (state == 9) {
                        sink.complete();
                    }
                    return state + 1;
                }
        ).subscribe(value -> {
            log.info("value: {}", value);
        }, error -> {
            log.error("error: {}", error.getMessage());
        }, () -> {
            log.info("complete");
        });
    }

    /**
     * create
     * <p>
     * • 비동기적으로 Flux를 생성
     * • FluxSink를 노출
     * • 명시적으로 next, error, complete 호출 가능
     * • SynchronousSink와 다르게 여러 번 next 가능
     * • 여러 thread에서 동시에 호출 가능
     * <p>
     * • 2개의 쓰레드에서 sink.next를 수행
     * • CompletableFuture의 allOf를 활용하여 두개의 작업이 끝난 후 complete 이벤트 전달
     */
    public void create() {
        Flux.create(sink -> {
            var task1 = CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 5; i++) {
                    sink.next(i);
                }
            });
            var task2 = CompletableFuture.runAsync(() -> {
                for (int i = 5; i < 10; i++) {
                    sink.next(i);
                }
            });
            CompletableFuture.allOf(task1, task2)
                    .thenRun(sink::complete);
        }).subscribe(value -> {
            log.info("value:  {}", value);
        }, error -> {
            log.error("error:  {}", error.getMessage());
        }, () -> {
            log.info("complete");
        });
    }

    /**
     * handle
     * <p>
     * • 독립적으로 sequence를 생성할 수는 없고 존재하는 source에 연결
     * • handler
     * • 첫 번째 인자로 source의 item이 제공
     * • 두 번째 인자로 SynchronousSink를 제공
     * • sink의 next를 이용해서 현재 주어진 item을 전달할지 말지를 결정
     * • 일종의 interceptor로 source의 item을 필터하거나 변경할 수 있다
     * • handle을 통해서 value와 sink가 전달
     * • sink의 next를 호출하여 값을 필터하거나 변경할 수 있고 complete, error를 더 일찍 전달 가능
     */
    public void handle() {
        Flux.fromStream(IntStream.range(0, 10).boxed())
                .handle((value, sink) -> {
                    if (value % 2 == 0) {
                        sink.next(value);
                    }
                }).subscribe(value -> {
            log.info("value:  {}", value);
        }, error -> {
            log.error("error:  {}", error);
        }, () -> {
            log.info("complete");
        });
    }
}
