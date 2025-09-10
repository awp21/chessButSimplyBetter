package chess;

import java.util.Collection;
import java.util.Objects;


import chess.pieceMoveLogic.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor color;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor color, ChessPiece.PieceType type) {
        this.color = color;
        this.type = type;
    }


    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        switch (type) {
            case ROOK:
                return Rook.getMoves(board, myPosition, color);
            case BISHOP:
                return Bishop.getMoves(board, myPosition, color);
            case QUEEN:
                return Queen.getMoves(board, myPosition, color);
            case KING:
                return King.getKingMoves(board, myPosition, color);
            case KNIGHT:
                return Knight.getKnightMoves(board, myPosition, color);
            case PAWN:
                return Pawn.getMoves(board, myPosition, color);
        }
        return null;
    }

    public static boolean getMovesHelper(ChessBoard board, ChessPosition startPosition, int rOff, int cOff, ChessGame.TeamColor color, Collection<ChessMove> moves) {
        int r = startPosition.getRow()+rOff;
        int c = startPosition.getColumn()+cOff;
        ChessPosition pos = new ChessPosition(r, c);
        try{
            inBounds(pos);
        } catch (Exception _) {
            return false;
        }
        ChessPiece piece = board.getPiece(pos);
        if(piece == null){
            moves.add(new ChessMove(startPosition, pos,null));
            return true;
        }
        else if(piece.getTeamColor() != color){
            moves.add(new ChessMove(startPosition, pos,null));
            return false;
        }
        return false;
    }

    public static void inBounds(ChessPosition position) throws Exception{
        int r = position.getRow();
        int c = position.getColumn();
        if (r>8 || r<1 || c>8 || c<1){
            throw new Exception("Position out of Bounds");
        }
    }



    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChessPiece piece)) {
            return false;
        }
        return color == piece.color && type == piece.type;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "color=" + color +
                ", type=" + type +
                '}';
    }
}
