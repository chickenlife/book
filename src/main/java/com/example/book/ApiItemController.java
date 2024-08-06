package com.example.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class ApiItemController {

    private final ItemRepository itemRepository;

    public ApiItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/api/items")
    Flux<Item> findAll(){
        return this.itemRepository.findAll();
    }

    @GetMapping("/api/items/{id}")
    Mono<Item> findById(@PathVariable String id){
        return this.itemRepository.findById(id);
    }

    @PostMapping("/api/items")
    Mono<ResponseEntity<?>> addNewItem(@RequestBody Mono<Item> item){
        return item.flatMap(s -> this.itemRepository.save(s))
                .map(savedItem -> ResponseEntity
                        .created(URI.create("/api/items/"+
                                savedItem.getId()))
                        .body(savedItem));
    }

}
