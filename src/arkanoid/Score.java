package arkanoid;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Score {
    private int pontos;
    private Rectangle retangulo; 
            
    public void zerar()
    {
        retangulo = new Rectangle(28,5,738,55);
        retangulo.setArcHeight(15);
        retangulo.setArcWidth(15);
        retangulo.setFill(Paint.valueOf("0xDCDCDC"));
        retangulo.setStroke(Paint.valueOf("0x000000"));
        pontos = 0;
    }
    
    public void somar(int pontos)
    {
        this.pontos += pontos;
    }
    
    public void subtrair(int pontos)
    {
        this.pontos -= pontos;
    }
    
    public int getPontos()
    {
        return pontos;
    }
    
    public Rectangle getRetangulo()
    {
        return retangulo;
    }
    
}
