package chess.pieceMoveLogic;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessPiece.getMovesHelper;

public class BishopMoves {
    public static Collection<ChessMove> getBishopMoves(ChessBoard board, ChessPosition startPosition, ChessGame.TeamColor color) {
        Collection<ChessMove> moves = new ArrayList<>();
        boolean t = true;
        for(int r = 1; r<7 && t; r++){
            t = getMovesHelper(board,startPosition,r,r,color,moves);
        }
        t=true;
        for(int r = -1; r>-7 && t; r--){
            t = getMovesHelper(board,startPosition,r,r,color,moves);
        }
        t=true;
        for(int c = 1; c<7 && t; c++){
            t = getMovesHelper(board,startPosition,c,c,color,moves);
        }
        t=true;
        for(int c = -1; c>-7 && t; c--){
            t = getMovesHelper(board,startPosition,c,c,color,moves);
        }
        return moves;
    }
}
