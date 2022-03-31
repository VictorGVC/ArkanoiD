package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class AbstractBlock extends GameObject{
    
    protected int pontos;
    protected Rectangle bloco = new Rectangle();
    
    public AbstractBlock(double cx,double cy,double cw,double ch,Pane pane)
    {
        super(cx,cy,cw,ch,pane);
        bloco = new Rectangle(cx,cy,cw,ch);
        bloco.setArcHeight(15);
        bloco.setArcWidth(15);
        bloco.setStroke(Paint.valueOf("0x000000"));
    }
    
    public Rectangle getBlock()
    {
        return bloco;
    }
    
    public double getPosX()
    {
        return bloco.getX();
    }
    
    public double getPosY()
    {
        return bloco.getY();
    }
    
    public int getPontos()
    {
        return pontos;
    }
    
}