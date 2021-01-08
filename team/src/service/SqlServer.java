package service;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.sql.*;
        /***
         数据库名称为ssdb,账号密码均为root,
         数据库含表employeedata,
         该表有十个字段，
         ***/
/**
 * @author shaoshao
 * @create 2021-01-05-15:40
 */

public class SqlServer {

    public static void main(String[] args) throws Exception {
        try{
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }
        String dburl = "jdbc:mysql://localhost:3306/ssdb?&useSSL=false&serverTimezone=UTC";
        String sql = "SELECT * FROM employeedata";
        try(    Connection conn = DriverManager.getConnection(dburl,"root","root");
                Statement stmt = conn.createStatement();
                ResultSet rst = stmt.executeQuery(sql))

        {
            while (rst.next()){
                System.out.println(rst.getInt(1)+"\t\t"+
                        rst.getInt(2) + "\t\t"+rst.getString(3) +
                        "\t\t" + rst.getDouble(4) + "\t\t" + rst.getDouble(5) +
                        "\t\t" + rst.getDouble(6) + "\t\t"+rst.getDouble(7)+
                        "\t\t"+rst.getString(8)+ "\t\t"+rst.getString(9)+
                        "\t\t"+rst.getDouble(10)
                );
            }

        }catch (SQLException se){
            se.printStackTrace();
            System.out.println("更新1");
            System.out.println("更新2");

        }

    }
}
