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
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
        List<Master> masters = masterDao.getAll();
        HttpSession session = req.getSession();
        session.setAttribute("pageContext", getServletContext().getContextPath());
        session.setAttribute("masters", masters);
        req.getRequestDispatcher("services.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("addServices")) addServices(req, resp);
        if (action.equals("makeAppointment")) makeAppointment(req, resp);
    }

    private void addServices(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String masterName = req.getParameter("master");
        String[] masterInfo = masterName.split(" ");
        Master master = masterDao.get(masterInfo[0], masterInfo[1]);

        List<String> services = serviceDao.getAllFromMaster(master.getId()).stream().map(Service::getName).collect(Collectors.toList());
        resp.setContentType("application/json");
        String json = new Gson().toJson(services);
        resp.getWriter().write(json);
    }

    private void makeAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String masterName = req.getParameter("master");
        String service = req.getParameter("service");
        String[] date = req.getParameter("date").split("-");
        String[] time = req.getParameter("time").split(":");
        // validation

        Timestamp.valueOf(LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1])));

    }
}
