package arkanoid;

import javafx.scene.shape.Rectangle;

public abstract class AbstractBlock {
    
    protected int cx,cy,maxx,maxy;
    protected Rectangle bloco = new Rectangle();
    
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
}