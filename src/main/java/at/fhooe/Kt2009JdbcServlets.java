package at.fhooe;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Kt2009JdbcServlets extends HttpServlet {

    private DataSource dataSource = null;

    public static class Aircraft {
        private final String airline;
        private final String type;
        private final int capacity;
        private final int bookedSeats;

        public Aircraft(String airline, String type, int capacity, int bookedSeats) {
            this.airline = airline;
            this.type = type;
            this.capacity = capacity;
            this.bookedSeats = bookedSeats;
        }

        public String getAirline() {
            return airline;
        }

        public String getType() {
            return type;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getBookedSeats() {
            return bookedSeats;
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/AircraftDb");
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        PrintWriter writer = httpServletResponse.getWriter();
        Collection<Aircraft> aircrafts = readAircrafts();
        writer.println("<body>");
        aircrafts.forEach(a -> {
            writer.println("<p>");
            writer.print(a.getAirline());
            writer.print(a.getType());
            writer.print(a.getCapacity());
            writer.print(a.getBookedSeats());
            writer.println("</p>");
        });
        writer.println("</body>");
    }

    public Collection<Aircraft> readAircrafts() {
        var aircrafts = new ArrayList<Aircraft>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("Select airline, type, capacity, bookedSeats from aircraft");
            statement.executeQuery();

            try (ResultSet resultSet = statement.getResultSet()) {
                aircrafts.add(new Aircraft(
                        resultSet.getString("airline"),
                        resultSet.getString("type"),
                        resultSet.getInt("capacity"),
                        resultSet.getInt("bookedSeats")));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return aircrafts;
    }
}
