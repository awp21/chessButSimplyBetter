package server;

import chess.ChessGame;
import chess.model.*;
import dataaccess.*;
import server.exceptions.BadRequestException;
import server.exceptions.UnauthorizedException;
import server.exceptions.UsernameTakenException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Service {
    private final UserDAO userdao = new UserMemDAO();
    private final AuthDAO authdao = new AuthMemDAO();
    private final GameDAO gamedao = new GameMemDAO();

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
        if(authdao.getAuth(authToken)==null){throw new UnauthorizedException("Error: Incorrect AuthToken");}
        authdao.removeAuth(authToken);
    }

    public CreateGameResponse createGame(CreateGameRequest createGameRequest) throws Exception{
        if(authdao.getAuth(createGameRequest.authToken())==null){throw new UnauthorizedException("Error: Incorrect AuthToken");}
        return new CreateGameResponse(gamedao.addGame(createGameRequest.gameName()));
    }

    public void joinGame(JoinGameRequest joinGameRequest) throws Exception{
        if(authdao.getAuth(joinGameRequest.authToken())==null){throw new UnauthorizedException("Error: Incorrect AuthToken");}
        String username = authdao.getAuth(joinGameRequest.authToken()).username();
        GameData gameData = gamedao.getGame(joinGameRequest.gameID());
        if(gameData == null){throw new BadRequestException("Error: GameID does not exist");}
        boolean join = joinGameRequest.playerColor().equals(ChessGame.TeamColor.WHITE);
        if (join) {
            if(gameData.whiteUsername() != null){throw new UsernameTakenException("Error: Color already taken");}
            gamedao.updateWhite(joinGameRequest.gameID(), username);
        } else {
            if(gameData.blackUsername() != null){throw new UsernameTakenException("Error: Color already taken");}
            gamedao.updateBlack(joinGameRequest.gameID(), username);
        }
    }

    public ListGamesResponse listGames(String authToken) throws Exception{
        if(authdao.getAuth(authToken)==null){throw new UnauthorizedException("Error: Incorrect AuthToken");}
        List<GameData> games = new ArrayList<>(gamedao.listGames().values());
        return new ListGamesResponse(games);
    }

    public void clear() throws Exception {
        userdao.clear();
        authdao.clear();
        gamedao.clear();
    }



    private static String generateToken() {
        return UUID.randomUUID().toString();
    }


}
