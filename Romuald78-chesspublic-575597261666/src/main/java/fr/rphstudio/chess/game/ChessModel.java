/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;
import static fr.rphstudio.chess.interf.IChess.ChessType.TYP_ROOK;
import fr.rphstudio.chess.interf.IMove;
import fr.rphstudio.chess.interf.OutOfBoardException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lievredoryan
 */
public class ChessModel implements IChess{
    
    private Board board = new Board();
    
    /**
     * Remove list of black pieces
     */
    public List<Piece> PieceRemovedBlack = new ArrayList();

    /**
     * Remove list of white pieces
     */
    public List<Piece> PieceRemovedWhite = new ArrayList();
    
    private long timeWhite;
    private long timeBlack;
    
    /**
     * Logs list
     */
    public HashMap<ChessPosition, Piece> logs = new HashMap();


    // private constructor restricted to this class itself(Model singleton)
    private ChessModel(){
        this.board = new Board();
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        timeWhite = time;
        timeBlack = time;
    }

    private static ChessModel instance = null;

    // static method to create instance of ChessModel class

    /**
     * Get instance of ChessModel (singleton)
     * @return
     */
    public static ChessModel getInstance(){
        if (instance == null)
            instance = new ChessModel();

        return instance;
    }


    @Override
    public void reinit() {
       this.board = new Board();
        PieceRemovedBlack = new ArrayList();
        PieceRemovedWhite = new ArrayList();
    } 

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        
        if(this.board.getPiece(p) == null)
            throw new EmptyCellException();
        else
            return this.board.getPiece(p).getChessType();
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(this.board.getPiece(p) == null)
            throw new EmptyCellException();
        else
            return this.board.getPiece(p).getChessColor();
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {
        int hsize = this.board.countColorPieces(color);
        return hsize;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        Piece pi = this.board.getPiece(p);
        return pi.getMoves(p, this.board);
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        Piece pieceToMove = this.board.getPiece(p0);
        Piece pieceToDelete = this.board.getPiece(p1);
        
        if(pieceToMove.getChessColor() == CLR_WHITE) {
            getPlayerDuration(CLR_WHITE, true);
        }else {
            getPlayerDuration(CLR_BLACK, true);

        }

        if(pieceToMove.getChessType() == ChessType.TYP_PAWN && pieceToMove.getChessColor() == ChessColor.CLR_WHITE)
        {
            if(p1.x >=0 && p1.x <= 7 && p1.y == 0)
            {
               //if(pieceToDelete.getChessType() != ChessType.TYP_KING)
               //{
                    pieceToMove.setChessType(ChessType.TYP_QUEEN);
                    pieceToMove.setImove(new Queen());
                    this.switchPiece(p0, p1, pieceToMove);
                    this.PieceRemovedBlack.add(pieceToDelete);
                //}
            }
            else
            {
                if(board.getPiece(p1) == null)
                {
                    this.switchPiece(p0, p1, pieceToMove);
                }
                else if(board.getPiece(p1).getChessColor() != pieceToMove.getChessColor())
                {
                    this.switchPiece(p0, p1, pieceToMove);
                    this.PieceRemovedBlack.add(pieceToDelete);
                }
            }
            
        }
        else if(pieceToMove.getChessType() == ChessType.TYP_PAWN && pieceToMove.getChessColor() == ChessColor.CLR_BLACK)
        {
            if(p1.x >=0 && p1.x <= 7 && p1.y == 7)
            {
               //if(pieceToMove.getChessType() != ChessType.TYP_KING)
               //{
                    pieceToMove.setChessType(ChessType.TYP_QUEEN);
                    pieceToMove.setImove(new Queen());
                    
                    this.switchPiece(p0, p1, pieceToMove);
                    this.PieceRemovedWhite.add(pieceToDelete);
                //}
            }
            else
            {
                if(board.getPiece(p1) == null)
                {
                    this.switchPiece(p0, p1, pieceToMove);
                }
                else if(board.getPiece(p1).getChessColor() != pieceToMove.getChessColor())
                {
                    this.switchPiece(p0, p1, pieceToMove);
                    this.PieceRemovedWhite.add(pieceToDelete);
                }
            }
            
        }

//        else if(pieceToMove.getChessType() == ChessType.TYP_KING && pieceToMove.getChessColor() == ChessColor.CLR_BLACK)
//        {
//            System.out.println("coucou1");
//            //boucle sur les logs
//            for(HashMap.Entry<ChessPosition, Piece> tmp : logs.entrySet())
//            {
//                System.out.println("coucou");
//                //si il y a un mouvement de roi
//                if(tmp.getValue().getChessType() != ChessType.TYP_KING)
//                {
//                    HashMap <ChessPosition, Piece> checkEmptyCellOnBoard = this.board.getBoard();
//                    
//                    //on reboucle sur l'échiquier
//                    for(HashMap.Entry<ChessPosition, Piece> checkEmptyCell : checkEmptyCellOnBoard.entrySet())
//                    {
//                        ChessPosition cellTopLeft1 = new ChessPosition(1, 0);
//                        ChessPosition cellTopLeft2 = new ChessPosition(2, 0);
//                        ChessPosition cellTopLeft3 = new ChessPosition(3, 0);
//                        
//                        ChessPosition cellTopRight1 = new ChessPosition(6, 0);
//                        ChessPosition cellTopRight2 = new ChessPosition(5, 0);
//                        
//                        ChessPosition cellBottomLeft1 = new ChessPosition(1, 7);
//                        ChessPosition cellBottomLeft2 = new ChessPosition(2, 7);
//                        ChessPosition cellBottomLeft3 = new ChessPosition(3, 7);
//                        
//                        ChessPosition cellBottomRight1 = new ChessPosition(6, 7);
//                        ChessPosition cellBottomRight2 = new ChessPosition(5, 7);
//
//                        List<ChessPosition> posList = new ArrayList();
//
//                        //si
//                        if(checkEmptyCell.getKey().equals(cellTopLeft1) || checkEmptyCell.getKey().equals(cellTopLeft2) || checkEmptyCell.getKey().equals(cellTopLeft3))
//                        {
//                            posList.add(checkEmptyCell.getKey());
//                        }
//                        else if(checkEmptyCell.getKey().equals(cellTopRight1) || checkEmptyCell.getKey().equals(cellTopRight2))
//                        {
//                            posList.add(checkEmptyCell.getKey());
//                        }
//                        else if(checkEmptyCell.getKey().equals(cellBottomLeft1) || checkEmptyCell.getKey().equals(cellBottomLeft2) || checkEmptyCell.getKey().equals(cellBottomLeft3))
//                        {
//                            posList.add(checkEmptyCell.getKey());
//                        }
//                        else if(checkEmptyCell.getKey().equals(cellBottomRight1) || checkEmptyCell.getKey().equals(cellBottomRight2))
//                        {
//                            posList.add(checkEmptyCell.getKey());
//                        }
//                   
//                        //si il y a pas de pieces avec les coordonnées 1/2/3/4
//                        for(int y = 0 ; y < posList.size(); y++)
//                        {
//                            ChessType rookType = TYP_ROOK;
//                            IMove rookMove = new Rook();
//                            ChessColor rookColor = ChessColor.CLR_BLACK;
//                            Piece rookPiece = new Piece(rookType, rookColor , rookMove);
//                            
//                            if(posList.get(y).equals(cellTopLeft3))
//                            {
//                                board.removePiece(p1);
//                                board.setPiece(p1, pieceToMove);
//                                board.removePiece(p0);
//                                board.setPiece(cellTopLeft3, rookPiece); 
//                            }
//                        }
//                    }
//                
//                }
//            }
//        }

        else
        {
            
            if(board.getPiece(p1) == null)
           {
               this.switchPiece(p0, p1, pieceToMove);
           }
           else if(board.getPiece(p1).getChessColor() != pieceToMove.getChessColor())
           {
               if(board.getPiece(p1).getChessColor() == ChessColor.CLR_BLACK)
               {
                   this.switchPiece(p0, p1, pieceToMove);
                   this.PieceRemovedBlack.add(pieceToDelete);
               }
               else if(board.getPiece(p1).getChessColor() == ChessColor.CLR_WHITE)
               {
                   this.switchPiece(p0, p1, pieceToMove);
                   this.PieceRemovedWhite.add(pieceToDelete);
               }
           }
        }

    }
    
