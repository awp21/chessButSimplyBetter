package server;

import chess.model.AuthData;
import chess.model.LoginRequest;
import chess.model.UserData;
import dataaccess.*;
import org.eclipse.jetty.server.Authentication;
import server.exceptions.BadRequestException;
import server.exceptions.UnauthorizedException;
import server.exceptions.UsernameTakenException;
import java.util.UUID;


public class Service {
    private final UserDAO userdao = new UserMemDAO();
    private final AuthDAO authdao = new AuthMemDAO();

    public Service(){}

    public AuthData register(UserData user)throws Exception{
        if(userdao.getUser(user.username()) != null){throw new UsernameTakenException("Error: Username already taken");}
        userdao.addUser(user);
        AuthData authData = new AuthData(user.username(),generateToken());
        authdao.addAuth(authData);
        return authData;
    }

    public AuthData login(LoginRequest loginRequest) throws Exception {
        UserData userData = userdao.getUser(loginRequest.username());
        if(userData==null){throw new UnauthorizedException("Error: User doesn't exist");}
        else if(!loginRequest.password().equals(userData.password())){throw new UnauthorizedException("Error: Incorrect password");}
        AuthData authData = new AuthData(loginRequest.username(),generateToken());
        authdao.addAuth(authData);
        return authData;
    }

    public void logout(String authToken)throws Exception{
        if(authdao.getAuth(authToken)==null){
            throw new UnauthorizedException("Error: Incorrect AuthToken");
        }
        authdao.removeAuth(authToken);
    }

    public void clear() throws Exception {
        userdao.clear();
        authdao.clear();
    }



    private static String generateToken() {
        return UUID.randomUUID().toString();
    }

}
