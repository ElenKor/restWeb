package com.example.demo.controllers;

import com.example.demo.models.Dish;
import com.example.demo.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DishController {


    @Autowired //анотация для создания переменной, ссылающейся на репозиторий
    private DishService dishService; //указание репозитория, к которому обращаемся и название пееременной

    @Configuration
    public class MvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/css/**")
                    .addResourceLocations("classpath:/css/");
        }
    }

    @GetMapping("/dishes")
    public String dishes(Model model) {
        model.addAttribute("dishes", dishService.findAll()); //передача значений
        model.addAttribute("title", "Блюда");
        return "dishes";
    }

    @GetMapping("/dishes/add") //GetMapping - пользователь переходит по определённому адресу
    public String dishesAdd(Model model) {
        return "dishes-add";
    }

    @RequestMapping(value = "/dishes/add",method = RequestMethod.POST) //получение данных из формы
    public String dishesDishesAdd(@RequestParam String img, @RequestParam String title, @RequestParam String calories, @RequestParam String fullText,@RequestParam List<String> ingredients) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        String ingredis = ingredients.get(0);
        for (int i = 1; i < ingredients.size(); i++){
            ingredis += "," + ingredients.get(i);
        }
        Dish dish = new Dish(img, title, fullText,calories, ingredis); //объект на основе модели dish с названием dish. (title, calories, fullText) - передача параметров
        dishService.save(dish); //сохранение объекта и добавление в бд -> обращение к репозиторию -> обращение к функции save и передача в него объекта, который необходимо сохранить => добавление в таблицу dish навых блюд, полученных от пользователя
        return "redirect:/dishes"; //переадресация пользователя на указанную страницу после добавления блюда
    }

    @GetMapping("/dishes/{id}") //{id} - динамическое значение url-адреса
    public String dishDetails(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        Optional<Dish> dish= dishService.findById(id); //нахождение записи по id и помещение в объект dish на основе класса Optional и модели <Dish>
        if(dish.isPresent()) {
            ArrayList<Dish> res = new ArrayList<>();
            dish.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
            model.addAttribute("dishes", res);
            return "dishes-details";
        } else {
            return "redirect:/dishes"; //перенаправление на указанную страницу
        }
    }

    @GetMapping("/dishes/{id}/edit") //редактирование блюда
    public String dishEdit(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        if(!dishService.existsById(id)){ //try - если определённая запись по определённому id не была найдена. иначе false
            return "redirect:/dishes"; //перенаправление на указанную страницу
        }
        Optional<Dish> dish= dishService.findById(id); //нахождение записи по id и помещение в объект dish на основе класса Optional и модели <Dish>
        ArrayList<Dish> res = new ArrayList<>();
        dish.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
        model.addAttribute("dishes", res);
        return "dishes-edit";
    }

    @PostMapping("/dishes/{id}/edit") //получение данных из формы
    public String dishDishUpdate(@PathVariable(value = "id") long id, @RequestParam String img, @RequestParam String title, @RequestParam String calories, @RequestParam String fullText, Model model,@RequestParam String ingredients) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Dish dish = dishService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        dish.setImg(img);
        dish.setTitle(title); //установка введеного заголовка
        dish.setCalories(calories);
        dish.setFullText(fullText);
        dish.setIngredients(ingredients);
        dishService.save(dish); //сохранение обновлённого объекта
        return "redirect:/dishes/{id}"; //переадресация пользователя на указанную страницу после добавления блюда
    }

    @PostMapping("/dishes/{id}/remove") //получение данных из формы
    public String dishDelete(@PathVariable(value = "id") long id, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Dish dish = dishService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        dishService.delete(dish); //удаление определенного блюда
        return "redirect:/dishes"; //переадресация пользователя на указанную страницу после удаления блюда
    }
}
