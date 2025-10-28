package dataaccess;
import chess.ChessGame;
import chess.model.GameData;
import java.util.HashMap;

public interface GameDAO {
    int addGame(String gameName) throws DataAccessException;
    GameData getGame(int gameID) throws DataAccessException;
    HashMap<Integer,GameData> listGames() throws DataAccessException;
    void updateWhite(int gameID, String username) throws DataAccessException;
    void updateBlack(int gameID, String username) throws DataAccessException;
    void updateGame(int gameID, ChessGame game) throws DataAccessException;
    void removeGame(int gameID) throws DataAccessException;
    void clear() throws DataAccessException;
}
