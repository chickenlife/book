package com.example.book;

import org.springframework.data.repository.CrudRepository;

public interface BlockingItemRepository extends CrudRepository<Item,String> {
}
