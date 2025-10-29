package dataaccess.sql;

import chess.model.AuthData;
import dataaccess.AuthDAO;
import server.exceptions.DataAccessException;

public class AuthSQLDAO implements AuthDAO {
    public void addAuth(AuthData auth) throws DataAccessException {

    }

    public AuthData getAuth(String authToken) throws DataAccessException {
        return null;
    }

    public void removeAuth(String authToken) throws DataAccessException {

    }

    public void clear() throws DataAccessException {

    }
}
