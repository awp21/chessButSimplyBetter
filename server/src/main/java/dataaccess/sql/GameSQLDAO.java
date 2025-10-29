package dataaccess.sql;

import chess.ChessGame;
import chess.model.GameData;
import dataaccess.GameDAO;
import server.exceptions.DataAccessException;

import java.util.HashMap;

public class GameSQLDAO implements GameDAO {
    public int addGame(String gameName) throws DataAccessException {
        return 0;
    }

    public GameData getGame(int gameID) throws DataAccessException {
        return null;
    }

    public HashMap<Integer, GameData> listGames() throws DataAccessException {
        return null;
    }

    public void updateWhite(int gameID, String username) throws DataAccessException {

    }

    public void updateBlack(int gameID, String username) throws DataAccessException {

    }

    public void updateGame(int gameID, ChessGame game) throws DataAccessException {

    }

    public void removeGame(int gameID) throws DataAccessException {

    }

    public void clear() throws DataAccessException {

    }
}
