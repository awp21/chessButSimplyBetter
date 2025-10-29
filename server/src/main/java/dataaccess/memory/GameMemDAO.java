package dataaccess.memory;
import chess.ChessGame;
import chess.model.GameData;
import dataaccess.GameDAO;

import java.util.HashMap;

public class GameMemDAO implements GameDAO {
    private final HashMap<Integer, GameData> gameDataCollection;
    private int currentGameID;

    public GameMemDAO(){
        gameDataCollection = new HashMap<Integer, GameData>();
        currentGameID = 0;
    }

    public int addGame(String gameName){
        currentGameID++;
        GameData addGame = new GameData(currentGameID,null,null,gameName,new ChessGame());
        gameDataCollection.put(currentGameID,addGame);
        return currentGameID;
    }
    public GameData getGame(int gameID){return gameDataCollection.get(gameID);}

    public HashMap<Integer, GameData> listGames(){return gameDataCollection;}

    public void updateWhite(int gameID, String username) {
        GameData original = gameDataCollection.get(gameID);
        GameData edited = new GameData(gameID,username, original.blackUsername(),
                original.gameName(), original.game());
        gameDataCollection.put(gameID,edited);
    }


    public void updateBlack(int gameID, String username) {
        GameData original = gameDataCollection.get(gameID);
        GameData edited = new GameData(gameID, original.whiteUsername(), username,
                original.gameName(), original.game());
        gameDataCollection.put(gameID,edited);
    }


    public void updateGame(int gameID, ChessGame game) {
        GameData original = gameDataCollection.get(gameID);
        GameData edited = new GameData(gameID, original.whiteUsername(), original.blackUsername(),
                original.gameName(), game);
        gameDataCollection.put(gameID,edited);
    }

    public void removeGame(int gameID){gameDataCollection.remove(gameID);}
    public void clear(){gameDataCollection.clear();}
}
