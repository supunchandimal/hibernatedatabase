package filmsbooking;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Purchesedfilm;
import pojos.Users;

public class buyfilms {

    public void buy(Users user) {
        while (true) {
            Session s = connection.controllers.getSessionFactory().openSession();
            String hql = "from  Film where delornot= 1 ";
            Query q = s.createQuery(hql);
            Transaction tr = s.beginTransaction();
            getaveragerating getrate = new getaveragerating();
            List<pojos.Film> filmlist = q.list();
            System.out.println("Film List");
            System.out.println("Film no    Film name        director          rating");
            int count =1;
            for (pojos.Film film : filmlist) {
                System.out.println(count+ "  " +film.getName()+"        "+ film.getDirector()+"              "+getrate.getrating(film.getFilmid()));
                count++;
            }
            
            System.out.println("what film you need to buy \n please enter the no or enter e to exit");
            Scanner obj = new Scanner(System.in);
            String i = obj.nextLine();
            
            if(i.equals("e"))break;
            Purchesedfilm film = new Purchesedfilm();
            film.setFilmid(String.valueOf(filmlist.get(Integer.parseInt(i)-1).getFilmid()));
            film.setUserid(String.valueOf(user.getUserid()));
            s.save(film);
            System.out.println("successfully brought");
            tr.commit();
            s.close();
            System.out.println("do you want to buy a another one?(y/N)");
            Scanner obj2 = new Scanner(System.in);
            String choise = obj2.nextLine();
            if(choise.equals("n")){
                break;
                
            }
            
        }
    }

}
