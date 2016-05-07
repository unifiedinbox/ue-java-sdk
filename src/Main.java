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

//This is the java class we use to test the whole sdk. It's there just as an example of usage.


public class Main {
    public static void main(String[] args) {

        UEApp app = new UEApp("b56063451547432d99111c91fd5d968b", "695590bcf875546bf85c6358d3512ef8");

        try {
            UEUser u = new UEUser("user://59cc6c5d-6bc8-4431-8ac0-5201e347b08d:ae600287-5943-4c0d-994f-62b3fcc2ceb1@");

            //We need to put the facebook access token below
            UEConnection uec = u.addConnection("conName", "facebook", "CAACEdEose0cBAH2DsJsMJHfKiClePb4ZAHsRq6DDCZCkI4FULnY4cJpt9lZBmoQ6Mw7OM1l1uZAsnhZCmfGsryfOEdHdfZCHhYSKm9w0qjqvN4x5hq6BZAzQIf6xFi83zFAeZB0DyINtY6uZCsZAonkwmjSLB3kwDf45D2XdeA7jCatNwoXHPXWW3gXDl1mvaGyX24qPjQ2C1M9QZDZD");

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
            m.setBody("Hi Emy!");


            //Message Link
            MessageLink mLink = new MessageLink();
            mLink.setDesc("Hi Emy, this is desc");
            mLink.setTitle("Hi Emy this is title");
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
