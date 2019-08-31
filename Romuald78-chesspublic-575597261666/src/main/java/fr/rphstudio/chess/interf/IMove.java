/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import java.util.List;

/**
 *
 * @author lievredoryan
 */
public interface IMove {     

    /**
     * Potentienal list of possible for a piece move)
     * @param p Current position 
     * @param b Board
     * @return
     */
    public List<ChessPosition> getPossibleMove (ChessPosition p, Board b);
      
    /**
     * Class allowing the move of pieces
     */
    public class Move {

        /**
         * Check if out board is not exception
         * @param p the position of piece
         * @return true if is out board exception
         */
        public static boolean isOutOfLimit(ChessPosition p) {
            if(p.x < 0 || p.y < 0 || p.x > 7 || p.y > 7)
                return true;
            return false;
            }
        }
}
