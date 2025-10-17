package server;
import chess.model.AuthData;
import chess.model.LoginRequest;
import chess.model.UserData;
import com.google.gson.Gson;
import io.javalin.*;
import io.javalin.http.*;
import server.exceptions.BadRequestException;
import server.exceptions.UsernameTakenException;




public class Handler {
    private final Service service = new Service();
    private final Gson serializer = new Gson();
    public Handler(){

    }

    public void register(Context ctx) {
        UserData user = serializer.fromJson(ctx.body(), UserData.class);
        try{
            if(user.username() == null || user.password() == null || user.email() == null){
                throw new BadRequestException("Error: Invalid Request, field is null");
            }
            AuthData auth = service.register(user);
            ctx.json(serializer.toJson(auth));
        } catch (BadRequestException e) {
            ctx.json(buildErrorMessages(e));
            ctx.status(400);
        } catch (UsernameTakenException e) {
            ctx.json(buildErrorMessages(e));
            ctx.status(403);
        }
    }

    public void login(Context ctx){
        LoginRequest loginRequest = serializer.fromJson(ctx.body(),LoginRequest.class);
        try{
            if(loginRequest.username() == null || loginRequest.password() == null){
                throw new BadRequestException("Error: Invalid Request, field is null");
            }
            AuthData auth = service.login(loginRequest);
            ctx.json(serializer.toJson(auth));
        }catch (BadRequestException e){
            ctx.json(buildErrorMessages(e));
            ctx.status(400);
        }
    }

    public void delete(Context ctx){

    }

    private String buildErrorMessages(Exception e){
        return String.format("""
                {"message": "%s"}""",e.getMessage());
    }


}
