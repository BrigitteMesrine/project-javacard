package fr.afpa;

import fr.afpa.models.Contact;

public class ContactVerifier {

    public static boolean verifyContact(Contact contact) {

        boolean isContact = false;

        if (contact.getFirstName() != null && contact.getFirstName() != null && contact.getPersoPhone() != null
                && contact.getEmail() != null && contact.getAddress() != null && contact.getZipCode() != null
                && contact.getGender() != null) {
            isContact = true;
        }

        return isContact;
    }

    public static boolean verifyEmail(Contact contact) {

        boolean isEmail = false;

        return isEmail;
    }
}
