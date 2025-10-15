package dataaccess;

import chess.model.UserData;
import org.eclipse.jetty.server.Authentication;

import java.util.Collection;
import java.util.HashMap;


public class UserMemDAO implements UserDAO{
    private HashMap<String,UserData> userDataCollection;

    public UserMemDAO(){
        userDataCollection = new HashMap<String, UserData>();
    }

    public void addUser(UserData user){
        userDataCollection.;
    }
    public boolean getUser(UserData user){
        return userDataCollection.contains(user);
    }

    public void removeUser(UserData user){
        userDataCollection.remove(user);
    }

}
