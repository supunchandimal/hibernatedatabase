
package filmsbooking;

import java.util.Scanner;
import pojos.Users;

public class customerpage {

    public void view(Users user) {
        
        Scanner obj = new Scanner(System.in);
        System.out.println("                                                    welcome "+user.getName());
        while (true) {
            System.out.println("     1.  Buy  Film");
            System.out.println("     2.  View my Films ");
            System.out.println("     3.  Exits");
                   
            String choise = obj.nextLine();
            if(choise.equals("1")){
                buyfilms buy= new buyfilms();
                buy.buy(user);
             
            }else if(choise.equals("2")){
                System.out.println("my products");
                viewmyfilms view = new  viewmyfilms();
                view.view(user);
            }else if(choise.equals("3")){
                break;
            }else{
                System.out.println("invalid argument");
            }
                   
        }
    }
}
