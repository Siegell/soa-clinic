package by.siegell.soa.clinic.imap;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityMap<T, Id> {
    private Map<Id, T> objects = new IdentityHashMap<>();

    public T getObject(Id id) {
        return objects.get(id);
    }

    public void putObject(Id id, T object) {
        objects.put(id, object);
    }

    public void deleteObject(Id id) {
        objects.remove(id);
    }
}
