/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import static fr.rphstudio.chess.interf.IChess.BOARD_HEIGHT;
import static fr.rphstudio.chess.interf.IChess.BOARD_WIDTH;
import fr.rphstudio.chess.interf.IChess.ChessColor;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lievredoryan
 */
public class Knight implements IMove {


    @Override
    public List<IChess.ChessPosition> getPossibleMove(ChessPosition p, Board b) {
        
       List<ChessPosition> position = new ArrayList<ChessPosition>();
       
       final Piece piece = b.getPiece(p);       
       
       final ChessColor color = piece.getChessColor();
       
       for(int dx = -2; dx <= 2; dx++) {
           for(int dy = - 2; dy <= 2; dy++) {
               if(Math.abs(dx) + Math.abs(dy) == 3) {
                   
                   ChessPosition tmp = new ChessPosition(p.x + dx, p.y + dy);
                   
                   if(!Move.isOutOfLimit(tmp)) {
                       if(b.getPiece(tmp) == null) {
                           position.add(tmp);
                       }else {
                           if(b.getPiece(tmp).getChessColor() != color) {
                               position.add(tmp);
                           }
                       }
                   }
               }
           }
       }
       return position;
    }
}

