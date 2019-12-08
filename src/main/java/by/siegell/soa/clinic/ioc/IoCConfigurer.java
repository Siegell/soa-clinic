package by.siegell.soa.clinic.ioc;

import by.siegell.soa.clinic.web.ActionFactory;

import java.util.HashMap;
import java.util.Map;

public class IoCConfigurer {
    public static void configure() throws IoCException {
        //web registration
        IoCContainer.registerFactory("by.siegell.soa.clinic.web.Action", "by.siegell.soa.clinic.web.ActionFactory");

        //doctor
        Map<String, String> doctorActionsDependencies = map(pair("by.siegell.soa.clinic.service.DoctorService", "setDoctorService"));
        ActionFactory.registerAction("/doctor/list", "by.siegell.soa.clinic.web.doctor.DoctorListActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctor.DoctorListActionImpl", doctorActionsDependencies);
        ActionFactory.registerAction("/doctor/edit", "by.siegell.soa.clinic.web.doctor.DoctorEditActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctor.DoctorEditActionImpl", doctorActionsDependencies);
        ActionFactory.registerAction("/doctor/save", "by.siegell.soa.clinic.web.doctor.DoctorSaveActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctor.DoctorSaveActionImpl", doctorActionsDependencies);
        ActionFactory.registerAction("/doctor/delete", "by.siegell.soa.clinic.web.doctor.DoctorDeleteActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctor.DoctorDeleteActionImpl", doctorActionsDependencies);

        //doctorSchedule
        Map<String, String> doctorScheduleActionsDependencies = map(pair("by.siegell.soa.clinic.service.DoctorScheduleService", "setDoctorScheduleService"));
        ActionFactory.registerAction("/doctor_schedule/list", "by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleListActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleListActionImpl", doctorScheduleActionsDependencies);
        ActionFactory.registerAction("/doctor_schedule/edit", "by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleEditActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleEditActionImpl", doctorScheduleActionsDependencies);
        ActionFactory.registerAction("/doctor_schedule/save", "by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleSaveActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleSaveActionImpl", doctorScheduleActionsDependencies);
        ActionFactory.registerAction("/doctor_schedule/delete", "by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleDeleteActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.doctorSchedule.DoctorScheduleDeleteActionImpl", doctorScheduleActionsDependencies);

        //appointment
        Map<String, String> appointmentActionsDependencies = map(pair("by.siegell.soa.clinic.service.AppointmentService", "setAppointmentService"));
        ActionFactory.registerAction("/appointment/list", "by.siegell.soa.clinic.web.appointment.AppointmentListActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.appointment.AppointmentListActionImpl", appointmentActionsDependencies);
        ActionFactory.registerAction("/appointment/edit", "by.siegell.soa.clinic.web.appointment.AppointmentEditActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.appointment.AppointmentEditActionImpl", appointmentActionsDependencies);
        ActionFactory.registerAction("/appointment/save", "by.siegell.soa.clinic.web.appointment.AppointmentSaveActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.appointment.AppointmentSaveActionImpl", appointmentActionsDependencies);
        ActionFactory.registerAction("/appointment/delete", "by.siegell.soa.clinic.web.appointment.AppointmentDeleteActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.appointment.AppointmentDeleteActionImpl", appointmentActionsDependencies);

        //user
        Map<String, String> userActionsDependencies = map(pair("by.siegell.soa.clinic.service.UserService", "setUserService"));
        ActionFactory.registerAction("/user/list", "by.siegell.soa.clinic.web.user.UserListActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.user.UserListActionImpl", userActionsDependencies);
        ActionFactory.registerAction("/user/edit", "by.siegell.soa.clinic.web.user.UserEditActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.user.UserEditActionImpl", userActionsDependencies);
        ActionFactory.registerAction("/user/save", "by.siegell.soa.clinic.web.user.UserSaveActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.user.UserSaveActionImpl", userActionsDependencies);
        ActionFactory.registerAction("/user/delete", "by.siegell.soa.clinic.web.user.UserDeleteActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.user.UserDeleteActionImpl", userActionsDependencies);
        ActionFactory.registerAction("/login", "by.siegell.soa.clinic.web.user.LoginActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.user.LoginActionImpl", userActionsDependencies);
        ActionFactory.registerAction("/logout", "by.siegell.soa.clinic.web.user.LogoutActionImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.web.user.LogoutActionImpl", userActionsDependencies);


        /* registration of factory for connections */
        IoCContainer.registerFactory("java.sql.Connection", "by.siegell.soa.clinic.pool.ConnectionFactory");

        /* registration of DAO */
        Map<String, String> daoDependencies = map(pair("java.sql.Connection", "setConnection"));
        IoCContainer.registerClass("by.siegell.soa.clinic.dao.DoctorDao", "by.siegell.soa.clinic.dao.impl.DoctorDaoImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.dao.impl.DoctorDaoImpl", daoDependencies);

        IoCContainer.registerClass("by.siegell.soa.clinic.dao.DoctorScheduleDao", "by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl", daoDependencies);

        IoCContainer.registerClass("by.siegell.soa.clinic.dao.AppointmentDao", "by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl", daoDependencies);

        IoCContainer.registerClass("by.siegell.soa.clinic.dao.UserDao", "by.siegell.soa.clinic.dao.impl.UserDaoImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.dao.impl.UserDaoImpl", daoDependencies);


        //services registration
        IoCContainer.registerClass("by.siegell.soa.clinic.service.UserService", "by.siegell.soa.clinic.service.impl.UserServiceImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.service.impl.UserServiceImpl", map(pair("by.siegell.soa.clinic.dao.UserDao", "setUserDao")));

        //utils
        IoCContainer.registerClass("by.siegell.soa.clinic.util.PermissionHelper", "by.siegell.soa.clinic.util.PermissionHelper");
        DIContainer.registerClass("by.siegell.soa.clinic.util.PermissionHelper", map(pair("by.siegell.soa.clinic.service.UserService", "setUserService")));

        IoCContainer.registerClass("by.siegell.soa.clinic.service.DoctorService", "by.siegell.soa.clinic.service.impl.DoctorServiceImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.service.impl.DoctorServiceImpl", map(pair("by.siegell.soa.clinic.dao.DoctorDao", "setDoctorDao")));

        IoCContainer.registerClass("by.siegell.soa.clinic.service.DoctorScheduleService", "by.siegell.soa.clinic.service.impl.DoctorScheduleServiceImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.service.impl.DoctorScheduleServiceImpl", map(pair("by.siegell.soa.clinic.dao.DoctorScheduleDao", "setDoctorScheduleDao")));

        IoCContainer.registerClass("by.siegell.soa.clinic.service.AppointmentService", "by.siegell.soa.clinic.service.impl.AppointmentServiceImpl");
        DIContainer.registerClass("by.siegell.soa.clinic.service.impl.AppointmentServiceImpl", map(pair("by.siegell.soa.clinic.dao.AppointmentDao", "setAppointmentDao"), pair("by.siegell.soa.clinic.dao.DoctorScheduleDao", "setDoctorScheduleDao")));

    }

    private static Map<String, String> map(String[]... strings) {
        Map<String, String> result = new HashMap<>();
        for (String[] pair : strings) {
            result.put(pair[0], pair[1]);
        }
        return result;
    }

    private static String[] pair(String key, String value) {
        return new String[]{key, value};
    }
}
