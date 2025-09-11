package chess.pieceMoveLogic;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Pawn {
    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition startPos, ChessGame.TeamColor color) {
        Collection<ChessMove> moves = new ArrayList<>();
        getPawnMovesHelper(board,startPos,moves);
        //This line is weird, I have a duplicate "take" move so this eliminates the duplicate.
        return moves.stream().distinct().collect(Collectors.toList());
    }

    public static void getPawnMovesHelper(ChessBoard board, ChessPosition startPos, Collection<ChessMove> moves) {
        ChessGame.TeamColor color = board.getPiece(startPos).getTeamColor();
        int r = 0;
        if(color == ChessGame.TeamColor.WHITE){
            r = 1;
        }
        else{
            r = -1;
        }

        ChessPosition pos = new ChessPosition(startPos.getRow()+r, startPos.getColumn());
        ChessPiece piece = board.getPiece(pos);
        if(piece == null){
            pawnMoves(board,startPos,pos,moves);
        }

        pos = new ChessPosition(startPos.getRow()+r, startPos.getColumn()+1);
        if(pos.getColumn()>=1 && pos.getColumn()<=8){
            piece = board.getPiece(pos);
            if(piece != null && piece.getTeamColor() != color){
                pawnMoves(board,startPos,pos,moves);
            }
        }

        pos = new ChessPosition(startPos.getRow()+r, startPos.getColumn()-1);
        if(pos.getColumn()>=1 && pos.getColumn()<=8){
            piece = board.getPiece(pos);
            if(piece != null && piece.getTeamColor() != color){
                pawnMoves(board, startPos,pos,moves);
            }
        }
    }

    public static void pawnMoves(ChessBoard board, ChessPosition startPos, ChessPosition endPos, Collection<ChessMove> moves) {
        ChessGame.TeamColor color = board.getPiece(startPos).getTeamColor();
        int startingRow = 0;
        int endRow = 0;
        int doubleChange = 0;
        if(color == ChessGame.TeamColor.WHITE){
            startingRow = 2;
            endRow = 8;
            doubleChange = 2;
        }
        else{
            startingRow = 7;
            endRow = 1;
            doubleChange = -2;
        }

        if(startPos.getRow()==startingRow){
            ChessPosition doubleJump = new ChessPosition(startPos.getRow()+doubleChange,startPos.getColumn());
            if(board.getPiece(doubleJump) == null){
                moves.add(new ChessMove(startPos,doubleJump,null));
            }
        }

        if(endPos.getRow() == endRow){
            moves.add(new ChessMove(startPos,endPos, ChessPiece.PieceType.KNIGHT));
            moves.add(new ChessMove(startPos,endPos, ChessPiece.PieceType.BISHOP));
            moves.add(new ChessMove(startPos,endPos, ChessPiece.PieceType.ROOK));
            moves.add(new ChessMove(startPos,endPos, ChessPiece.PieceType.QUEEN));
            return;
        }
        moves.add(new ChessMove(startPos,endPos, null));
    }

}
