package server;

import chess.model.UserData;
import dataaccess.UserDAO;
import dataaccess.UserMemDAO;
import server.exceptions.UsernameTakenException;

import java.util.Collection;
import java.util.HashSet;

public class Service {

    public Service(){

    }

    public boolean register(UserData user)throws UsernameTakenException {
        UserDAO userdao = new UserMemDAO();
        userdao.

        return true;
    }
}
