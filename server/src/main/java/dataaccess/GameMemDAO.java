package dataaccess;
import chess.model.GameData;

import java.util.HashMap;

public class GameMemDAO implements GameDAO{
    private final HashMap<Integer, GameData> gameDataCollection;
    //FIND WAY TO INCREMENT GAMEIDs
    private int currentGameID;
    public GameMemDAO(){
        gameDataCollection = new HashMap<Integer, GameData>();
    }
    public void addGame(GameData game){gameDataCollection.put(currentGameID,game);}
    public GameData getGame(int gameID){return gameDataCollection.get(gameID);}
    public void removeGame(int gameID){gameDataCollection.remove(gameID);}
    public void clear(){gameDataCollection.clear();}
}
