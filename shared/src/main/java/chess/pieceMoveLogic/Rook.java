package chess.pieceMoveLogic;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessPiece.getMovesHelper;

public class Rook {
    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition startPosition, ChessGame.TeamColor color) {
        Collection<ChessMove> moves = new ArrayList<>();
        boolean t = true;
        for(int x = 1; x<7 && t; x++){
            t = getMovesHelper(board,startPosition,x,0,color,moves);
        }
        t=true;
        for(int x = 1; x<7 && t; x++){
            t = getMovesHelper(board,startPosition,-x,0,color,moves);
        }
        t=true;
        for(int x = 1; x<7 && t; x++){
            t = getMovesHelper(board,startPosition,0,x,color,moves);
        }
        t=true;
        for(int x = 1; x<7 && t; x++){
            t = getMovesHelper(board,startPosition,0,-x,color,moves);
        }
        return moves;
    }
}
