
package filmsbooking;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class getaveragerating {
  public float getrating(int filmid){
      float average= 0;
      int total=0;
       Session s = connection.controllers.getSessionFactory().openSession();
            String hql = "from  Ratings where rating='"+filmid+"'";
            Query q = s.createQuery(hql);
            Transaction tr = s.beginTransaction();

            List<pojos.Ratings> filmlist = q.list();
            
            int count =0;
            for (pojos.Ratings rating : filmlist) {
               
                total = total+rating.getDelornot();
               
                count++;
            }
            if(count>0){
            average = total/count;
            }
           
      return average;        
  }   
}
