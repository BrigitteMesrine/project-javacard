package fr.afpa;

import java.util.List;

import com.github.cliftonlabs.json_simple.*;

import fr.afpa.models.Contact;

public class ContactJSONSerializer implements Serializer<Contact> {

    @Override
    public void saveList(String filePath, List<Contact> objectsToSave) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveList'");
    }

    @Override
    public void save(String filePath, Contact object) {
        JsonArray list = new JsonArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");
        System.out.println(list);

    }

}
