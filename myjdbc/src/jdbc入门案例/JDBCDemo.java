package jdbc入门案例;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        java.sql.Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
        String url = "jdbc:mysql://localhost:3306/day04?useSSL=false";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,username,password);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from category");
        while(rs.next()){
            System.out.println(rs.getObject("cid") + " " + rs.getObject("cname"));
        }
        rs.close();
        st.close();
        conn.close();
    }
}
