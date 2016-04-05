import com.unificationengine.lib.message.Message;
import com.unificationengine.lib.message.MessageLink;
import com.unificationengine.lib.message.MessageOptions;
import com.unificationengine.lib.message.ReceiverTypes;
import com.unificationengine.models.UEApp;
import com.unificationengine.models.UEConnection;
import com.unificationengine.models.UEUser;

/**
 * Created by deadlock on 3/30/16.
 */
public class Main {
    public static void main(String[] args) {

        UEApp app = new UEApp("b56063451547432d99111c91fd5d968b", "695590bcf875546bf85c6358d3512ef8");
        UEUser u;
        try {
            u = new UEUser("user://59cc6c5d-6bc8-4431-8ac0-5201e347b08d:ae600287-5943-4c0d-994f-62b3fcc2ceb1@");
            UEConnection uec = u.addConnection("conName", "facebook", "CAACEdEose0cBAJxc7g5cxXWvNECRUxAnOZAJqZA3jfZBAEOO4qXh1uZAhkFJwpUC9791xEz4hEYz9xAeANlQJmUYVAQZAC3korVXP1130Rnf51MFM2dFSZAZBHyY8i94j0gCqPYjiv2YkPIiXqNeyJtu4NcTtZAOF0B0XCJRF11XzGuke1cM1WB11ZBb88i0PEv8frX9YRne6zAZDZD");

            MessageOptions options = new MessageOptions();
            options.addReceiver(ReceiverTypes.ME);
//            options.addReceiver(ReceiverTypes.PAGE, "PAGE_ID");

            Message m = new Message();
            m.setSubject("Subject");
            m.setImage("https://raw.githubusercontent.com/plu/JPSimulatorHacks/master/Data/test.png");
            m.setBody("Message Body");


            MessageLink mLink = new MessageLink();
            mLink.setDesc("Link Description");
            mLink.setTitle("Link Title");
            mLink.setUri("http://google.com");

            m.setLink(mLink);

            options.setMessage(m);


            uec.sendMessage(options);
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
