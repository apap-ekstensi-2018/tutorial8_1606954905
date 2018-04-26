package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


   

    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	model.addAttribute("title", "Add new studnet");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (Model model,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa);
        studentDAO.addStudent (student);
         
        model.addAttribute("title", "Konfirmasi Add new studnet");
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title", "View Student");
            
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title", "View Student");
            
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title", "View Student");
            
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title", "View Student");
            
            return "not-found";
        }
    }
    
    @RequestMapping("/student/update/{npm}")
    public String updatePath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute("title", "Update Student");
            model.addAttribute ("student", student);
            //System.out.println(student);
            //return "form-update";
            return "form-update-object";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title", "Update Student");
            return "not-found";
        }
    }

//    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
//    public String updateSubmit(@RequestParam(value = "npm", required = false) String npm,
//    		@RequestParam(value = "name", required = false) String name,
//    		@RequestParam(value = "gpa", required = false) double gpa)
//    {
//    	 	StudentModel student = new StudentModel (npm, name, gpa);
//         studentDAO.updateStudent(student); 
//         return "success-update";
//    }
    
    
    @PostMapping(value = "/student/update/submit")
    public String updateSubmit(@ModelAttribute StudentModel student, Model model)
    {
    	 	//StudentModel student = new StudentModel (npm, name, gpa);
    		if (student != null)
    		{
         studentDAO.updateStudent(student); 
    		}
    		
            model.addAttribute("title", "Konfirmasi Update Student");
         return "success-update";
    }

    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title", "View All Student");
        
        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	 StudentModel student = studentDAO.selectStudent (npm);

         if (student != null) {
        	 	studentDAO.deleteStudent (npm);
                model.addAttribute("title", "Konfirmasi Delete Student");
             return "delete";
         } else {
             model.addAttribute("title", "Delete Student");
             model.addAttribute ("npm", npm);
             return "not-found";
         }
        
    }

}
