package dataaccess;

import chess.model.UserData;

public interface UserDAO {
    void addUser(UserData user);
    UserData getUser(UserData user);
    void removeUser(UserData user);
}
