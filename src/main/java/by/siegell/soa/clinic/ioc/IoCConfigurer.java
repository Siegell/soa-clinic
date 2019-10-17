package by.siegell.soa.clinic.ioc;

import java.util.HashMap;
import java.util.Map;

public class IoCConfigurer {
    public static void configure() throws IoCException {
        IoCContainer.registerFactory("java.sql.Connection", "by.siegell.soa.clinic.pool.ConnectionFactory");

        Map<String, String> daoDependencies = new HashMap<>();
        daoDependencies.put("java.sql.Connection", "setConnection");

        IoCContainer.registerClass("by.siegell.soa.clinic.dao.DoctorDao", "by.siegell.soa.clinic.dao.impl.DoctorDaoImpl", daoDependencies);
        IoCContainer.registerClass("by.siegell.soa.clinic.dao.DoctorScheduleDao", "by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl", daoDependencies);
        IoCContainer.registerClass("by.siegell.soa.clinic.dao.AppointmentDao", "by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl", daoDependencies);


        Map<String, String> doctorServiceDependencies = new HashMap<>();
        doctorServiceDependencies.put("by.siegell.soa.clinic.dao.DoctorDao", "setDoctorDao");
        IoCContainer.registerClass("by.siegell.soa.clinic.service.DoctorService", "by.siegell.soa.clinic.service.impl.DoctorServiceImpl", doctorServiceDependencies);

        Map<String, String> doctorScheduleServiceDependencies = new HashMap<>();
        doctorScheduleServiceDependencies.put("by.siegell.soa.clinic.dao.DoctorScheduleDao", "setDoctorScheduleDao");
        IoCContainer.registerClass("by.siegell.soa.clinic.service.DoctorScheduleService", "by.siegell.soa.clinic.service.impl.DoctorScheduleServiceImpl", doctorScheduleServiceDependencies);

        Map<String, String> appointmentServiceDependencies = new HashMap<>();
        appointmentServiceDependencies.put("by.siegell.soa.clinic.dao.AppointmentDao", "setAppointmentDao");
        appointmentServiceDependencies.put("by.siegell.soa.clinic.dao.DoctorScheduleDao", "setDoctorScheduleDao");
        IoCContainer.registerClass("by.siegell.soa.clinic.service.AppointmentService", "by.siegell.soa.clinic.service.impl.AppointmentServiceImpl", appointmentServiceDependencies);
    }
}
