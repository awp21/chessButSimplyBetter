package dataaccess.sql;

import chess.model.UserData;
import dataaccess.UserDAO;
import server.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQLDAO implements UserDAO {
    public void addUser(UserData user) throws DataAccessException {
        String command = "INSERT INTO userdatabase (username, password, email)" +
                "VALUES (?,?,?)";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(command);
            preparedStatement.setString(1,user.username());
            preparedStatement.setString(2,user.password());
            preparedStatement.setString(3,user.email());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserData getUser(String username) throws DataAccessException {
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT username, password, email FROM userdatabase WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()){
                return null;
            }
            var name = rs.getString("username");
            if(name.equals(username)){
                return new UserData(rs.getString("username"),rs.getString("password"),rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void clear() throws DataAccessException {
        String command = "TRUNCATE userdatabase";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(command);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
