package server;

import chess.model.AuthData;
import chess.model.UserData;
import com.google.gson.Gson;
import io.javalin.*;
import io.javalin.http.*;
import server.exceptions.BadRequestException;

public class Server {

    private final Javalin javalin;


    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.
        javalin.post("/user",this::register);
        javalin.post("/session",this::login);
    }

    private void register(Context ctx){
        Gson serializer = new Gson();
        Handler handler = new Handler();
        try{
            AuthData auth = handler.register(ctx);
            ctx.json(serializer.toJson(auth));
        } catch (BadRequestException e) {
            ctx.json(serializer.toJson(e.getMessage()));
            ctx.status(401);
        }
    }

    private void login(Context cxt){

    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
