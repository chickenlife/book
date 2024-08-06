package com.example.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private @Id String id;
    private List<CartItem> cartItem;

    public Cart(String id) {
        this(id, new ArrayList<>());
    }

    public Cart(String id, List<CartItem> cartItem) {
        this.id = id;
        this.cartItem = cartItem;
    }
}
