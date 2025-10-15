package chess.pieceMoveLogic;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessPiece.getMovesHelper;

public class Bishop{
    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition startPosition, ChessGame.TeamColor color) {
        Collection<ChessMove> moves = new ArrayList<>();
        boolean t = true;
        for(int x = 1; x<8 && t; x++){
            t = getMovesHelper(board,startPosition,x,x,color,moves);
        }
        t=true;
        for(int x = 1; x<8 && t; x++){
            t = getMovesHelper(board,startPosition,-x,x,color,moves);
        }
        t=true;
        for(int x = 1; x<8 && t; x++){
            t = getMovesHelper(board,startPosition,-x,-x,color,moves);
        }
        t=true;
        for(int x = 1; x<8 && t; x++){
            t = getMovesHelper(board,startPosition,x,-x,color,moves);
        }
        return moves;
    }
}
