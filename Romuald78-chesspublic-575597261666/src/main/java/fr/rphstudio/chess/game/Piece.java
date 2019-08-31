/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.ChessColor;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import fr.rphstudio.chess.interf.IChess.ChessType;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lievredoryan
 */
public class Piece {
    
    private ChessColor color;
    private ChessType type;
    private IMove imove;
    
    /**
     * Initialize the piece of chess
     * @param type type
     * @param color color
     * @param i Move
     * @see IMove
     */
    public Piece(ChessType type, ChessColor color, IMove i) {
        this.type = type;
        this.color = color;
        this.imove = i; 
    }
    
    /**
     * Return the color of current piece
     * @return
     */
    public ChessColor getChessColor() {
        return this.color;
    }
    
    /**
     * Return the type of current piece
     * @return
     */
    public ChessType getChessType() {
        return this.type;
    }
    
    /**
     * Return the move of current piece
     * @return
     */
    public IMove getImove()
    {
        return this.imove;
    }
    
    /**
     * Set the type of current piece
     * @param t Chesstype
     */
    public void setChessType(ChessType t) {
        this.type = t;
    }
    
    /**
     * Set the move of current piece
     * @param i Move
     */
    public void setImove(IMove i) {
        this.imove = i;
    }
    
    /**
     * Return the all possible moves
     * @param p The position of Piece
     * @param b The board
     * @return array of possible moves
     */
    public List<ChessPosition> getMoves(ChessPosition p, Board b){
       return this.imove.getPossibleMove(p, b);
   }    
}
    
