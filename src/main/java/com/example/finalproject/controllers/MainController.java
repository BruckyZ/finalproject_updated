package com.example.finalproject.controllers;

import com.example.finalproject.entity.*;
import com.example.finalproject.repositories.ClientRepository;
import com.example.finalproject.repositories.ExperianceRepository;
import com.example.finalproject.repositories.SpecialityRepository;
import com.example.finalproject.repositories.TrainerRepository;
import com.example.finalproject.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class MainController {

    @Autowired
    UserService userService;


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    SpecialityRepository specialityRepository;

    @Autowired
    ExperianceRepository experianceRepository;

    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("allclients", clientRepository.findAll());
        model.addAttribute("fittrain", trainerRepository.findAll());
        return "index";
    }
    @RequestMapping("/home")
    public String showHomePage(){
        return "home";
    }

    @RequestMapping("/login")
    private String login(Model model)
    {
        return "login";
    }

    @GetMapping("/register")
    private String register(Model model)
    {   model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model)
    {

        model.addAttribute("user", user);

        if (result.hasErrors())
        {
            return "registration";
        }
        else
        {
            userService.saveAdmin(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "/login";
    }

    @GetMapping("/addtrainer")
    private String addtrainer(Model model)
    {
        model.addAttribute("trainer", new Trainer());
        return "trainerform";
    }

    @PostMapping("/processtrainer")
    private String processtrainer(@Valid Trainer trainer, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "trainerform";
        }
        trainerRepository.save(trainer);
        model.addAttribute("fittrain", trainerRepository.findAll());
        return "trainerlist";
    }

    @GetMapping("/addspeciality")
    private String addspeciality(Model model)
    {
        model.addAttribute("speciality", new Speciality());
        return "specialityform";
    }

    @PostMapping("/processspeciality")
    private String processspeciality(@Valid Speciality speciality, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "specialityform";
        }
        specialityRepository.save(speciality);
        model.addAttribute("fitspeciality", specialityRepository.findAll());
        return "specialitylist";
    }


    @GetMapping("/addclient")
    public String addRecordForm(Model model){
        model.addAttribute("client", new Client());
        return "addclientform";
    }

    @PostMapping("/processclientlist")
    public String processClientForm(@Valid Client client, BindingResult result, Model model){
        {
            if(result.hasErrors()){
                return "addclientform";
            }
            clientRepository.save(client);
            model.addAttribute("allclients",clientRepository.findAll());
            return "clientlist";
        }
    }

    @RequestMapping("/showclientlist")
    public String showclientlisting(){
        return "clientlist";
    }


    @GetMapping("/addexperiance")
    public String addexperianceForm(Model model){
        model.addAttribute("experiance", new Experiance());
        return "experianceform";
    }

    @PostMapping("/processexperiancelist")
    public String processexperianceform(@Valid Experiance experiance, BindingResult result, Model model){
        {
            if(result.hasErrors()){
                return "experianceform";
            }
            experianceRepository.save(experiance);
            model.addAttribute("allexperiance",experianceRepository.findAll());
            return "experiancelist";
        }
    }


    @GetMapping("/addTrainertoExperiance/{id}")                 //Experiance mapped by Trainer
    public String addStudents(@PathVariable("id") long trainerid, Model model)
    {

        model.addAttribute("trainer", trainerRepository.findOne(new Long(trainerid)));
        model.addAttribute("experiancelist", experianceRepository.findAll());
        return "traineraddexperiance";
    }
    @PostMapping("/addTrainertoExperiance")
    public String addTrainertoExperiance(HttpServletRequest request, Model model)
    {
        String trainerId = request.getParameter("trainerId");
        String experianceid = request.getParameter("experianceid");
        Trainer trainer=trainerRepository.findOne(new Long(trainerId));
        trainer.addExperiance(experianceRepository.findOne(new Long(experianceid)));
        trainerRepository.save(trainer);
        model.addAttribute("experiancelist", experianceRepository.findAll());
        model.addAttribute("trainerlist", trainerRepository.findAll());
        return "redirect:/";
    }


}