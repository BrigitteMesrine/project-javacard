package fr.afpa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.github.cliftonlabs.json_simple.*;

import fr.afpa.models.Contact;

public class ContactJSONSerializer implements Serializer<Contact> {

    @Override
    public void saveList(String filePath, List<Contact> contactstoSave) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveList'");
    }

    @Override
    public void save(String filePath, Contact contact) {

        try (FileWriter fileWriter = new FileWriter(filePath)) {

            // convert object to json and write to file
            Jsoner.serialize(contact, fileWriter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
