package filmsbooking;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;


public class viewusers {

    public void viewuser() {
        Session s = connection.controllers.getSessionFactory().openSession();
       
        String viewuser = "from  Users ";
        Query viewq = s.createQuery(viewuser);
        Scanner obj = new Scanner(System.in);
        while (true) {

            List<pojos.Users> viewusers = viewq.list();
            System.out.println("Film List");
            int usercount = 1;
            System.out.println("id" + "       " + "Film");
            for (pojos.Users user : viewusers) {
                System.out.println(usercount + "     " + user.getName());
                usercount++;
            }

            System.out.println("Enter the id no to view films brought by user ");
            System.out.println("To go back enter 'E' ");
            String str = obj.nextLine();
            if (str.equals("E") || str.equals("e")) {
                break;
            } else {
                String st = "from Purchesedfilm where userid ='" + String.valueOf(viewusers.get(Integer.parseInt(str) - 1).getUserid()) + "'";
                Query quser = s.createQuery(st);
                List<pojos.Purchesedfilm> userfilms = quser.list();

                if (userfilms.isEmpty()) {
                    System.out.println("no films perchesed");
                }
                System.out.println("film list are");
                for (pojos.Purchesedfilm filmbyid : userfilms) {

                    pojos.Film newfilm = (pojos.Film) s.load(pojos.Film.class, Integer.parseInt(filmbyid.getFilmid()));
                    System.out.println(newfilm.getName());
                }
            }

        }
    }
}
