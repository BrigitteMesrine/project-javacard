package fr.afpa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.afpa.models.Contact;

public class ContactVCardSerializer implements Serializer<Contact> {

    @Override
    public void saveList(String filePath, List<Contact> contactsToSave) {
        try {
            File vcfFile = new File("allcontacts" + filePath);
            FileWriter fw = new FileWriter(vcfFile);
            for (Contact contact : contactsToSave) {
                // converting Gender constants to chars as Strings
                String gender = "";
                switch (contact.getGender()) {
                    case Contact.Gender.MALE:
                        gender = "M";
                        break;

                    case Contact.Gender.FEMALE:
                        gender = "F";
                        break;

                    case Contact.Gender.NON_BINARY:
                        gender = "NB";
                        break;

                    default:
                        break;
                }

                // format birthdate
                String bDate = null;
                if (contact.getBirthDate() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    bDate = contact.getBirthDate().format(formatter);
                }

                fw.write("BEGIN:VCARD\r\n");
                fw.write("VERSION:3.0\r\n");
                fw.write("N:" + contact.getLastName() + ";" + contact.getFirstName() + "\r\n");
                fw.write("FN:" + contact.getFirstName() + " " + contact.getLastName() + "\r\n");
                fw.write("TEL;TYPE=WORK,VOICE:" + contact.getProPhone() + "\r\n");
                fw.write("TEL;TYPE=HOME,VOICE:" + contact.getPersoPhone() + "\r\n");
                fw.write("ADR;TYPE=WORK:;;" + contact.getAddress() + ";" + contact.getZipCode() + "\r\n");
                fw.write("EMAIL;TYPE=PREF,INTERNET:" + contact.getEmail() + "\r\n");
                fw.write("GENDER:" + gender + "\r\n");
                fw.write("BDAY:" + bDate + "\r\n");
                fw.write("NICKNAME:" + contact.getPseudo() + "\r\n");
                fw.write("URL:" + contact.getGitLink() + "\r\n");
                fw.write("END:VCARD\r\n");
                fw.write("\r\n");
            }
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void save(String filePath, Contact contact) {

        // converting Gender constants to chars as Strings
        String gender = "";
        switch (contact.getGender()) {
            case Contact.Gender.MALE:
                gender = "M";
                break;

            case Contact.Gender.FEMALE:
                gender = "F";
                break;

            case Contact.Gender.NON_BINARY:
                gender = "NB";
                break;

            default:
                break;
        }

        // format birthdate
        String bDate = null;
        if (contact.getBirthDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            bDate = contact.getBirthDate().format(formatter);
        }

        try {
            File vcfFile = new File(contact.getLastName() + filePath);
            FileWriter fw = new FileWriter(vcfFile);
            fw.write("BEGIN:VCARD\r\n");
            fw.write("VERSION:3.0\r\n");
            fw.write("N:" + contact.getLastName() + ";" + contact.getFirstName() + "\r\n");
            fw.write("FN:" + contact.getFirstName() + " " + contact.getLastName() + "\r\n");
            fw.write("TEL;TYPE=WORK,VOICE:" + contact.getProPhone() + "\r\n");
            fw.write("TEL;TYPE=HOME,VOICE:" + contact.getPersoPhone() + "\r\n");
            fw.write("ADR;TYPE=WORK:;;" + contact.getAddress() + ";" + contact.getZipCode() + "\r\n");
            fw.write("EMAIL;TYPE=PREF,INTERNET:" + contact.getEmail() + "\r\n");
            fw.write("GENDER:" + gender + "\r\n");
            fw.write("BDAY:" + bDate + "\r\n");
            fw.write("NICKNAME:" + contact.getPseudo() + "\r\n");
            fw.write("URL:" + contact.getGitLink() + "\r\n");
            fw.write("END:VCARD\r\n");
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
