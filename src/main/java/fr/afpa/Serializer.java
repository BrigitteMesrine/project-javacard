package fr.afpa;

import java.util.List;

public interface Serializer<T> {

    public void saveList(String filePath, List<T> objectsToSave);

    // save for a single Object must not be called by ContactBinarySerializer for
    // it is better to add single objet to a list (either after addition or modification)
    // and call saveListMethod
    public void save(String filePath, T object);

}
