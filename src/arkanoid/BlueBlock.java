package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class BlueBlock extends AbstractBlock{
    
    protected boolean aciona;
    public BlueBlock(double cx,double cy,double cw,double ch,Pane pane)
    {
        super(cx,cy,cw,ch,pane);
        aciona = false;
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
    
    public int getPontos(Racket raquete)
    {
        return 40;
    }
    
    public int getPontos(Ball bolinha)
    {
        return 60;
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
