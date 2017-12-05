package com.example.finalproject.controllers;

import com.example.finalproject.entity.*;
import com.example.finalproject.repositories.*;
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

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("allclients", clientRepository.findAll());
        model.addAttribute("fittrain", trainerRepository.findAll());
        model.addAttribute("allexperiance", experianceRepository.findAll());
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
        Trainer trainer= new Trainer();
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


    @GetMapping("/addlocation")
    public String addlocationForm(Model model){
        model.addAttribute("location", new Location());
        return "locationform";
    }

    @PostMapping("/processlocationlist")
    public String processlocationForm(@Valid Location location, BindingResult result, Model model){
        {
            if(result.hasErrors()){
                return "locationform";
            }
            locationRepository.save(location);
            model.addAttribute("alllocation",locationRepository.findAll());
            return "locationlist";
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

    @GetMapping("/addappointment")
    private String addAppointment(Model model)
    {
        model.addAttribute("appointment", new Appointment());
        return "appointmentform";
    }

    @PostMapping("/processappointment")
    private String processAppointment(@Valid Appointment appointment, Model model)
    {
        model.addAttribute("allappointments", appointmentRepository.findAll());
        return "appointmentlist";
    }

    @GetMapping("/addTrainertoExperiance/{id}")                 //Experiance mapped by Trainer
    public String addStudents(@PathVariable("id") long experianceID, Model model)
    {

        model.addAttribute("allexperiance", experianceRepository.findOne(new Long(experianceID)));
        model.addAttribute("fittrain", trainerRepository.findAll());
        return "traineraddexperiance";
    }
    @PostMapping("/addTrainertoExperiance/{trainid}")
    public String addTrainertoExperiance(@RequestParam("experiances")
        String experianceID,@PathVariable("trainid")long trainID,
        @ModelAttribute("anexperiance") Experiance E, Model model)
    {
        Trainer T=trainerRepository.findOne(new Long(trainID));
        T.addExperiance(experianceRepository.findOne(new Long(experianceID)));
        trainerRepository.save(T);
        model.addAttribute("allexperiance", experianceRepository.findAll());
        model.addAttribute("fittrain", trainerRepository.findAll());
        return "redirect:/";
    }


}