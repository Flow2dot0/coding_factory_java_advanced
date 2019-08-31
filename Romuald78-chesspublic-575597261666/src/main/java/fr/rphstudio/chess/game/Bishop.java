/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lievredoryan
 */
public class Bishop implements IMove {
    
    @Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board b) {
        
       List<IChess.ChessPosition> position = new ArrayList<IChess.ChessPosition>();
       
       final Piece piece = b.getPiece(p);       
       
       final IChess.ChessColor color = piece.getChessColor();
       
       for(int dir = 0; dir <= 3 ; dir++)
       {
           int dx = 0;
           int dy = 0;
           
           // creating directions
           if(dir == 0)
           {
               dx = -1;
               dy = -1;
           }
           else if(dir == 1)
           {
               dx = -1;
               dy = 1;
           }
           else if(dir == 2)
           {
               dx = 1;
               dy = 1;
           }
           else if(dir == 3)
           {
               dx = 1;
               dy = -1;
           }
           
           for(int dist = 1 ; dist <= 7 ; dist++)
           {
               IChess.ChessPosition target = new IChess.ChessPosition(p.x+(dist*dx),p.y+(dist*dy));
               
               // if not outside
                if(!Move.isOutOfLimit(target)) 
                {
                    // if empty
                    if(b.getPiece(target) == null)
                    {
                        position.add(target);
                    }
                    // if different color and previous is null
                    else if(b.getPiece(target).getChessColor() != color)
                    {
                        position.add(target);
                        break;
                    }
                    else
                    {
                        break;
                    }

                }

           }
       }
        return position;

    }
    
}
