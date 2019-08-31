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
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lievredoryan
 */
public class Pawn implements IMove  {

    @Override
    public List<ChessPosition> getPossibleMove(ChessPosition p, Board b) {
    
       List<IChess.ChessPosition> position = new ArrayList<IChess.ChessPosition>();
       
       final Board board = b;
       final Piece piece = b.getPiece(p);       
       final ChessColor color = piece.getChessColor();
       final ChessPosition initial = new IChess.ChessPosition(p.x, p.y);
       
       for(int dir = 0; dir <= 5 ; dir++)
       {
            if(dir == 0 && color == CLR_BLACK && !Move.isOutOfLimit(initial)) // bottom left
           {
                ChessPosition targetAtk = new IChess.ChessPosition(p.x-1,p.y+1);

                if(b.getPiece(targetAtk) != null && !Move.isOutOfLimit(targetAtk) && b.getPiece(targetAtk).getChessColor() != color)
                {
                    position.add(targetAtk);
                }
           }
            else if(dir == 1 && color == CLR_BLACK && !Move.isOutOfLimit(initial)) // bottom right
           {
                ChessPosition targetAtk = new IChess.ChessPosition(p.x+1,p.y+1);

                if(b.getPiece(targetAtk) != null && !Move.isOutOfLimit(targetAtk) && b.getPiece(targetAtk).getChessColor() != color)
                {
                    position.add(targetAtk);
                }
           }
            else if(dir == 2 && color == CLR_WHITE && !Move.isOutOfLimit(initial)) // top left
           {
                ChessPosition targetAtk = new IChess.ChessPosition(p.x-1,p.y-1);

                if(b.getPiece(targetAtk) != null && !Move.isOutOfLimit(targetAtk) && b.getPiece(targetAtk).getChessColor() != color)
                {
                    position.add(targetAtk);
                }
           }
            else if(dir == 3 && color == CLR_WHITE && !Move.isOutOfLimit(initial)) // top right
           {
                ChessPosition targetAtk = new IChess.ChessPosition(p.x+1,p.y-1);

                if(b.getPiece(targetAtk) != null && !Move.isOutOfLimit(targetAtk) && b.getPiece(targetAtk).getChessColor() != color)
                {
                    position.add(targetAtk);
                }
           }
           else if(dir == 4 && color == CLR_WHITE && !Move.isOutOfLimit(initial)) // top
           {
                ChessPosition target = new IChess.ChessPosition(p.x,p.y-1);
                ChessPosition nextTarget = new IChess.ChessPosition(p.x, p.y-2);
                
                if(initial.x >= 0 && initial.y == 6)
                { 
                    if(b.getPiece(target) == null && !Move.isOutOfLimit(target))
                    {
                        position.add(target);
                        if(b.getPiece(nextTarget) == null && !Move.isOutOfLimit(nextTarget))
                            position.add(nextTarget);
                    }
                }
                else
                {
                    if(b.getPiece(target) == null)
                    {
                        position.add(target);
                    }
                }
           }
           else if(dir == 5 && color == CLR_BLACK  && !Move.isOutOfLimit(initial)) // bottom
           {
    
                ChessPosition target = new IChess.ChessPosition(p.x,p.y+1);
                ChessPosition nextTarget = new IChess.ChessPosition(p.x, p.y+2);
                
                if(initial.x >= 0 && initial.y == 1)
                { 
                    if(b.getPiece(target) == null && !Move.isOutOfLimit(target))
                    {
                        position.add(target);
                        if(b.getPiece(nextTarget) == null && !Move.isOutOfLimit(nextTarget))
                            position.add(nextTarget);
                    }
                }
                else
                {
                    if(b.getPiece(target) == null)
                    {
                        position.add(target);
                    }
                }
           }
       }
       return position;
    }
}    
