// test class for serializazer

package fr.afpa;

import java.time.LocalDate;
import java.util.ArrayList;

import fr.afpa.models.Contact;

public class Test {
    public static void main(String[] args) {
        
        Contact contactTest = new Contact(null, "MICHEL", "0606060606", "miche@michel.com",
        "36 rue du trente-six", "33360", Contact.Gender.MALE, null, null, null, null);
        Contact contactTest2 = new Contact("Jean", "JEAN", "0707070707", "jean@jean.com",
        "37 rue du trente-sept", "33370", Contact.Gender.NON_BINARY, LocalDate.of(1985, 10, 26), null, null, null);
        Contact contactTest3 = new Contact("Pierre", "PIERRE", "0708080808", "pierre@caillou.com",
        "38 rue du trente-huit", "33380", Contact.Gender.FEMALE, LocalDate.of(1985, 10, 26), null, null, null);

        // add new contacts to this list, modify existing objects from this list
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(contactTest);
        contacts.add(contactTest2);
        contacts.add(contactTest3);

        // then overwrite serial file with updated ArrayList<>
        ContactBinarySerializer contactBinarySerializer = new ContactBinarySerializer(); // is there a way to make this static ?
        contactBinarySerializer.saveList("contacts.serial", contacts);

        contacts = contactBinarySerializer.loadList("contacts.serial");
        // Contact contact = null;
        // contact = contactBinarySerializer.load("contacts.serial");
        ContactVCardSerializer contactVCardSerializer = new ContactVCardSerializer();
        contactVCardSerializer.saveList(".vcf", contacts);
        ContactJSONSerializer contactJSONSerializer = new ContactJSONSerializer();
        contactJSONSerializer.save("contacts.json", contactTest);


        System.out.println(contacts.toString() + "\"");
        
    }
}
