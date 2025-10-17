package dataaccess;

import chess.model.UserData;
import java.util.HashMap;


public class UserMemDAO implements UserDAO{
    private final HashMap<String,UserData> userDataCollection;

    public UserMemDAO(){
        userDataCollection = new HashMap<String, UserData>();
    }

    public void addUser(UserData user){
        userDataCollection.put(user.username(),user);
    }

    public UserData getUser(UserData user){
        return userDataCollection.get(user.username());
    }

    public void removeUser(UserData user){
        userDataCollection.remove(user.username());
    }

}
