
package filmsbooking;

import java.util.Scanner;


public class Filmsbooking {

   
    public static void main(String[] args) {

        Scanner obj = new Scanner(System.in);

        while (true) {
           
            System.out.println("*********************************************************************************************************************");

            System.out.println("                                            film sale ");
            System.out.println("**********************************************************************************************************************");
          
            System.out.println("please Enter the option");
            System.out.println("1.login");
            System.out.println("2.Register");
            String haveaccount = obj.nextLine();

            if (haveaccount.equals("1")) {
                login login = new login();
                login.login();

            } else {
               registration regist= new registration();
               regist.register();
            }
            System.out.flush();
        }

    }

}
