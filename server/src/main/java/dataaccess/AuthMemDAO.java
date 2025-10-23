package dataaccess;

import chess.model.AuthData;
import chess.model.UserData;


import java.util.HashMap;

public class AuthMemDAO implements AuthDAO{
    private final HashMap<String, AuthData> authDataCollection;
    public AuthMemDAO(){
        authDataCollection = new HashMap<String, AuthData>();
    }
    public void addAuth(AuthData auth){authDataCollection.put(auth.authToken(),auth);}
    public AuthData getAuth(String authToken){return authDataCollection.get(authToken);}
    public void removeAuth(String authToken){authDataCollection.remove(authToken);}
    public void clear(){authDataCollection.clear();}
}
