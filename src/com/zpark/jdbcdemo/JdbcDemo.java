package com.zpark.jdbcdemo;
import java.sql.*;
import java.sql.DriverManager;

public class JdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //创建数据库的连接
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
        //创建sql的执行对象
        Statement stat=conn.createStatement();
        //创建sql语句
        String sql="select * from user";
        //执行sql
        ResultSet rs=stat.executeQuery(sql);
        //输出结果
        while (rs.next()){
            int id=rs.getInt(1);
            String name=rs.getString(2);
            String pwd=rs.getString(3);
            System.out.println(id+","+name+","+pwd);
        }
        //释放资源
        rs.close();
        stat.close();
        conn.close();
    }
}
