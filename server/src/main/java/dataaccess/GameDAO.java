package dataaccess;
import chess.model.GameData;

public interface GameDAO {
    void addGame(GameData game) throws DataAccessException;
    GameData getGame(int gameID) throws DataAccessException;
    void removeGame(int gameID) throws DataAccessException;
    void clear() throws DataAccessException;
}
