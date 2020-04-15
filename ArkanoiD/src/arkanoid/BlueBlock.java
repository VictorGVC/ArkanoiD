package arkanoid;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BlueBlock extends AbstractBlock{
    
    protected boolean aciona;
    public BlueBlock(int cx,int cy,int cw,int ch)
    {
        aciona = false;
        this.cx=cx; this.cy=cy;
        bloco = new Rectangle(cx,cy,cw,ch);
        bloco.setFill(Paint.valueOf("0x005DFF"));
    }
    
    public void acionaFuncao()
    {
        aciona = true;
    }
    
    public void mover()
    {
        cy++;
        bloco.setY(cy);
    }
    
    public boolean ativado()
    {
        return aciona;
    }
    
    public boolean colidiu(Racket raquete)
    {
        return this.bloco.getBoundsInParent().intersects(raquete.raquete.getBoundsInParent());
    }
}
