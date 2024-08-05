package com.example.book;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class KitchenService {
    Flux<Dish> getDishes() {
        return Flux.<Dish> generate(sink -> sink.next(randomDish()))
                .delayElements(Duration.ofMillis(250));
    }
}
