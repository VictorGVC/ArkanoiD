package arkanoid;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class YellowBlock extends AbstractBlock{
    
    YellowBlock(int cx,int cy,int cw,int ch)
    {
        this.cx=cx; this.cy=cy;
        bloco = new Rectangle(cx,cy,cw,ch);
        bloco.setFill(Paint.valueOf("0xF3DD00"));
    }
}
