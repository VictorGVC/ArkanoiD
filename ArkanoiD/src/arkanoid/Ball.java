package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
   
    protected Circle imagem;
    protected int cx, cy, velx, vely, maxx, maxy,dificuldade;
    public Ball(int cx, int cy, int raio,Pane pane,int dif)
    {   
        this.dificuldade = dif;
        this.cx=cx; this.cy=cy;
        imagem = new Circle(cx,cy,raio);
        imagem.setFill(Paint.valueOf("0x0000FF"));
        velx=vely=dificuldade;
        maxx=(int)pane.getWidth(); // largura da tela
        maxy=(int)pane.getHeight(); // altura da tela
    }

    public int getMaxx() {
        return maxx;
    }

    public int getMaxy() {
        return maxy;
    }

    public Circle getImagem()
    {
        return imagem;
    }

    public boolean colidiu(Ball outro)
    {
        return imagem.getBoundsInParent().intersects(outro.getImagem().getBoundsInParent());
    }
    
    public void acelerar()
    {   //pode acelerar, parar ou diminuir
        if((int)(Math.random()*2)==0)
           velx++;
        else
           vely++;
    }
    
    public void mover()
    {
        cx+=velx;
        cy+=vely;
        if( cx>=getMaxx()-9 || cx<10)
           velx=velx*-1; //muda a direção
        if( cy>=getMaxy()-9 || cy<15)
           vely=vely*-1;  //muda a direção

        imagem.setCenterX(cx);
        imagem.setCenterY(cy);
    }
    
    boolean colidiu(Racket outro)
    {
       return this.imagem.getBoundsInParent().intersects(outro.raquete.getBoundsInParent());
    }
    
    boolean colidiu(AbstractBlock bloco)
    {
        return this.imagem.getBoundsInParent().intersects(bloco.bloco.getBoundsInParent());
    }

    public void mudaBolinha(double cx)
    {
        if(vely>0)
            this.vely*=-1;
        if(this.cx>=cx&&velx>0&&this.cx<=cx+25)
            velx*=-1;
        else if(this.cx<=cx+100&&velx<=0&&this.cx>=cx+75)
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
        else if(this.cx<=cx&&this.cy>=cy&&velx>0&&vely>0)
        {
            vely*=-1;
            velx*=-1;
        }
        else if(this.cx>=cx&&this.cx<=cx+100)
            vely*=-1;
        else if(this.cy>=cy-10&&this.cy<=cy+40)
            velx*=-1;
    }       
}


