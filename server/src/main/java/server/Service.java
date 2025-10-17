package server;

import chess.model.AuthData;
import chess.model.LoginRequest;
import chess.model.UserData;
import dataaccess.UserDAO;
import dataaccess.UserMemDAO;
import server.exceptions.UsernameTakenException;
import java.util.UUID;


public class Service {
    private UserDAO userdao = new UserMemDAO();

    public Service(){

    }

    public AuthData register(UserData user)throws UsernameTakenException {
        UserData testUser = userdao.getUser(user);
        if(testUser != null){
            throw new UsernameTakenException("Error: Username already taken");
        }
        userdao.addUser(user);
        //registerAuthData
        return new AuthData(user.username(),generateToken());
    }

    public AuthData login(LoginRequest loginRequest){
        return new AuthData(loginRequest.username(),generateToken());
    }

    private static String generateToken() {
        return UUID.randomUUID().toString();
    }

}
