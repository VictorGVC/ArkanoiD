package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class GameObject{
    protected double cx,cy,ch,cw,maxx,maxy;
    protected String cor;
    
    public GameObject(double cx,double cy,double ch, double cw,Pane pane) {
        setCh(ch);
        setCw(cw);
        setCx(cx);
        setCy(cy);
        maxx=(int)pane.getWidth();
        maxy=(int)pane.getHeight();
    }
    
    public GameObject(double cx,double cy,Pane pane) {
        setCx(cx);
        setCy(cy);
        maxx=(int)pane.getWidth();
        maxy=(int)pane.getHeight();
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public double getCh() {
        return ch;
    }

    public void setCh(double ch) {
        this.ch = ch;
    }

    public double getCw() {
        return cw;
    }

    public void setCw(double cw) {
        this.cw = cw;
    }
    
}
