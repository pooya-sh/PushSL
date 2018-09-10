package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.DBdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Repository {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;


    public List<DBdata> listDBdata() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users JOIN Travels ON Users.User_ID = Travels.User_ID")) {
            List<DBdata> datalist = new ArrayList<>();
            while (rs.next()) {
                DBdata dbdata = new DBdata(rs.getString("UserEmail"), rs.getString("JourneyNumberRT"),
                        rs.getString("TimeBeforeLeaving"), rs.getString("Date"));
                datalist.add(dbdata);
            }

            return datalist;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void deleteData(String journeyNumber) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Travels WHERE JourneyNumberRT = ? ");
            statement.setString(1, journeyNumber);
            statement.executeUpdate();
        }
    }

    public void addData(String email, String journeyNumberRT, String timeBeforeLeaving) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement checkUsers = conn.prepareStatement("SELECT * FROM Users WHERE UserEmail = ? ");
            checkUsers.setString(1, email);
            ResultSet resultset = checkUsers.executeQuery();
            int id;
            if (resultset.next()) {
                id = resultset.getInt("User_ID");
            } else {
                PreparedStatement statement1 = conn.prepareStatement("INSERT INTO Users VALUES (?);");
                statement1.setString(1, email);
                statement1.executeUpdate();

                PreparedStatement statement2 = conn.prepareStatement(" SELECT * FROM Users WHERE UserEmail = ?");
                statement2.setString(1, email);
                ResultSet resultSet = statement2.executeQuery();
                resultSet.next();
                id = resultSet.getInt("User_ID");
            }

            PreparedStatement addTravelStatemnet = conn.prepareStatement("INSERT INTO Travels VALUES (?,?,?,?)");
            addTravelStatemnet.setInt(1, Integer.parseInt(journeyNumberRT));
            addTravelStatemnet.setString(2, timeBeforeLeaving);
            addTravelStatemnet.setString(3, "1956-03-11");
            addTravelStatemnet.setInt(4, id);
            addTravelStatemnet.executeUpdate();

            System.out.println("id: " + id);
        }
    }
}

