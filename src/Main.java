import com.unificationengine.lib.message.Message;
import com.unificationengine.lib.message.MessageLink;
import com.unificationengine.lib.message.MessageOptions;
import com.unificationengine.lib.message.ReceiverTypes;
import com.unificationengine.models.UEApp;
import com.unificationengine.models.UEConnection;
import com.unificationengine.models.UEUser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by deadlock on 3/30/16.
 */
public class Main {
    public static void main(String[] args) {

        UEApp app = new UEApp("b56063451547432d99111c91fd5d968b", "695590bcf875546bf85c6358d3512ef8");

        try {
            UEUser u = new UEUser("user://59cc6c5d-6bc8-4431-8ac0-5201e347b08d:ae600287-5943-4c0d-994f-62b3fcc2ceb1@");
            UEConnection uec = u.addConnection("conName", "facebook", "CAACEdEose0cBACUuDCesj7DMZCEgZBilF8cIoHN1Pl8snkNPjycL59ziltQowf2utbdW4oBSX7x0fNrs1jZA5oW42cdFzfUYFHCB4g6gOtBiDkkZCDWSsnvh8a53ZCfFj3cdv3i3YTZClCCr1NgMpf9U6NXI7Iy1de2lZB2jEmFf1ZBD7vOWulxT9X8AQk9R4pyTL46JTdtUEAZDZD");

            //Specify Message Options
            MessageOptions options = new MessageOptions();

            //Add a message receiver (My wall)
            options.addReceiver(ReceiverTypes.ME);

            //Add a message receiver (My Page)
            options.addReceiver(ReceiverTypes.PAGE, "PAGE_ID");

            Message m = new Message();

            //Message Subject
            m.setSubject("Subject");

            //Message Image
            m.setImage("https://raw.githubusercontent.com/plu/JPSimulatorHacks/master/Data/test.png");

            //Message Body
            m.setBody("Message Body");


            //Message Link
            MessageLink mLink = new MessageLink();
            mLink.setDesc("Link Description");
            mLink.setTitle("Link Title");
            mLink.setUri("http://google.com");

            m.setLink(mLink);

            options.setMessage(m);


            //Send Message
            ArrayList<String> urls = uec.sendMessage(options);
            System.out.println(Arrays.asList(urls.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
