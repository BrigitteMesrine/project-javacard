package fr.afpa;

import java.util.ArrayList;

public interface Deserializer<T> {

    public ArrayList<T> loadList(String filePath);
    public T load(String filePath);
    
}
