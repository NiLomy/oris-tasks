package ru.kpfu.itis.lobanov.control1.servlet;

import com.google.gson.Gson;
import ru.kpfu.itis.lobanov.control1.model.dao.impl.AppointmentDao;
import ru.kpfu.itis.lobanov.control1.model.dao.impl.MasterDao;
import ru.kpfu.itis.lobanov.control1.model.dao.impl.ServiceDao;
import ru.kpfu.itis.lobanov.control1.model.entity.Appointment;
import ru.kpfu.itis.lobanov.control1.model.entity.Master;
import ru.kpfu.itis.lobanov.control1.model.entity.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/service")
public class MainServlet extends HttpServlet {
    private MasterDao masterDao;
    private ServiceDao serviceDao;
    private AppointmentDao appointmentDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        masterDao = new MasterDao();
        serviceDao = new ServiceDao();
        appointmentDao = new AppointmentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageContext", getServletContext().getContextPath());
        List<Master> masters = masterDao.getAll();
        HttpSession session = req.getSession();
        session.setAttribute("masters", masters);
        req.getRequestDispatcher("classwork/services.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("addServices")) addServices(req, resp);
        if (action.equals("makeAppointment")) makeAppointment(req, resp);
    }

    private void addServices(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String masterInput = req.getParameter("master");

        if (masterInput.isEmpty()) {
            resp.setContentType("text/plain");
            resp.getWriter().write("empty");
            return;
        }

        int masterId = Integer.parseInt(masterInput);
        Master master = masterDao.get(masterId);

        List<String> services = serviceDao.getAllFromMaster(master.getId()).stream().map(Service::getName).collect(Collectors.toList());
        resp.setContentType("application/json");
        String json = new Gson().toJson(services);
        resp.getWriter().write(json);
    }

    private void makeAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String masterName = req.getParameter("master");
        String serviceName = req.getParameter("service");
        String[] date = req.getParameter("date").split("-");
        String[] time = req.getParameter("time").split(":");

        resp.setContentType("text/plain");

        if (phone.isEmpty() || masterName.isEmpty() || serviceName.isEmpty() || date.length == 1 || time.length == 1) {
            resp.getWriter().write("empty");
            return;
        }

        Pattern pattern = Pattern.compile("^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            resp.getWriter().write("phoneError");
            return;
        }

        Service service = serviceDao.get(serviceName);
        int masterId = Integer.parseInt(masterName);
        int serviceId = service.getId();
        int duration = service.getDuration();

        Timestamp timestampStart = Timestamp.valueOf(LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1])));
        Timestamp timestampEnd = Timestamp.valueOf(timestampStart.toLocalDateTime().plusMinutes(duration));
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());

        if (timestampStart.before(currentDate)) {
            resp.getWriter().write("dateError");
            return;
        }

        List<Appointment> appointments = appointmentDao.getAllFromMaster(masterId);
        if (canAppoint(appointments, duration, timestampStart, timestampEnd)) {
            appointmentDao.save(new Appointment(
                    masterId,
                    serviceId,
                    timestampStart,
                    phone
            ));
            resp.getWriter().write("ok");
        } else {
            resp.getWriter().write("error");
        }
    }

    private boolean canAppoint(List<Appointment> appointments, int duration, Timestamp timestampStart, Timestamp timestampEnd) {
        for (Appointment appointment: appointments) {
            Timestamp tsStart = appointment.getTimestamp();
            Timestamp tsEnd = Timestamp.valueOf(tsStart.toLocalDateTime().plusMinutes(duration));

            if (!(timestampStart.after(tsEnd) || timestampEnd.before(tsStart) || timestampStart.equals(tsEnd) || timestampEnd.equals(tsStart))) {
                return false;
            }
        }
        return true;
    }
}
