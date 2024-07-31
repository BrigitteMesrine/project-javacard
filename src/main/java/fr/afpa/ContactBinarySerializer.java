package fr.afpa;

import java.util.List;

import fr.afpa.models.Contact;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ContactBinarySerializer implements Serializer<Contact>{

    @Override
    public void saveList(String filePath, List<Contact> contactsToSave) {
        try {
            // ouverture d'un flux de sortie vers le fichier "contacts.serial"
            FileOutputStream fos = new FileOutputStream("contacts.serial");

            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            try {
                // sérialisation : écriture de l'objet dans le flux de sortie
                oos.writeObject(contactsToSave); 
                // on vide le tampon
                oos.flush();
                System.out.println(contactsToSave + " a ete serialise");
            } finally {
                //fermeture des flux
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void save(String filePath, Contact contact) {
        try {
            // ouverture d'un flux de sortie vers le fichier "contacts.serial"
            FileOutputStream fos = new FileOutputStream("contacts.serial");

            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            try {
                // sérialisation : écriture de l'objet dans le flux de sortie
                oos.writeObject(contact); 
                // on vide le tampon
                oos.flush();
                System.out.println(contact + " a ete serialise");
            } finally {
                //fermeture des flux
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
