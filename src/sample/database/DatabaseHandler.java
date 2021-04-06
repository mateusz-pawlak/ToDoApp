package sample.database;

import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO "+ Const.USERS_TABLE + "("+Const.USERS_FIRSTNAME+","+Const.USERS_USERNAME+","
                +Const.USERS_PASSWORD+")"+ "VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        if(!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_USERNAME
                    + "=?" + " AND " + Const.USERS_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString( 1, user.getUserName());
                preparedStatement.setString( 2, user.getPassword());

                resultSet = preparedStatement.executeQuery();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        } else {
            System.out.println("Please enter username and password");
        }

        return resultSet;
    }
}
