package com.example.demo.repo;

import com.example.demo.models.Dish;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {//extends - наследование. <> - указание модели, с которой работаем и типа данных
    Dish findByTitle(String title);
}