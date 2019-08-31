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
public class Rook implements IMove {
        
    @Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board b) {
        
        List<IChess.ChessPosition> position = new ArrayList<IChess.ChessPosition>();
        
        int dx = p.x;
        int dy = p.y;
        
        for(int i = 1 ; i <= 4 ; i++)
        {
            this.direction(dx, dy, position, p, b, i);
        } 
        return position;
    }
    
    /**
     * Choose the orientation of piece
     * @param dx x axis 
     * @param dy y axis
     * @param position List of position
     * @param p The current position of piece
     * @param b The board
     * @param direction top, left, right, bottom direction
     */
    public void direction(int dx, int dy, List<IChess.ChessPosition> position, IChess.ChessPosition p, Board b, int direction)
    { 
        for(int i = 1; i <= 7; i++)
        {
            IChess.ChessPosition pos = new IChess.ChessPosition(dx, dy);
            
            if(direction == 1)
            {
                pos.x = dx;
                pos.y = dy+i;
            }
            else if(direction == 2)
            {
                pos.x = dx-i;
                pos.y = dy;
            }
            else if(direction == 3)
            {
                pos.x = dx;
                pos.y = dy-i;
            }
            else
            {
                pos.x = dx+i;
                pos.y = dy;
            }
            
            if(b.isPositionInBoard(pos) == false)
            {
                break;
            }
            else
            {
                if(b.getPiece(pos) == null)
                {
                    position.add(pos);
                }
                else if(b.getPiece(pos).getChessColor() != b.getPiece(p).getChessColor())
                {
                    position.add(pos);
                    break;
                }
                else if(b.getPiece(pos).getChessColor() == b.getPiece(p).getChessColor())
                {
                    break;
                }
            }
        }
        
            
    }
        
}
     
    

