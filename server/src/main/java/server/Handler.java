package server;
import chess.model.AuthData;
import chess.model.AuthTokenRequest;
import chess.model.LoginRequest;
import chess.model.UserData;
import com.google.gson.Gson;
import io.javalin.*;
import io.javalin.http.*;
import server.exceptions.BadRequestException;
import server.exceptions.RecordValidator;
import server.exceptions.UnauthorizedException;
import server.exceptions.UsernameTakenException;

public class Handler {
    private final Service service = new Service();
    private final Gson serializer = new Gson();

    public Handler(){
    }

    public void register(Context ctx) throws Exception{
        UserData user = serializer.fromJson(ctx.body(), UserData.class);
        recordCheckFields(user);
        AuthData auth = service.register(user);
        ctx.json(serializer.toJson(auth));
    }

    public void login(Context ctx) throws Exception{
        LoginRequest loginRequest = serializer.fromJson(ctx.body(),LoginRequest.class);
        recordCheckFields(loginRequest);
        AuthData auth = service.login(loginRequest);
        ctx.json(serializer.toJson(auth));
    }

    public void logout(Context ctx) throws Exception{
        AuthTokenRequest authTokenRequest = new AuthTokenRequest(ctx.header("authorization"));
        recordCheckFields(authTokenRequest);
        service.logout(authTokenRequest.authToken());
    }

    public void listGames(Context ctx) {

    }

    public void createGame(Context ctx) {
    }

    public void joinGame(Context ctx) {
    }

    public void delete(Context ctx) throws Exception{
        service.clear();
    }

    public void errorHandler(Exception e, Context ctx){
        ctx.status(switch (e) {
            case BadRequestException _ -> HttpStatus.BAD_REQUEST;
            case UsernameTakenException _ -> HttpStatus.FORBIDDEN;
            case UnauthorizedException _ -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        }).json(buildErrorMessages(e));
    }

    private void recordCheckFields(Record r) throws BadRequestException{
        RecordValidator.validateNonNullRecord(r);
    }

    private String buildErrorMessages(Exception e){
        return String.format("""
                {"message": "%s"}""",e.getMessage());
    }



}
