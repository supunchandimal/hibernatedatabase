package filmsbooking;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Ratings;
import pojos.Users;

public class viewmyfilms {

    public void view(Users user) {
        Session s = connection.controllers.getSessionFactory().openSession();
        String hql = "from  Purchesedfilm where userid='" + user.getUserid() + "'";
        Query q = s.createQuery(hql);


        List<pojos.Purchesedfilm> films = q.list();
        List<pojos.Film> filmswithname = new ArrayList<>();
        System.out.println("Film List");
        int count = 1;
        if (!films.isEmpty()) {
            for (pojos.Purchesedfilm film : films) {
                pojos.Film film1 = (pojos.Film) s.load(pojos.Film.class, Integer.parseInt(film.getFilmid()));

                if (film1 != null) {
                    filmswithname.add(film1);
                }

            }

            if (!filmswithname.isEmpty()) {

                while (true) {
                    System.out.println("id" + "       " + "films");
                    for (pojos.Film f : filmswithname) {

                        System.out.println(count + "      " + f.getName());
                        count++;
                    }
                    count=1;
                    System.out.println("do you want to rate a filme (Y/N)");
                    Scanner obj = new Scanner(System.in);
                    String i = obj.nextLine();
                    if (i.equals("y")) {
                        System.out.println("Enter the film no ");
                        Scanner obj2 = new Scanner(System.in);
                        int j = obj2.nextInt();
                         Transaction tr = s.beginTransaction();
                        System.out.println("what is the you rating that film out of 10");
                          Scanner obj3 = new Scanner(System.in);
                          int k = obj3.nextInt();
                          Ratings rate = new  Ratings();
                          rate.setUserid(String.valueOf(user.getUserid()));
                          rate.setRating(String.valueOf(filmswithname.get(j-1).getFilmid()));
                          rate.setDelornot(k);
                          
                          s.save(rate);
                         tr.commit();
                          
                                  
                          
                    }else{
                        break;
                    }
                }

            }
        }
        
        
    }
}
