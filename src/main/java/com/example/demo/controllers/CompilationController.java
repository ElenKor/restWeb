package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.repo.DishRepository;
import com.example.demo.services.DishService;
import com.example.demo.services.CompilationService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CompilationController {
    @Autowired
    private CompilationService compilationService;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private DishService dishService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.registration(user)) {
            model.addAttribute("errorMessage", "Пользователь с таким логином уже существует");
            return "registration";
        }
        return "redirect:/login";
    }



    @GetMapping("/compilations")
    public String compilations(Model model) {
        List<Compilation> compilations = compilationService.findAll();
        model.addAttribute("compilations",compilations);
        model.addAttribute("title", "Подборки от шефа");
        return "compilations";
    }

    @GetMapping("/compilations/add") //GetMapping - пользователь переходит по определённому адресу
    public String compilationsAdd(Model model) {
        model.addAttribute("compilations",compilationService.findAll());
        List<String> names = new ArrayList<>();
        for (Dish dish : dishService.findAll()){
            names.add(dish.getTitle());
        }
        model.addAttribute("names",names);
        return "compilations-add";
    }

    @RequestMapping(value = "/compilations/add",method = RequestMethod.POST) //получение данных из формы
    public String compilationsCompilationsAdd(@RequestParam String title, @RequestParam String[] dishes,@RequestParam String img) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Dish> dish = new ArrayList<>();
        for (String value : dishes){
            dish.add(dishRepository.findByTitle(value));
        }
        Compilation compilation = new Compilation(title,dish,img); //объект на основе модели Compilation с названием compilation. (title, fullText) - передача параметров
        compilationService.save(compilation); //сохранение объекта и добавление в бд -> обращение к репозиторию -> обращение к функции save и передача в него объекта, который необходимо сохранить => добавление в таблицу compilation навых подборок, полученных от пользователя
        return "redirect:/compilations"; //переадресация пользователя на указанную страницу после добавления подборки
    }

    @GetMapping("/compilations/{id}") //{id} - динамическое значение url-адреса
    public String compilationsDetails(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        Optional<Compilation> compilations = compilationService.findById(id); //нахождение записи по id и помещение в объект compilation на основе класса Optional и модели <Compilation>
        if(compilations.isPresent()) {
            ArrayList<Compilation> res = new ArrayList<>();
            compilations.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
            model.addAttribute("compilations", res);
            model.addAttribute("dishes",res.get(0).getDishes());
            model.addAttribute("title",res.get(0).getTitle());
            model.addAttribute("id",res.get(0).getId());
            return "compilations-details";
        } else {
            return "redirect:/compilations"; //перенаправление на указанную страницу
        }
    }

    @GetMapping("/compilations/{id}/edit") //редактирование подборки
    public String compilationsEdit(@PathVariable(value = "id") long id, Model model) { //@PathVariable - анотация, принимающая динамический параметр из url-адреса (в определённый параметр (long id) помещается значение, полученное из url-адреса
        if(!compilationService.existsById(id)){ //try - если определённая запись по определённому id не была найдена. иначе false
            return "redirect:/compilations"; //перенаправление на указанную страницу
        }
        //Optional<Post> post= postRepository.findById(id);
        Optional<Compilation> compilation= compilationService.findById(id); //нахождение записи по id и помещение в объект compilation на основе класса Optional и модели <Compilation>
        ArrayList<Compilation> res = new ArrayList<>();
        compilation.ifPresent(res::add); //из класса Optional переводим в класс ArrayList
        model.addAttribute("compilations", res);
        model.addAttribute("dishes",res.get(0).getDishes());
        return "compilations-edit";
    }

    @PostMapping("/compilations/{id}/edit") //получение данных из формы
    public String compilationsCompilationsEdit(@PathVariable(value = "id") long id, @RequestParam String title, Model model,@RequestParam String img) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Compilation compilation = compilationService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        compilation.setTitle(title);
        compilation.setImg(img);
        compilationService.save(compilation); //сохранение обновлённого объекта
        return "redirect:/compilations/{id}"; //переадресация пользователя на указанную страницу
    }

    @PostMapping("/compilations/{id}/remove") //получение данных из формы
    public String compilationsDelete(@PathVariable(value = "id") long id, Model model) { //@RequestParam - получение значений из формы. title - получение значений из данного поля
        Compilation compilation = compilationService.findById(id).orElseThrow(
                () -> new RuntimeException()
        ); //orElseTrow() - исключительная ситуация в случае не нахождения записи
        compilationService.delete(compilation); //удаление определенной записи
        return "redirect:/compilations"; //переадресация пользователя на указанную страницу после удаления подборки
    }
}
