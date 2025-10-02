package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard board;
    private TeamColor teamTurn;
    private TeamColor winner = null;

    public ChessGame() {
        board = new ChessBoard();
        board.resetBoard();
        teamTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> validM = new ArrayList<>();
        ChessPiece piece = board.getPiece(startPosition);
        Collection<ChessMove> posMoves = piece.pieceMoves(board,startPosition);
        ChessBoard savedBoard;
        for(ChessMove move : posMoves){
            savedBoard = board.copy();
            board.addPiece(move.getEndPosition(),piece);
            //Maybe here
            board.addPiece(startPosition,null);
            if(!isInCheck(piece.getTeamColor())){
                validM.add(move);
            }
            board = savedBoard;
        }
        return validM;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if(winner != null){
            throw new InvalidMoveException("Game is over!");
        }
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();
        ChessPiece piece = board.getPiece(start);
        if(piece == null){
            throw new InvalidMoveException("Position is empty");
        }
        if(piece.getTeamColor() != teamTurn){
            throw new InvalidMoveException("Its not your turn");
        }
        Collection<ChessMove> validMovesToTest = validMoves(start);
        if(!validMovesToTest.contains(move)){
            throw new InvalidMoveException("Invalid Move");
        }

        ChessPiece.PieceType promotes = move.getPromotionPiece();
        if (promotes == null){
            board.addPiece(end,piece);
            board.addPiece(start,null);
        }else{
            ChessPiece pawnPromotion = new ChessPiece(piece.getTeamColor(),promotes);
            board.addPiece(end,pawnPromotion);
            board.addPiece(start,null);
        }
        if(isInCheckmate(TeamColor.BLACK)){
            winner = TeamColor.WHITE;
            return;
        }
        if(isInCheckmate(TeamColor.WHITE)){
            winner = TeamColor.BLACK;
            return;
        }
        //switch color
        teamTurn = teamTurn==TeamColor.BLACK? TeamColor.WHITE : TeamColor.BLACK;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //HERE IS MY ISSUE, I am not looking at the new fake board I think.
        ChessPosition kingPos = findKing(teamColor);
        for(int x = 1; x<=8; x++){
            for(int y = 1; y<=8; y++){
                if(searchAttackKing(x,y,teamColor,kingPos)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(isInCheck(teamColor)){
            if(!validMovesExist(teamColor)){
                if(teamColor.equals(TeamColor.WHITE)){
                    winner = TeamColor.BLACK;
                }else{
                    winner = TeamColor.WHITE;
                }
                return true;
            }
        }
        return false;
    }



    public boolean validMovesExist(TeamColor teamColor){
        Collection<ChessMove> validM = new ArrayList<>();
        for(int r = 1; r<=8; r++){
            for(int c = 1; c<=8; c++){
                ChessPosition pos = new ChessPosition(r,c);
                ChessPiece piece = board.getPiece(pos);
                if(piece != null && piece.getTeamColor() == teamColor){
                    validM.addAll(validMoves(pos));
                    if(!validM.isEmpty()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if(!isInCheck(teamColor)){
            if(!validMovesExist(teamColor)){
                return true;
            }
        }
        return false;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }

    private ChessPosition findKing(TeamColor color){
        for(int x = 1; x<=8; x++) {
            for (int y = 1; y <= 8; y++) {
                ChessPosition pos = new ChessPosition(x,y);
                ChessPiece piece = board.getPiece(pos);
                if(piece != null && piece.getPieceType() == ChessPiece.PieceType.KING && piece.getTeamColor() == color){
                    return pos;
                }
            }
        }
        return null;
    }


    private boolean searchAttackKing(int x, int y,TeamColor color, ChessPosition kingPos){
        ChessPosition pos = new ChessPosition(x,y);
        ChessPiece piece = board.getPiece(pos);
        if(piece != null && piece.getTeamColor()!=color){
            Collection<ChessMove> checks = piece.pieceMoves(board,kingPos);
            return containsKingPosition(checks,kingPos);
        }
        return false;
    }



    private boolean containsKingPosition(Collection<ChessMove> moves, ChessPosition target) {
        for (ChessMove move : moves) {
            if (move.getEndPosition().equals(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) && teamTurn == chessGame.teamTurn && winner == chessGame.winner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, teamTurn, winner);
    }
}
