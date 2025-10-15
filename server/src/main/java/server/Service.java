package server;

import chess.model.UserData;
import dataaccess.UserDAO;
import dataaccess.UserMemDAO;
import server.exceptions.UsernameTakenException;

import java.util.Collection;
import java.util.HashSet;

public class Service {
    private UserDAO userdao = new UserMemDAO();

    public Service(){

    }

    public boolean register(UserData user)throws UsernameTakenException {
        if(userdao.getUser(user)){
            throw new UsernameTakenException("Username already taken");
        }
        userdao.addUser(user);
        return true;
    }
}
