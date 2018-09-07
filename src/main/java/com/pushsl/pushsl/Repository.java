package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.DBdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users JOIN Travels ON User.User_ID")) {
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
}
