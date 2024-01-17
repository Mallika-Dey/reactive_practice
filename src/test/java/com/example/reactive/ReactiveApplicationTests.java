package com.example.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class ReactiveApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMap() {
        List<List<Integer>> numbers = new ArrayList<>(List.of(
                List.of(1, 2), List.of(3, 4), List.of(5, 6)));

        List<Integer> oneDimen = numbers.stream().flatMap(Collection::stream).toList();
        System.out.println(oneDimen);
    }

    @Test
    public void testError() {
        Flux<Integer> flux1 = Flux.just(1, 2, 3, 4, 5, 6);
        Flux<Integer> flux2 = Flux.just(4, 5, 6);
        Flux<Integer> mergedFlux = Flux.merge(flux1, flux2); // interleaved
        Flux<Integer> concatenatedFlux = Flux.concat(flux1, flux2); // in order
        Flux<Integer> zipFlux = Flux.zip(flux1, flux2, (f1, f2) -> f1 + f2);
        Flux<Integer> combinedFlux = Flux.combineLatest(flux1, flux2, (f1, f2) -> f1 + f2);

        mergedFlux
                .log()
                .doOnSubscribe(s -> System.out.println("subscribe" + s))
                .subscribe(
                        value -> System.out.println(value),
                        error -> System.out.println("error" + error.getMessage())
                );


        concatenatedFlux
                .log()
                .subscribe(
                        value -> System.out.println("concated flux " + value),
                        error -> System.out.println("error" + error.getMessage()),
                        () -> System.out.println("complete"),
                        subscription -> subscription.request(4) //no of elements it request
                );

        zipFlux
                .log()
                .subscribe(
                        value -> System.out.println("zip flux " + value),
                        error -> System.out.println("error" + error.getMessage()),
                        () -> System.out.println("complete"),
                        //subscription canceled
                        subscription -> subscription.cancel()
                );

        combinedFlux
                .log()
                .subscribe(
                        value -> System.out.println("zip flux " + value),
                        error -> System.out.println("error" + error.getMessage()),
                        () -> System.out.println("complete")
                        //subscription canceled
                        //subscription -> subscription.cancel()
                );

    }

    @Test
    public void testMethods() {
//        Flux.just(1, 2, 3, 4)
//                .log()
//                .doOnSubscribe(value -> System.out.println("subscription start " + value))
//                .doOnNext(value -> System.out.println("on next " + value))
//                .doOnError(error -> System.out.println("error " + error))
//                .subscribe();

        Flux<String> aliceStories = Flux.just("Alice's Story 1", "Alice's Story 2", "Alice's Story 3");
        Flux<String> bobStories = Flux.just("Bob's Story 1", "Bob's Story 2", "Bob's Story 3");

        Flux<String> mergedStories = Flux.merge(aliceStories, bobStories);

        // Subscribe to listen to the merged stories
        mergedStories.subscribe(story -> System.out.println("Merged Story: " + story));
    }
}
