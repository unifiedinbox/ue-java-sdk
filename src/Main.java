import com.unificationengine.models.UEApp;
import com.unificationengine.models.UEUser;

/**
 * Created by deadlock on 3/30/16.
 */
public class Main {
    public static void main(String[] args) {

        UEApp app = new UEApp("b56063451547432d99111c91fd5d968b", "695590bcf875546bf85c6358d3512ef8");

        UEUser u = app.createUser();

        if (app.deleteUser(u.getUri() + "xaa"))
            System.out.println("Deleted");
//        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        JsonObject obj = new JsonObject();
//        obj.add("id",new JsonPrimitive(1234));
//d
//
//        JsonObject info = new JsonObject();
//        info.add("name", new JsonPrimitive("Hossam"));
//        info.add("age",new JsonPrimitive(51));
//
//        obj.add("info", info);
//        System.out.println(StringEscapeUtils.escapeJava(obj.toString()));

    }
}
