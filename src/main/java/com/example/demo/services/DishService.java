package com.example.demo.services;

import com.example.demo.models.Dish;
import com.example.demo.repo.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired private DishRepository dishRepository;

    public List<Dish> findAll() {
        ArrayList<Dish> activities = new ArrayList<>();
        dishRepository.findAll().forEach(activities::add);
        return activities;
    }

    @Transactional
    public Page<Dish> findAll(int page, int size) {
        return dishRepository.findAll(PageRequest.of(page, size, Sort.by("calories")));
    }

    @Transactional
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Transactional
    public Optional<Dish> findById(long id) {
        return  dishRepository.findById(id);
    }

    @Transactional
    public boolean existsById(long id) {
        return dishRepository.existsById(id);
    }

    @Transactional
    public void delete(Dish dish) {
        dishRepository.delete(dish);
    }

}

