import com.unificationengine.models.UEApp;
import com.unificationengine.models.UEConnection;
import com.unificationengine.models.UEUser;

/**
 * Created by deadlock on 3/30/16.
 */
public class Main {
    public static void main(String[] args) {

        UEApp app = new UEApp("b56063451547432d99111c91fd5d968b", "695590bcf875546bf85c6358d3512ef8");
        UEUser u = null;
        try {
            u = new UEUser("user://59cc6c5d-6bc8-4431-8ac0-5201e347b08d:ae600287-5943-4c0d-994f-62b3fcc2ceb1@");
            UEConnection uec = u.addConnection("cnname", "facebook", "CAACEdEose0cBAINyhwZBwRXFTCV1WxUxBFq4q2GSKzXrs7jsGZBvtiZArHwUMjolZB0SZAz5qpJh6tkw4J1Mkdb5iCKZCRQ8ZC542MixrSBGUQAzibyklmegWIvDmjZAOnWfZCqcSe0badXrcRAikCOGRYShgzD89qVXvI3YZBsyMjerZAjprq1a4ZCd6mQg7zYpjmaZCaMDDd0JyXwZDZD");
            System.out.println(uec);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
