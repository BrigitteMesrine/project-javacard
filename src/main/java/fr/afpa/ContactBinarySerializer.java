package fr.afpa;

import java.util.ArrayList;
import java.util.List;

import fr.afpa.models.Contact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ContactBinarySerializer implements Serializer<Contact>, Deserializer<Contact> {

    @Override
    public void saveList(String filePath, List<Contact> contactsToSave) {
        try {
            // ouverture d'un flux de sortie vers le fichier "contacts.serial"
            FileOutputStream fos = new FileOutputStream(filePath);

            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                // sérialisation : écriture de l'objet dans le flux de sortie
                oos.writeObject(contactsToSave);
                // on vide le tampon
                oos.flush();

            } finally {
                // fermeture des flux
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // defined but must not be called when updating contact list
    // add single object to a List<> and call saveList instead
    @Override
    public void save(String filePath, Contact contact) {
        try {
            // ouverture d'un flux de sortie vers le fichier "contacts.serial"
            FileOutputStream file = new FileOutputStream(filePath);

            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream out = new ObjectOutputStream(file);
            try {
                // sérialisation : écriture de l'objet dans le flux de sortie
                out.writeObject(contact);
                // on vide le tampon
                out.flush();

            } finally {
                // fermeture des flux
                try {
                    out.close();
                } finally {
                    file.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public ArrayList<Contact> loadList(String filePath) {

        ArrayList<Contact> contacts = null;
        try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            // sonarlint signale également un warning ici
            contacts = (ArrayList<Contact>) in.readObject();
             
            in.close();
            file.close();

        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught from loadList(" + filePath + ")");
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return contacts;
    }



    @Override
    public Contact load(String filePath) {
        Contact contact = null;
        try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            contact = (Contact) in.readObject();
             
            in.close();
            file.close();

        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return contact;
    }

}
