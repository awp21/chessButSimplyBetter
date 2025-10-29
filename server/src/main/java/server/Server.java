package server;


import dataaccess.sql.DatabaseManager;
import io.javalin.*;
import io.javalin.http.*;
import server.exceptions.DataAccessException;


public class Server {

    private final Javalin javalin;
    private final Handler handler = new Handler();


    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));
        javalin.post("/user", handler::register);
        javalin.post("/session",handler::login);
        javalin.delete("/session",handler::logout);
        javalin.get("/game",handler::listGames);
        javalin.post("/game",handler::createGame);
        javalin.put("/game",handler::joinGame);
        javalin.delete("/db",handler::delete);
        javalin.exception(Exception.class,handler::errorHandler);
    }

    public int run(int desiredPort){
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
