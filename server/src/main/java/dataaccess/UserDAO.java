package dataaccess;

import chess.model.UserData;


public interface UserDAO {
    void addUser(UserData user) throws DataAccessException;
    UserData getUser(String username) throws DataAccessException;
    void clear() throws DataAccessException;
}
