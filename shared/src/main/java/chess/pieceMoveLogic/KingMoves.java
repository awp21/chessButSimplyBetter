package chess.pieceMoveLogic;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import static chess.ChessPiece.getMovesHelper;

public class KingMoves {
    public static Collection<ChessMove> getKingMoves(ChessBoard board, ChessPosition startPosition, ChessGame.TeamColor color) {
        Collection<ChessMove> moves = new ArrayList<>();

        getMovesHelper(board,startPosition,1,-1, color, moves);
        getMovesHelper(board,startPosition,1,0, color, moves);
        getMovesHelper(board,startPosition,1,1, color, moves);
        getMovesHelper(board,startPosition,0,-1, color, moves);
        getMovesHelper(board,startPosition,0,1, color, moves);
        getMovesHelper(board,startPosition,-1,-1, color, moves);
        getMovesHelper(board,startPosition,-1,0, color, moves);
        getMovesHelper(board,startPosition,-1,1, color, moves);

        return moves;
    }
}