    /**
     * Switch pieces position
     * @param p0 Initial position
     * @param p1 new position
     */
    public void switchPiece(ChessPosition p0, ChessPosition p1, Piece piece)
    {
        this.logs.put(p0, piece);
        board.removePiece(p1);
        board.removePiece(p0);
        board.setPiece(p1, piece);
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        
        ChessPosition posKing = this.board.getPositionKing(color);

        HashMap<ChessPosition, Piece> EnnemiesList = this.board.getlistEnnemies(color);

        for(HashMap.Entry<ChessPosition, Piece> Ennemy : EnnemiesList.entrySet())
        {
            ChessPosition posEnnemy = Ennemy.getKey();
            
            List<ChessPosition> possibleMoveEnnemy = this.getPieceMoves(posEnnemy);
            
            for(int i = 0; i < possibleMoveEnnemy.size(); i++)
            {
                if(posKing.equals(possibleMoveEnnemy.get(i)))
                {
                    return ChessKingState.KING_THREATEN;
                }
            }
        }
        return ChessKingState.KING_SAFE;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        List<Piece> PiecesList = this.getListRemovedPieces(color);
        List<ChessType> list = new ArrayList();

        for(Piece p : PiecesList)
        {
            list.add(p.getChessType());
        }

        return list;
    }
    
    /**
     * Return color list of pieces removed
     * @param color
     * @return color list into array list
     */
    public List<Piece> getListRemovedPieces(ChessColor color)
    {
        if(color == ChessColor.CLR_WHITE)
        {
            return this.PieceRemovedWhite;
        }
        else
        {
            return this.PieceRemovedBlack;
        }
    }

    @Override
    public boolean undoLastMove() {
        return false;
    }

@Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        
        long time = 0;
        
        time = (color == CLR_WHITE) ? timeWhite:timeBlack;
        
        long tmp = new Timestamp(System.currentTimeMillis()).getTime();

        if(isPlaying) {
            
            if(color == CLR_WHITE) {
                timeBlack = tmp - timeBlack;
                time = timeBlack;

            }
            else {
                timeWhite = tmp - timeWhite;
                time = timeWhite;

            }
            
            
        }
        
        
        return time;
    }
}