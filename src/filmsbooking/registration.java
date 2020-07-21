package filmsbooking;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class registration {

    public void register() {
        System.out.println("                                               ***** Registeration*******");
        Scanner obj = new Scanner(System.in);
        System.out.println("please Enter your name");
        String name = obj.nextLine();

        System.out.println("\n\nplease Enter the passwoyrd?");
        String password = obj.nextLine();

        Session s = connection.controllers.getSessionFactory().openSession();
        Transaction tr = s.beginTransaction();

        pojos.Users user1 = new pojos.Users();
        user1.setName(name);
        user1.setPassword(password);
        user1.setRole("customer");
        s.save(user1);

        tr.commit();
        s.close();
        tr = null;
        System.out.println("successfully added please login to the system");
        login login = new login();
        login.login();
    }
}
