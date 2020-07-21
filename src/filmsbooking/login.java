package filmsbooking;

import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;

public class login {

    public void login() {
        System.out.flush();

        
        System.out.println("                                         **********Login*******");
        
        Scanner obj = new Scanner(System.in);
        System.out.println("please Enter the name");
        String name = obj.nextLine();

        System.out.println("\n\nplease Enter the password");
        String password = obj.nextLine();

        if (name.equals("admin") && password.equals("admin@123")) {
            adminclass admin = new adminclass();
            admin.run();
        } else {
            System.out.println("wait");
            Session s = connection.controllers.getSessionFactory().openSession();
            String hql = "from  Users e where e.name='" + name + "' and e.password='" + password + "' ";
            Query q = s.createQuery(hql);

            pojos.Users usr = (pojos.Users) q.uniqueResult();
            if (usr != null) {
               
                customerpage customer = new customerpage();
                customer.view(usr);

            }

        }
    }
}
