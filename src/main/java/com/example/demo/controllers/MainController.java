package com.example.demo.controllers;

import com.example.demo.models.Dish;
import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import com.example.demo.repo.UserRepository;
import com.example.demo.services.DishService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
@Controller
public class MainController { //класс, отвещающий за обработку всех переходов по сайту (@Controller)

    @Autowired private DishService dishService;
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @GetMapping("/admPage")
    private String admPage(Model model){
        List<User> users = userRepository.findAll();
        List<User> registered = new ArrayList<>();
        for (User user : users){
            if(user.getRole().equals(Role.REGISTERED)){
                registered.add(user);
            }
        }
        model.addAttribute("registered",registered);
        return "admPage";
    }

    @PostMapping("/us/makeManager/{id}")
    private String makeManager(@PathVariable Long id){
        userService.makeManager(id);
        return "redirect:/admPage";
    }

    @PostMapping("/us/makeChief/{id}")
    private String makeChief(@PathVariable Long id){
        userService.makeChief(id);
        return "redirect:/admPage";
    }

    @GetMapping("/adm/dishes/add")
    private String addDish(Model model){
        return "admDishCreate";
    }

    @PostMapping("/adm/dishes/create")
    private String addAddDish(@RequestParam String title){
        dishService.save(new Dish(title));
        return "redirect:/dishes";
    }

    @GetMapping("/adm/dishes/edit/{id}")
    private String editDish(Model model,@PathVariable Long id){
        if(!dishService.existsById(id)){ //try - если определённая запись по определённому id не была найдена. иначе false
            return "redirect:/dishes"; //перенаправление на указанную страницу
        }
        Optional<Dish> dish= dishService.findById(id); //нахождение записи по id и помещение в объект dish на основе класса Optional и модели <Dish>
        ArrayList<Dish> res = new ArrayList<>();
        dish.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
        model.addAttribute("dishes", res);
        return "admDishUpdate";
    }

    @PostMapping("/adm/dishes/{id}/edit")
    private String editEditDish(@RequestParam String title,@PathVariable Long id){
        Dish dish = dishService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        dish.setTitle(title); //установка введеного заголовка
        dishService.save(dish); //сохранение обновлённого объекта
        return "redirect:/dishes/{id}";
    }

}
