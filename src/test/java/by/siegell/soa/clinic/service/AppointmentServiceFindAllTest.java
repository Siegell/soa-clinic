package by.siegell.soa.clinic.service;

import by.siegell.soa.clinic.ioc.IoCConfigurer;
import by.siegell.soa.clinic.ioc.IoCContainer;
import by.siegell.soa.clinic.ioc.IoCException;

public class AppointmentServiceFindAllTest {
    public static void main(String[] args) throws IoCException {
        IoCConfigurer.configure();
        try (IoCContainer ioc = new IoCContainer()) {
            AppointmentService appointmentService = ioc.get(AppointmentService.class);
            appointmentService.findAll().forEach(System.out::println);
        }
    }
}
