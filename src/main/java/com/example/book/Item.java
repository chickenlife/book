package com.example.book;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private @Id String id;
    private String name;
    private String description;
    private double price;
    private String distributorRegion;
    private Date releaseDate;
    private int availableUnits;
    private Point location;
    private boolean active;

    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
