package filmsbooking;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Film;
import pojos.FilmGen;

public class adminclass {

    public void run() {
        Scanner obj = new Scanner(System.in);

        while (true) {
            
            System.out.println("************************************************************************************************************");
            System.out.println("\n                                                 Welcome admin");
            System.out.println("\n********************************************************************************************************");
            

            System.out.println("\n\n what is your choise?\n");
            System.out.println("1. film\n");
            System.out.println("2. users\n");

            System.out.println("3.exit");

            int desire = obj.nextInt();

            switch (desire) {
                case 1:

                    Session s = connection.controllers.getSessionFactory().openSession();
                    String hql = "from  Film where delornot=1 ";
                    Query q = s.createQuery(hql);

                    List<pojos.Film> filmlist = q.list();
                    System.out.println("Film List");
                    System.out.println("Film no     film name   rating");
                    getaveragerating rates = new getaveragerating();
                    int count = 1;
                    for (pojos.Film film : filmlist) {
                        System.out.println(count + "      " + film.getName() + "    " + rates.getrating(film.getFilmid()));
                        count++;
                    }

                    System.out.println("\n1.add new film");
                    System.out.println("2.delete film");
                    System.out.println("3.update film");
                    int choise = obj.nextInt();
                    switch (choise) {
                        case 1:

                            Scanner object = new Scanner(System.in);
                            while (true) {
                                System.out.println("film name");
                                String filmname = object.nextLine();

                                System.out.println("Director ");
                                String director = object.nextLine();

                                System.out.println("actor");
                                String actor = object.nextLine();

                                System.out.println("runtime");
                                String runtime = object.nextLine();

                                pojos.Film newfilm = new Film();

                                newfilm.setName(filmname);
                                newfilm.setDirector(director);
                                newfilm.setRuntime(runtime);
                                newfilm.setActor(actor);
                                newfilm.setDelornot(1);

                                Transaction tr = s.beginTransaction();

                                s.save(newfilm);
                                tr.commit();
                                tr = null;
                                System.out.println("film added");

                                System.out.println("what are the genres in this film");
                                System.out.println("after adding geres enter 1 to exit ");
                                while (true) {
                                    String hql1 = "from  Film e where e.name='" + newfilm.getName() + "'";

                                    Query q1 = s.createQuery(hql1);

                                    pojos.Film usr = (pojos.Film) q1.uniqueResult();
                                    FilmGen filmgen = new FilmGen();

                                    String gen = object.nextLine();
                                    if (gen.equals("1")) {
                                        break;
                                    }
                                    filmgen.setFilmid(String.valueOf(usr.getFilmid()));
                                    filmgen.setGenre(gen);
                                    Transaction tr1 = s.beginTransaction();
                                    s.save(filmgen);
                                    tr1.commit();
                                    tr1 = null;

                                }

                                System.out.println("\n \n do you need to add another film (Y/N)");
                                String exitornot = object.nextLine();

                                if (exitornot.equals("y")) {

                                } else {

                                    break;
                                }

                                s.close();

                            }

                            break;
                        case 2:

                            Scanner obj3 = new Scanner(System.in);

                            Transaction tr = s.beginTransaction();
                            String hql4 = "from  Film where delornot=1 ";
                            Query q4 = s.createQuery(hql4);

                            List<pojos.Film> filmlist4 = q4.list();
                            System.out.println("Film List");
                            int count2 = 1;
                            for (pojos.Film film : filmlist4) {
                                System.out.println(count + "     " + film.getName());
                                count2++;
                            }

                            System.out.println("\n\n\nPlease Enter the film no to delete?");

                            int i = obj3.nextInt();

                            pojos.Film film2 = (pojos.Film) s.load(pojos.Film.class, filmlist.get(i - 1).getFilmid());
                            film2.setDelornot(0);
                            s.update(film2);
                            tr.commit();
                            break;
                        case 3:
                            Scanner objupdate = new Scanner(System.in);
                            Transaction tr4 = s.beginTransaction();
                            String hqlupdate = "from  Film where delornot=1 ";
                            Query qupdate = s.createQuery(hqlupdate);

                            List<pojos.Film> filmlistforupdate = qupdate.list();
                            System.out.println("Film List");
                            int countupdate = 1;
                            for (pojos.Film film : filmlistforupdate) {
                                System.out.println(countupdate + "     " + film.getName());
                                countupdate++;
                            }

                            System.out.println("\n\n\nPlease Enter the film no to update?");

                            int iupa = objupdate.nextInt();

                            pojos.Film film = (pojos.Film) s.load(pojos.Film.class, filmlistforupdate.get(iupa - 1).getFilmid());

                            System.out.println("1.film name " + film.getName());
                            System.out.println("2.film director " + film.getDirector());
                            System.out.println("3.film actor " + film.getActor());
                            System.out.println("4.film runtime " + film.getRuntime());

                            System.out.println("Enter the no of the field you want to update");

                            int t = objupdate.nextInt();

                            System.out.println("Enter the new data ");

                            Scanner scn = new Scanner(System.in);
                            String updatedata = scn.nextLine();

                            switch (t) {
                                case 1:
                                    film.setName(updatedata);
                                    break;
                                case 2:
                                    film.setDirector(updatedata);
                                    break;
                                case 3:
                                    film.setActor(updatedata);
                                    break;
                                case 4:
                                    film.setRuntime(updatedata);
                                    break;
                                default:
                                    System.out.println("invlaid");
                                    break;

                            }

                            s.update(film);
                            tr4.commit();
                            break;
                    }
                    break;
                case 2:

                    viewusers view = new viewusers();
                    view.viewuser();
                    break;
                case 3:

                    return;
                default:
                    System.out.println("invalid argument");
            }

        }
    }

}
