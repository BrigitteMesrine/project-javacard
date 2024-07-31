package fr.afpa;

import java.util.List;

public interface Serializer<T> {

    public void saveList(String filePath, List<T> objectsToSave);
    public void save(String filePath, T object);
    
}
