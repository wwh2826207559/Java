package com.zpark.jdbcdemo;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JdbcDemo3 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //由键盘输入信息
        Scanner sca=new Scanner(System.in);
        System.out.println("请输入用户名：");
        String uname=sca.nextLine();
        System.out.println("请输入密码：");
        String pword=sca.nextLine();
        Properties pro=new Properties();
        //文件输入流
        FileInputStream fis=new FileInputStream("src/com/zpark/jdbcdemo/jdbc.properties");
        pro.load(fis);
        Class.forName(pro.getProperty("Driver-name"));
        Connection conn=DriverManager.getConnection(pro.getProperty("url"),pro.getProperty("username"), pro.getProperty("password"));
        //创建sql的执行对象
        Statement stat=conn.createStatement();
        //创建sql语句
        String sql="select * from user where username='"+uname+"'and password='"+pword+"'";
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
