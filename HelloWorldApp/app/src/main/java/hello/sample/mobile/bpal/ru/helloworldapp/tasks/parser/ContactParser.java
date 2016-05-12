package hello.sample.mobile.bpal.ru.helloworldapp.tasks.parser;

import org.json.JSONException;
import org.json.JSONObject;

import hello.sample.mobile.bpal.ru.helloworldapp.data.Contact;

/**
 * Created by Topexpert on 12.05.2016.
 */
public class ContactParser {

    public Contact parseContcat(JSONObject jsonObject) throws JSONException {
        Contact contact = new Contact();
        contact.name = jsonObject.getString("name");
        contact.lastName = jsonObject.getString("lastName");

        return contact;
    }
}
