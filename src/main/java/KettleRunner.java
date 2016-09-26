import com.google.common.base.Strings;
import org.ops4j.pax.url.mvn.MavenResolver;
import org.ops4j.pax.url.mvn.MavenResolvers;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

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
      new KettleRunner().runTransformation(args[0]);
   }

   public File loadFile(String filename) {
      return new File(filename);
   }

   public void runTransformation(String filename) {
      assert !Strings.isNullOrEmpty(filename);

      try {
         KettleEnvironment.init();

         TransMeta transMeta = new TransMeta(filename);
         Trans trans = new Trans(transMeta);

         trans.execute(null); // You can pass arguments instead of null.
         trans.waitUntilFinished();
         if (trans.getErrors() > 0) {
            throw new RuntimeException("There were errors during transformation execution.");
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public void runTransformationMvn(String mvnUrl) {
      Dictionary<String, String> dict = new Hashtable<String, String>();
      dict.put("org.ops4j.pax.url.mvn.localRepository", System.getProperty("java.io.tmpdir"));
      MavenResolver mavenResolver = MavenResolvers.createMavenResolver(dict, null);
      try {
         File file = mavenResolver.resolve(mvnUrl);
         System.out.println(file.getAbsolutePath());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
