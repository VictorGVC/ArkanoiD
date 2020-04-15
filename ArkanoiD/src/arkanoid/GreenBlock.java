package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GreenBlock extends AbstractBlock{
    
    protected int velx;
    protected boolean aciona;
    public GreenBlock(int cx,int cy,int cw,int ch,Pane pane)
    {
        aciona = false;
        velx = 1;
        this.cx=cx; this.cy=cy;
        bloco = new Rectangle(cx,cy,cw,ch);
        bloco.setFill(Paint.valueOf("0x36E702"));
        maxx = (int)pane.getHeight();
    }
    
    public int getMax()
    {
        return maxx;
    }
    
    public void acionaFuncao()
    {
        aciona = true;
    }
    
    public void MoverY()
    {
        cy++;
        bloco.setY(cy);
    }
    
    public void moverX()
    {
        cx+=velx;
        if(cx>=getMax() || cx<0)
        {
            velx=velx*-1;
        }
        bloco.setX(cx);
    }
    
    public boolean ativado()
    {
        return aciona;
    }
}
