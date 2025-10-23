package dataaccess;

import chess.model.UserData;


public interface UserDAO {
    void addUser(UserData user) throws DataAccessException;
    UserData getUser(UserData user) throws DataAccessException;
    void clear() throws DataAccessException;
}
