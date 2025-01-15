package com.xsis.b345.frontend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xsis.b345.frontend.models.*;


@Controller
@RequestMapping("/category")
public class CategoryController {
    private List<category> data = new ArrayList<category>();

    CategoryController(){
        data.add(new category());
        data.get(0).setId(1);
        data.get(0).setName("Makanan");
        data.get(0).setDeskripsi("Kategori Makanan");
        data.get(0).setDeleted(false);
        data.get(0).setCreateBy(1);
        data.get(0).setCreateDate(LocalDateTime.now());

        data.add(new category());
        data.get(1).setId(2);
        data.get(1).setName("Obat");
        data.get(1).setDeskripsi("Kategori Obat-Obatan");
        data.get(1).setDeleted(false);
        data.get(1).setCreateBy(1);
        data.get(1).setCreateDate(LocalDateTime.now());
    }

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/category/index");
   
        view.addObject("category", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView addData() {
        ModelAndView view= new ModelAndView("/category/add");
        view.addObject("title", "Add new category");
        return view;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute category Category){
        try {
            if(Category.getName() ==""){
                return "Gagal";
            }else{
                return "Sukses";
            }   
        } catch (Exception e) {
            return "gagal";
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editdata(@PathVariable long id){
        ModelAndView view= new ModelAndView("/category/edit");
        category Category = data.get((int)id-1);
        view.addObject("category", Category);
        view.addObject("title", "Edit Category");
        return view;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute category Category){
        try {
            if(Category.getName() ==""){
                return "Gagal";
            }else{
                return "Sukses";
            }   
        } catch (Exception e) {
            return "gagal";
        }
    }
    
}
