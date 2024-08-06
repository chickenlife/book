package com.example.book;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Service
public class CartService {
    private ItemRepository itemRepository;
    private CartRepository cartRepository;

    public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    Mono<Cart> addToCart(String cartId, String id){
        return this.cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .flatMap(cart -> cart.getCartItem().stream()
                        .filter(cartItem ->cartItem.getItem()
                                .getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet( () ->
                            this.itemRepository.findById(id)
                                    .map(CartItem::new)
                                    .doOnNext(cartItem ->
                                            cart.getCartItem().add(cartItem))
                                    .map(cartItem -> cart)))
                .flatMap(this.cartRepository::save);
    }

}
