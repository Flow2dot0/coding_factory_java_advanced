/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IChess.ChessColor;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import fr.rphstudio.chess.interf.IChess.ChessType;
import static fr.rphstudio.chess.interf.IChess.ChessType.TYP_BISHOP;
import static fr.rphstudio.chess.interf.IChess.ChessType.TYP_KING;
import static fr.rphstudio.chess.interf.IChess.ChessType.TYP_KNIGHT;
import static fr.rphstudio.chess.interf.IChess.ChessType.TYP_PAWN;
import static fr.rphstudio.chess.interf.IChess.ChessType.TYP_QUEEN;
import static fr.rphstudio.chess.interf.IChess.ChessType.TYP_ROOK;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lievredoryan
 */

                                     
public class Board {                            /*K création de la classe Board */
    
    private HashMap<ChessPosition, Piece> board = new HashMap<ChessPosition, Piece>();  /*K création du constructeur */

    /**
     *
     */
    public HashMap<ChessPosition, Piece> piecesRemovedList = new HashMap<ChessPosition, Piece>(); 
    
    /**
     *
     */
    public Board() {
  
        for(int x = 0; x < 8; x++) {                 /*K boucle initialisant la couleur de la pièce en début de partie*/
            for(int y = 0; y < 8; y++) {
                ChessPosition pos = new ChessPosition(x,y);
                                            
                ChessColor color;
                if(x <= 7 && y <= 1) {
                    color = CLR_BLACK;
                }else if(x<=7 && y >= 6) {
                    color = CLR_WHITE;
                }else {
                    continue;
                }
                
                ChessType type;
                IMove move;                   /*K condition initialisant le type de pièce en fonction de sa pos départ */
                
                if((x == 0 && y == 0) || (x == 0 && y == 7) || (x == 7 && y == 0) || (x == 7 && y == 7)) {
                    type = TYP_ROOK;
                    move = new Rook();
                }
                else if((x == 1 && y == 0) || (x == 6 && y == 0) || (x == 1 && y == 7) || (x == 6 && y == 7))
                {
                    type = TYP_KNIGHT;
                    move = new Knight();
                }
                else if((x == 2 && y == 0) || (x == 5 && y == 0) || (x == 2 && y == 7) || (x == 5 && y == 7))
                {
                    type = TYP_BISHOP;
                    move = new Bishop();
                }
                else if((x == 3 && y == 0) || (x == 3 && y == 7))
                {
                    type = TYP_QUEEN;
                    move = new Queen();
                }
                else if((x == 4 && y == 0) || (x == 4 && y == 7))
                {

                    type = TYP_KING;
                    move = new King();
                }
               else if(y == 1 || y == 6)
               {
                   type = TYP_PAWN;
                   move = new Pawn();
                }
                else {
                    continue;
                }                
                Piece piece = new Piece(type, color, move);
                this.board.put(pos, piece);
            }
        } 
    }
       
    /**
     * Count color 
     * @param color Color of current Chess
     * @return number of the same color Piece
     */
    public int countColorPieces(ChessColor color)
    {
        int ccp = 0;
        
        for(HashMap.Entry<ChessPosition, Piece> tmp : this.board.entrySet())
        {
            if(tmp.getValue().getChessColor() == color)
            {
                ccp++;
            }
        }
        return ccp;
    }
    
    /**
     * Get piece with chess position in board
     * @param pos The chess Position
     * @return null if the case is empty or instance of Piece
     */
    public Piece getPiece(ChessPosition pos) {       /*K getter de la pièce, nous permet de savoir quelle pièce à cette pos */
        
        for(HashMap.Entry<ChessPosition, Piece> tmp : this.board.entrySet()) {
            if(pos.equals(tmp.getKey())) {
                return tmp.getValue();
            }
        }
        return null;
    }
    
    /**
     * Get the position of Piece 
     * @param p the Piece
     * @return ull if the case is empty or instance of ChessPosition
     */
    public ChessPosition getPiecePosition(Piece p)
    {
        for(HashMap.Entry<ChessPosition, Piece> tmp : this.board.entrySet())
        {
            if(tmp.getValue().equals(p))
            {
                return tmp.getKey();
            }
        }
        return null;
    }
    
    /**
     * Get the current position of King
     * @param color The color of King
     * @return the position of King
     */
    public ChessPosition getPositionKing(ChessColor color)
    {
        for(HashMap.Entry<ChessPosition, Piece> tmp : this.board.entrySet())
        {

            if(tmp.getValue().getChessType() == TYP_KING && tmp.getValue().getChessColor() == color)
            {
                return tmp.getKey();
            }
        }
        return null;
    }
    
    /**
     * Get ennemies of current color
     * @param color The color of ennemy
     * @return
     */
    public HashMap<ChessPosition, Piece> getlistEnnemies(ChessColor color)
    {
        HashMap<ChessPosition, Piece> listEnnemies = new HashMap<ChessPosition, Piece>();
        
        for(HashMap.Entry<ChessPosition, Piece> tmp2 : this.board.entrySet())
        {
            if(tmp2.getValue().getChessColor() != color)
            {
                    listEnnemies.put(tmp2.getKey(), tmp2.getValue());
            }
        }
        return listEnnemies;
    }
    
    /**
     * Get the list of deleted pieces
     * @param color Color of pieces
     * @return List of deleted pieces
     */
    public List<ChessType> getPiecesRemovedList(ChessColor color)
    {
        List<ChessType> listPiecesRemoved = new ArrayList();
        
        for(HashMap.Entry<ChessPosition, Piece> tmp : this.piecesRemovedList.entrySet())
        {
            if(tmp.getValue().getChessColor() == color)
            {
                listPiecesRemoved.add(tmp.getValue().getChessType());
            }
        }
        return listPiecesRemoved;
    }
    
    /**
     * Get the board
     * @return board
     */
    public HashMap<ChessPosition, Piece> getBoard()
    {
        return this.board;
    }
     
    /**
     * Set piece of specific position
     * @param pos the desired position
     * @param piece
     */
    public void setPiece(ChessPosition pos, Piece piece){
        this.board.put(pos, piece);
    }

    /**
     * Remove a piece with specific position
     * @param pos position of the piece you wish to delete
     */
    public void removePiece(ChessPosition pos){
        for(ChessPosition i : this.board.keySet())
        {
            if(pos.equals(i))
            {
                this.board.remove(i);
                break;
            }
        }
    }
      
    /**
     * Check out of board exception
     * @param p the current position of Piece
     * @return 
     */
    public boolean isPositionInBoard(ChessPosition p){       /*K condition, la pièce est elle sur le board ou non */
        
        boolean bool;
        if ((p.x >= 0 && p.x <= 7)&&(p.y >= 0 && p.y <= 7)){
           bool = true;
        }
        else {
           bool = false;
        }
        return bool;
    }  
}
