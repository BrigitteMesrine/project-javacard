package fr.afpa;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.github.cliftonlabs.json_simple.*;

import fr.afpa.models.Contact;

public class ContactJSONSerializer implements Serializer <Contact> {

    @Override
    public void saveList(String filePath, List<Contact> contactsToSave) {
        
        try (FileWriter fileWriter = new FileWriter(filePath)) {

            // convert object to json and write to file
            Jsoner.serialize(contactsToSave, fileWriter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(String filePath, Contact contact) {

        try (FileWriter fileWriter = new FileWriter(filePath)) {

            // convert object to json and write to file
            Jsoner.serialize(contact, fileWriter);

        } catch (IOException e) {
            // sonarlint constate que c'est une erreur générique mais uqi n'affecte pas le code
            throw new RuntimeException(e);
        }

    }

}
