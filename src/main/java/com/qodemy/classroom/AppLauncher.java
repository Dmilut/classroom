package com.qodemy.classroom;

import java.util.List;

import com.qodemy.classroom.config.AppConfig;
import com.qodemy.classroom.model.User;
import com.qodemy.classroom.service.UserService;
import com.qodemy.classroom.service.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppLauncher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        // Add User
        User user1 = new User();
        user1.setFirstName("Ivan");
        user1.setLastName("Ivanov");

        userService.saveUser(user1);

        // Get Users
        List<User> users = userService.findAllUsers();
        for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println();
        }

        context.close();
    }
}
