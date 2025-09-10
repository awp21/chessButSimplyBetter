package chess.pieceMoveLogic;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class Pawn {
    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor color) {
        Collection<ChessMove> moves = new ArrayList<>();
        int direction = (color == ChessGame.TeamColor.WHITE) ? 1 : -1;
        boolean firstMove = (color == ChessGame.TeamColor.WHITE) ? (myPosition.getRow() == 1) : (myPosition.getRow() == 6);
        boolean promote = (color == ChessGame.TeamColor.WHITE) ? (myPosition.getRow() == 6) : (myPosition.getRow() == 1);
        ChessPosition testPos = new ChessPosition(myPosition.getRow()+direction, myPosition.getColumn());
        ChessPiece piece = board.getPiece(testPos);
        if(piece == null){
            moves.add(new ChessMove(myPosition, testPos,null));
            if (firstMove){
                ChessPosition doubleJump = new ChessPosition(myPosition.getRow()+direction*2, myPosition.getColumn());
                piece = board.getPiece(doubleJump);
                if (piece == null){
                    moves.add(new ChessMove(myPosition,doubleJump,null));
                }
            }
            else if (promote){
                addPromotions(myPosition,testPos,moves);
            }
        }
        testPos = new ChessPosition(myPosition.getRow()+direction, myPosition.getColumn()+1);
        if (board.getPiece(testPos) != null) {
            if (board.getPiece(testPos).getTeamColor() != color) {
                if (promote) {
                    addPromotions(myPosition, testPos, moves);
                } else {
                    moves.add(new ChessMove(myPosition, testPos, null));
                }
            }
            testPos = new ChessPosition(myPosition.getRow() + direction, myPosition.getColumn() - 1);
            if (board.getPiece(testPos).getTeamColor() != color) {
                if (promote) {
                    addPromotions(myPosition, testPos, moves);
                } else {
                    moves.add(new ChessMove(myPosition, testPos, null));
                }
            }
        }
        return moves;
    }

    private static void addPromotions(ChessPosition myPosition, ChessPosition endPosition, Collection<ChessMove> moves){
        moves.add(new ChessMove(myPosition,endPosition, ChessPiece.PieceType.QUEEN));
        moves.add(new ChessMove(myPosition,endPosition, ChessPiece.PieceType.ROOK));
        moves.add(new ChessMove(myPosition,endPosition, ChessPiece.PieceType.KNIGHT));
        moves.add(new ChessMove(myPosition,endPosition, ChessPiece.PieceType.BISHOP));
    }


}
