package by.siegell.soa.clinic.ioc;

public interface Factory<T> {
    T get(String key) throws IoCException;
}
