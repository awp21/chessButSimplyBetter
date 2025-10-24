package dataaccess;

import chess.model.UserData;
import java.util.HashMap;


public class UserMemDAO implements UserDAO{
    private final HashMap<String,UserData> userDataCollection;
    public UserMemDAO(){userDataCollection = new HashMap<String, UserData>();}
    public void addUser(UserData user){userDataCollection.put(user.username(),user);}
    public UserData getUser(String username){
        return userDataCollection.get(username);
    }
    public void clear(){userDataCollection.clear();}
}
