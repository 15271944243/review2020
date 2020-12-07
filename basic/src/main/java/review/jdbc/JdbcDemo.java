package review.jdbc;

import java.sql.*;
import java.util.ArrayList;

/**
 * jdbc demo
 * @author: xiaoxiaoxiang
 * @date: 2020/12/7 21:43
 */
public class JdbcDemo {

    /**
     * 1. 导入JDBC驱动包
     * 2. 通过DriverManager注册驱动
     * 3. 创建连接
     * 4. 创建Statement
     * 5. CRUD
     * 6. 操作结果集
     * 7. 关闭连接
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<User> userList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                userList.add(new User(id, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
