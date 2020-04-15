package arkanoid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

class GamePane extends Pane{
    
    private int dificuldade = 5;     
    Random rand;
    List<YellowBlock> yblocos;
    List<BlueBlock> bblocos;
    List<GreenBlock> gblocos;
    
    public GamePane() 
    {       
        Platform.runLater(()->{iniciarControles();});
    }  
    
    public void adicionarBlocos()
    {        
        int k=30,l=5,bl=0,y=0,g=0,b;
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<7;j++)
            {
                b = rand.nextInt(3)+1;
                switch(b)
                {
                    case 1:
                        yblocos.add(new YellowBlock(k,l,100,30));
                        getChildren().add(yblocos.get(y++).getBlock());
                    break;
                    case 2:
                        gblocos.add(new GreenBlock(k,l,100,30,this));
                        getChildren().add(gblocos.get(g++).getBlock());
                    break;
                    case 3:
                        bblocos.add(new BlueBlock(k,l,100,30));
                        getChildren().add(bblocos.get(bl++).getBlock());
                    break;
                }
                k+=105;
            }
            k=30;
            l+=35;
        }
    }
    
    public void iniciarControles()
    {
        rand = new Random();
        yblocos = new ArrayList();
        gblocos = new ArrayList();
        bblocos = new ArrayList();
        //instanciar os objetos do jogo
        
        
        Ball bolinha = new Ball(300,300,10,this,dificuldade);
        Racket raquete = new Racket(350,660,100,15,this);
        
        AnimationTimer timer;
        adicionarBlocos();
        // adicionar os componentes no painel
        getChildren().addAll(bolinha.getImagem());
        getChildren().addAll(raquete.getRaquete());

        //processo cíclico
        //movendo as bolinhas e verificando colisão
        timer=new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                bolinha.mover();        
                if(bolinha.colidiu(raquete))
                {
                    bolinha.mudaBolinha(raquete.raquete.getX());
                }
                for (YellowBlock bloco: yblocos)
                {
                    if(bolinha.colidiu(bloco))
                    {
                        getChildren().remove(bloco.getBlock());
                        yblocos.remove(bloco);
                        bolinha.mudaBolinha(bloco.getPosX(),bloco.getPosY());
                        break;
                    }
                }
                for (BlueBlock bloco: bblocos)
                {
                    if(bolinha.colidiu(bloco))
                    {
                        if(!bloco.ativado())
                            bloco.acionaFuncao();
                        else
                        {
                            getChildren().remove(bloco.getBlock());
                            bblocos.remove(bloco);
                        }
                        bolinha.mudaBolinha(bloco.getPosX(),bloco.getPosY());
                        break;
                    }
                    if(bloco.ativado())
                    {
                        bloco.mover();
                    }
                    if(bloco.colidiu(raquete))
                    {
                        getChildren().remove(bloco.getBlock());
                        bblocos.remove(bloco);
                    }
                        
                }
                for (GreenBlock bloco: gblocos)
                {
                    if(bolinha.colidiu(bloco))
                    {
                        if(!bloco.ativado())
                            bloco.acionaFuncao();
                        else
                        {
                            getChildren().remove(bloco.getBlock());
                            gblocos.remove(bloco);
                        }
                        bolinha.mudaBolinha(bloco.getPosX(),bloco.getPosY());
                        break;
                    }
                    if(bloco.ativado())
                    {
                        if(bloco.getPosY()<180)
                            bloco.MoverY();
                        else
                            bloco.moverX();
                    }
                }
            }
        };
        timer.start();
        getScene().setOnKeyPressed(e->{raquete.mover(e.getCode().toString());});
        getScene().setOnMouseMoved(e->{raquete.moverM((int)e.getSceneX()-50);});
    }
}