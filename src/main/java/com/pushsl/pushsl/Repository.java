package com.pushsl.pushsl;

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

public class Repository {
}

//    @Component
//    public class JdbcBlogRepository {
//
//        @Qualifier("dataSource")
//        @Autowired
//        private DataSource dataSource;
//
//
//        public  listUsers() {
//            try (Connection conn = dataSource.getConnection();
//                 Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery("SELECT id, title FROM blogs")) {
//                List<Blog> blogs = new ArrayList<>();
//                while (rs.next()) blogs.add(rsBlog(rs));
//                return blogs;
//            } catch (SQLException e) {
//                throw new BlogRepositoryException(e);
//            }
//}
