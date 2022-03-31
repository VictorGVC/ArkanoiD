package arkanoid;

import javafx.scene.paint.Paint;

public class GreenBlock extends AbstractBlock{
    
    protected int velx;
    protected boolean aciona;
    public GreenBlock(double cx,double cy,double cw,double ch,Game pane)
    {
        super(cx,cy,cw,ch,pane);
        pontos = 45;
        aciona = false;
        velx = 1;
        bloco.setFill(Paint.valueOf("0x36E702"));
    }
    
    public double getMax()
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
        if(cx>=getMax()-100 || cx<0)
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
