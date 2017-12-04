package com.example.finalproject.dataloader;



import com.example.finalproject.entity.Client;
import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.Trainer;
import com.example.finalproject.entity.User;
import com.example.finalproject.repositories.ClientRepository;
import com.example.finalproject.repositories.RoleRepository;
import com.example.finalproject.repositories.TrainerRepository;
import com.example.finalproject.repositories.UserRepository;
import com.example.finalproject.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository appRoles;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService userService;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    ClientRepository clientRepository;



    @Override
    public void run(String... strings) throws Exception {

        Role aRole = new Role();
        aRole.setRole("ADMIN");
        appRoles.save(aRole);
        System.out.println("Admin role has been created");


        User user = new User();
        user.setPassword("pass");
        user.setUsername("admin");
        user.setEmail("person@person.com");
        user.addRole(appRoles.findByRole("ADMIN"));
        userRepo.save(user);

        Client client = new Client("Jim", "Gray", "3016310818", "jim@montgormerycollege.edu", "Male");
        clientRepository.save(client);

        Client client2 = new Client("Rachel","Turner","3457896081","Sally@gmail.com","Female");
        clientRepository.save(client2);


        Trainer trainer = new Trainer("Sally", "Turner", "3457896081", "Sally@gmail.com", "5 Stars", "female");
        trainerRepository.save(trainer);

        Trainer trainer2 = new Trainer("Jack","Pine","3017345681","Jack@gmail.com","5 Stars","Male");
        trainerRepository.save(trainer2);
    }
}
