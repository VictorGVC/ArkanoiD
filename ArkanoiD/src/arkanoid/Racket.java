package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Racket {
    protected Rectangle raquete;
    protected double cw,ch,cx,cy;
    protected int maxx,maxy,velx,vely;
    
    public Racket(double cx,double cy,double cw,double ch,Pane pane)
    {
        this.cx=cx; this.cy=cy;
        raquete = new Rectangle(cx,cy,cw,ch);
        raquete.setFill(Paint.valueOf("0xC0C0C0"));
        maxx = (int)pane.getHeight();
        maxy = (int)pane.getHeight();
    }
    
    public Rectangle getRaquete()
    {
        return raquete;
    }
    
    public double getRaqPos()
    {
        return cx;
    }
    
    public void moverM(int direcao)
    {
        this.raquete.setX(direcao);
        this.cx = raquete.getX();
    }
    
    public void mover(String direcao)
    {
        switch (direcao) 
        {
            case "RIGHT": // direita
                this.raquete.setX(this.cx += 40);
                if(cx>=maxx+20)
                    raquete.setX(this.cx = maxx + 20);
            break;
            case "LEFT":
                this.raquete.setX(this.cx -= 40);
                if(cx<=0)
                    raquete.setX(this.cx = 0);
            break;
       }
    }
}