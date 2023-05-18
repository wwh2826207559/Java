package com.zpark.jdbcdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JdbcDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //由键盘输入信息
        Scanner sca = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String uname = sca.nextLine();
        System.out.println("请输入密码：");
        String pword = sca.nextLine();
        Properties pro = new Properties();
        //文件输入流
        FileInputStream fis = new FileInputStream("src/com/zpark/jdbcdemo/jdbc.properties");
        pro.load(fis);
        Class.forName(pro.getProperty("Driver-name"));
        Connection conn = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("username"), pro.getProperty("password"));
        //创建预处理statement
        PreparedStatement pstat = conn.prepareStatement("select * from user where username=? and password=?");
        pstat.setString(1, uname);
        pstat.setString(2, pword);
        //执行sql
        ResultSet rs = pstat.executeQuery();
        //输出结果
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String pwd = rs.getString(3);
            System.out.println(id + "," + name + "," + pwd);
        }
        //释放资源
        rs.close();
        pstat.close();
        conn.close();
    }
}