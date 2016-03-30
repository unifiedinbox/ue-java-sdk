import com.unificationengine.lib.AppKeychain;
import com.unificationengine.utils.UERequest;

/**
 * Created by deadlock on 3/30/16.
 */
public class Main {
    public static void main(String[] args) {

        AppKeychain appKeychain = new AppKeychain("b56063451547432d99111c91fd5d968b", "695590bcf875546bf85c6358d3512ef8");

        UERequest.fetch("user/list", appKeychain, null);
//        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        JsonObject obj = new JsonObject();
//        obj.add("id",new JsonPrimitive(1234));
//
//
//        JsonObject info = new JsonObject();
//        info.add("name", new JsonPrimitive("Hossam"));
//        info.add("age",new JsonPrimitive(51));
//
//        obj.add("info", info);
//        System.out.println(StringEscapeUtils.escapeJava(obj.toString()));

    }
}
