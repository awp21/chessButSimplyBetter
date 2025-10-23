package server;

import chess.model.AuthData;
import chess.model.LoginRequest;
import chess.model.UserData;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import dataaccess.UserMemDAO;
import server.exceptions.UsernameTakenException;
import java.util.UUID;


public class Service {
    private final UserDAO userdao = new UserMemDAO();

    public Service(){

    }

    public AuthData register(UserData user)throws UsernameTakenException, DataAccessException {
        if(userdao.getUser(user) != null){
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
