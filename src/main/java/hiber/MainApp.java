package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("UserWithCar5", "Lastname5", "user5@mail.ru", new Car("Audi A3",347344334)));
      userService.add(new User("UserWithCar6", "Lastname6", "user6@mail.ru", new Car("Dodge Challenger", 324983434)));
      userService.add(new User("UserWithCar7", "Lastname7", "user7@mail.ru", new Car("Chevrolet Camaro",3409945)));
      userService.add(new User("UserWithCar8", "Lastname8", "user8@mail.ru", new Car("LADA PRIORA", 23453948)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      User user1 = userService.getCarOwner("Audi A3",347344334);
      User user2 = userService.getCarOwner("Dodge Challenger", 324983434);
      User user3 = userService.getCarOwner("Chevrolet Camaro",3409945);
      User user4 = userService.getCarOwner("LADA PRIORA", 23453948);
      context.close();
   }
}
