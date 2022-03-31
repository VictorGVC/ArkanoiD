package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends GameObject{
   
    private Circle bolinha;
    private int velx, vely,dificuldade;
    public Ball(double cx, double cy, int raio,Pane pane,int dif)
    {   
        super(cx,cy,pane);
        this.dificuldade = dif;
        bolinha = new Circle(cx,cy,raio);
        bolinha.setFill(Paint.valueOf("0x0000FF"));
        velx=vely=dificuldade*=-1;
        bolinha.setStroke(Paint.valueOf("0x000000"));
    }

    public double getMaxx() {
        return maxx;
    }

    public double getMaxy() {
        return maxy;
    }

    public Circle getImagem()
    {
        return bolinha;
    }

    public boolean colidiu(Ball outro)
    {
        return bolinha.getBoundsInParent().intersects(outro.getImagem().getBoundsInParent());
    }
    
    public void reposicionar(double cx,double cy)
    {
        this.cx = (int) cx;
        this.cy = (int) cy;
        this.vely *= -1;
        bolinha.setCenterX(cx);
        bolinha.setCenterY(cy);
    }
    
    public void mover()
    {
        cx+=velx;
        cy+=vely;
        if( cx>=getMaxx()-8 || cx<10)
           velx=velx*-1; //muda a direção
        if(cy<=80)
           vely=vely*-1;  //muda a direção

        bolinha.setCenterX(cx);
        bolinha.setCenterY(cy);
    }
    
    boolean colidiu(Racket raquete)
    {
       return this.bolinha.getBoundsInParent().intersects(raquete.getRaquete().getBoundsInParent());
    }
    
    boolean colidiu(AbstractBlock bloco)
    {
        return this.bolinha.getBoundsInParent().intersects(bloco.getBlock().getBoundsInParent());
    }

    public void mudaBolinha(double cx)
    {
        if(vely>0)
            this.vely*=-1;
        if(velx>0&&this.cx<=cx+25)
            velx*=-1;
        else if(velx<=0&&this.cx>=cx+75)
            velx*=-1;
    }
    
    public void mudaBolinha(double cx,double cy)
    {   
        if(this.cx>=cx+100&&this.cy>=cy+30&&velx<0&&vely<0)
        {
            vely*=-1;
            velx*=-1;
        }
        else if(this.cx>=cx+100&&this.cy<=cy&&velx<0&&vely>0)
        {
            vely*=-1;
            velx*=-1;
        }
        else if(this.cx<=cx&&this.cy>=cy+30&&velx>0&&vely<0)
        {
            vely*=-1;
            velx*=-1;
        }
        else if(this.cx<=cx&&this.cy<=cy&&velx>0&&vely>0)
        {
            vely*=-1;
            velx*=-1;
        }
        else if(this.cx>=cx&&this.cx<=cx+100)
            vely*=-1;
        else if(this.cy>=cy-10&&this.cy<=cy+40)
            velx*=-1;
    }   

    public boolean perdeu()
    {
        return bolinha.getCenterY()>=getMaxy()+30;
    }
}


