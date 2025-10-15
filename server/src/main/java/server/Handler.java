package server;
import chess.model.AuthData;
import chess.model.UserData;
import com.google.gson.Gson;
import io.javalin.*;
import io.javalin.http.*;
import server.exceptions.BadRequestException;
import server.exceptions.UsernameTakenException;

import java.util.UUID;


public class Handler {
    private final Service service;
    public Handler(){
        service = new Service();
    }

    public AuthData register(Context ctx) throws BadRequestException, UsernameTakenException{
        Gson serializer = new Gson();
        UserData user = serializer.fromJson(ctx.body(), UserData.class);

        if(user.username() == null || user.password() == null || user.email() == null){
            throw new BadRequestException("Invalid Request, not 3 parameters recieved");
        }
        service.register(user);

        AuthData auth = new AuthData(generateToken(),user.username());
        return auth;
    }



    private static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
