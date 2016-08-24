import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * Created by timofeev-aa-090513 on 24.08.16.
 *
 * @author timofeev-aa-090513
 */
public class KettleRunner {
   public static void main(String[] args) {
      if (args == null || args.length != 1) {
         throw new RuntimeException("Transformation filename should be passed as a single agrument.");
      }
      try {
         KettleEnvironment.init();

         TransMeta transMeta = new TransMeta(args[0]);
         Trans trans = new Trans(transMeta);

         trans.execute(null); // You can pass arguments instead of null.
         trans.waitUntilFinished();
         if (trans.getErrors() > 0) {
            throw new RuntimeException("There were errors during transformation execution.");
         }
      } catch (Exception e) {
         // TODO Put your exception-handling code here.
         System.out.println(e);
      }
   }
}
