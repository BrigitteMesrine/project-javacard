// test class for serializazer

package fr.afpa;

import java.util.ArrayList;

import fr.afpa.models.Contact;

public class Test {
    public static void main(String[] args) {
        
        Contact contactTest = new Contact("Michel", "MICHEL", "0606060606", "miche@michel.com",
        "36 rue du trente-six", "33360", Contact.Gender.MALE, null, null, null, null);
        Contact contactTest2 = new Contact("Jean", "JEAN", "0707070707", "jean@jean.com",
        "37 rue du trente-sept", "33370", Contact.Gender.NON_BINARY, null, null, null, null);

        // add new contacts to this list, modify existing objects from this list
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(contactTest);
        contacts.add(contactTest2);

        // then overwrite serial file with updated ArrayList<>
        ContactBinarySerializer contactBinarySerializer = new ContactBinarySerializer(); // is there a way to make this static ?
        contactBinarySerializer.saveList("contacts.serial", contacts);

        contacts = contactBinarySerializer.loadList("contacts.serial");

        System.out.println(contacts.toString());
    }
}
