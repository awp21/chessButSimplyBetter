package dataaccess;

import chess.model.AuthData;

public interface AuthDAO {
    void addAuth(AuthData auth) throws DataAccessException;
    AuthData getAuth(String authToken) throws DataAccessException;
    void removeAuth(String authToken) throws DataAccessException;
    void clear() throws DataAccessException;
}
