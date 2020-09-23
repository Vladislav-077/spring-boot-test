package com.example.RatingJournal.controller;

import com.example.RatingJournal.model.Student;
import com.example.RatingJournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
public class LearnerController {

    private final UserService userService;

    @Autowired
    public LearnerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

    // Информацию будем дергать не из репозитория а из UserService- так правильно.!
    @GetMapping("/students")
    public String findAll(Model model) {
        List<Student> students = userService.findAll();
        model.addAttribute("students", students);
        //return String.format(model.toString());
        return "student-list";
    }
    // Удаление пользователя, после удаления пользователя происходит редирект на главную страницу.
    @GetMapping("/student-delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/students";
    }

    // При переходе по ссылке localhost/students-create - должна открыться html страница students-create.html
    @GetMapping("/student-create")
    public String createUserForm(Student student) {
        return "student-create";
    }
    // Так как форма на странице students-create.html отправит pos запрос, мы должны его поймайть.
    @PostMapping("/student-create")
    public String userCreate(Student student) {
        userService.saveUser(student);
        return "redirect:/students"; // Редирект на главную страницу.
    }

    @GetMapping("student-update/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model) {
        Student student = userService.findById(id);
        model.addAttribute(student);
        return "/student-update";
    }
    @PostMapping("/student-update")
    public String updateUser(Student student) {
        userService.saveUser(student);
        return "redirect:/students";
    }

    //Получаем всех отличников.
    @GetMapping("/student-findByRating")
    public String findAllStudentByRating(@RequestParam(value = "rating", required = true) Integer rating, Model model) {
        if (rating == null) {
            return "redirect:/students";
        }
        else {
            List<Student> students = userService.findGoodStudent(rating);
            model.addAttribute("students", students);
            return "student-list";
        }
    }



}
