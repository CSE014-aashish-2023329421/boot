package com.javam25.Springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private StudentRepository studentRepo;

    @RequestMapping("/")
    public String index(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Student student = studentRepo.findById(id).orElseThrow();
        model.addAttribute("student", student);
        return "add";
    }
}
