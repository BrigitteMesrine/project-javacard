package fr.afpa;

import java.util.ArrayList;

import fr.afpa.models.Contact;

public class Test {
    public static void main(String[] args) {
        
        Contact contactTest = new Contact("Michel", "MICHEL", "0606060606", "miche@michel.com",
        "36 rue du trente-six", "33360", Contact.Gender.MALE, null, null, null, null);
        Contact contactTest2 = new Contact("Jean", "JEAN", "0707070707", "jean@jean.com",
        "37 rue du trente-sept", "33370", Contact.Gender.NON_BINARY, null, null, null, null);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(contactTest);
        contacts.add(contactTest2);

        ContactBinarySerializer contactBinarySerializer = new ContactBinarySerializer();
        contactBinarySerializer.saveList("contacts.serial", contacts);
    }
}
