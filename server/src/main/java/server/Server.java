package server;


import io.javalin.*;
import io.javalin.http.*;


public class Server {

    private final Javalin javalin;
    private final Handler handler = new Handler();


    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        javalin.post("/user", handler::register);
        javalin.post("/session",handler::login);
        javalin.delete("/db",handler::delete);
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
