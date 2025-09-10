package chess.pieceMoveLogic;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public class Queen {
    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor color) {
        Collection<ChessMove> moves;
        moves = Rook.getMoves(board,myPosition,color);
        moves.addAll(Bishop.getMoves(board,myPosition,color));
        return moves;
    }
}
