package arkanoid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class Game extends Pane{
    
    private int dificuldade, vidas;     
    private Random rand;
    private List<YellowBlock> yblocos;
    private List<BlueBlock> bblocos;
    private List<GreenBlock> gblocos;
    private boolean mouse,pause,terminou;
    public static boolean ganhou,perdeu;
    private Score contador;
    private List<String> dificuldades;
    private Alert periferico,conf; 
    private Image coracao;
    private ImageView v1,v2,v3,v4,v5;
    private MediaPlayer som1,som2,som3;
    private Media som,somg,somp;
    private Text pontos,tp1,tp2;
    private AnimationTimer timer;
    private Rectangle rp;
    
    public Game() 
    {           
        ganhou = perdeu = pause = terminou = false;
        vidas = 5;
        rand = new Random();
        yblocos = new ArrayList();
        gblocos = new ArrayList();
        bblocos = new ArrayList();
        som = new Media(new File("music.mp3").toURI().toString());
        som1 = new MediaPlayer(som);
        rp = new Rectangle(300,300,200,200);
        rp.setArcHeight(15);
        rp.setArcWidth(15);
        rp.setFill(Paint.valueOf("0xFFFF"));
        rp.setStroke(Paint.valueOf("0x000000"));
        Platform.runLater(()->{iniciarControles();});
        this.setStyle("-fx-background-image: url('star.gif')");
        
    }  
    
    public void ganhou() {
        Alert wl = new Alert(AlertType.INFORMATION);
        wl.setTitle("YOU WIN");
        wl.setHeaderText("YEAH");
        wl.setContentText("Você ganhou, deseja jogar novamente");
        wl.getButtonTypes().clear();
        wl.getButtonTypes().add(ButtonType.YES);
        wl.getButtonTypes().add(ButtonType.NO);
        if(wl.showAndWait().get() == ButtonType.NO)
        {
            terminou = true;
            Stage stage = (Stage)getScene().getWindow();
            stage.close();
        }
    }
    
    public void perdeu() {
        Alert wl = new Alert(AlertType.INFORMATION);
        wl.setTitle("YOU LOOSE!");
        wl.setHeaderText("YOU LOOSE");
        wl.setContentText("Você perdeu, deseja jogar novamente");
        wl.getButtonTypes().clear();
        wl.getButtonTypes().add(ButtonType.YES);
        wl.getButtonTypes().add(ButtonType.NO);
        if(wl.showAndWait().get() == ButtonType.NO)
        {
            terminou = true;
            Stage stage = (Stage)getScene().getWindow();
            stage.close();
        }
    }
    
    public void win()
    {
        Stage stage = new Stage();
        Game root = new Game();
        root.ganhou = true;
        
        Scene scene = new Scene(root, 800, 680);
       
        stage.setTitle("ArkanoiD");
        stage.setScene(scene);
        stage.show();
    }
    
    public void lose()
    {
        Stage stage = new Stage();
        Game root = new Game();
        root.perdeu = true;
        
        Scene scene = new Scene(root, 800, 680);
       
        stage.setTitle("ArkanoiD");
        stage.setScene(scene);
        stage.show();
    }
    
    public void finalizar()
    {
        som1.stop();
        timer.stop();
        Stage stage = (Stage)getScene().getWindow();
        stage.close();
        if(ganhou)
            win();
        else
            lose();
        
    }
    
    public void perdeVida()
    {
        vidas--;
        switch(vidas)
        {
            case 0:
                getChildren().remove(v5);
            break;
            case 1:
                getChildren().remove(v4);
            break;
            case 2:
                getChildren().remove(v3);
            break;
            case 3:
                getChildren().remove(v2);
            break;
            case 4:
                getChildren().remove(v1);
            break;
            
        }
    }
    
    public void pausar(String p, AnimationTimer timer)
    {
        if(p=="ESCAPE")
        {
            if(pause)
            {
                getChildren().remove(tp2);
                getChildren().remove(tp1);
                getChildren().remove(rp);
                som1.play();
                pause = false;
            }
            else
            {
                getChildren().add(rp);
                getChildren().add(tp1);
                getChildren().add(tp2);
                pause = true;
                som1.pause();
            } 
        }
        else if(pause&&p=="ENTER")
        {
            perdeu = true;
            finalizar();
        }
    }
    
    public void escolherDificuldade()
    {
        dificuldades = new ArrayList<>();
        dificuldades.add("fácil");
        dificuldades.add("normal");
        dificuldades.add("difícil");
        dificuldades.add("impossível");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("escolha", dificuldades);
        dialog.setTitle("Dificuldade");
        dialog.setHeaderText("Dificuldade");
        dialog.setContentText("Escolha a dificuldade:");

        Optional<String> resposta = dialog.showAndWait();
        if (resposta.isPresent()){
            switch(resposta.toString())
            {
                case "Optional[fácil]":
                    dificuldade = 4;
                break;
                case "Optional[normal]":
                    dificuldade = 7;
                break;
                case "Optional[difícil]":
                    dificuldade = 12;
                break;
                case "Optional[impossível]":
                    dificuldade = 20;
                break;
                default:
                    conf= new Alert(AlertType.INFORMATION);
                    conf.setTitle("Alerta!");
                    conf.setContentText("Como não foi escolhida nenhuma "
                        + "dificuldade por padrão irá jogar na "
                        + "dificuldade (normal) ");
                    
                    conf.showAndWait();
                    dificuldade = 7;   
            }
        }
        else
        {
            conf= new Alert(AlertType.INFORMATION);
            conf.setTitle("Alerta!");
            conf.setContentText("Como não foi escolhida nenhuma "
                    + "dificuldade por padrão irá jogar na "
                    + "dificuldade (normal) ");

            conf.showAndWait();
            dificuldade = 7;
        }
    }
    
    public void adicionarCoracoes()
    {
        coracao = new Image("coracao.png",30,30,true,false);
        v1 = new ImageView(coracao);
        v1.setY(20);
        v1.setX(600);
        v2 = new ImageView(coracao);
        v2.setY(20);
        v2.setX(632);
        v3 = new ImageView(coracao);
        v3.setY(20);
        v3.setX(664);
        v4 = new ImageView(coracao);
        v4.setY(20);
        v4.setX(698);
        v5 = new ImageView(coracao);
        v5.setY(20);
        v5.setX(732);
        getChildren().add(v1);
        getChildren().add(v2);
        getChildren().add(v3);
        getChildren().add(v4);
        getChildren().add(v5);
    }
    
    public void escolherPeriferico()
    {
        periferico = new Alert(AlertType.CONFIRMATION);
        periferico.setTitle("Periférico");
        periferico.setHeaderText("Periférico");
        periferico.setContentText("Escolha o periférico deseja jogar:");

        ButtonType buttonTypeOne = new ButtonType("Mouse");
        ButtonType buttonTypeTwo = new ButtonType("Teclado");

        periferico.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> resposta = periferico.showAndWait();
        if(resposta.get() == buttonTypeOne)
            mouse = true;
        else if(resposta.get() == buttonTypeTwo) 
            mouse = false;
    }
    
    public void adicionarBlocos()
    {        
        int k=30,l=65,bl=0,y=0,g=0,b;
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<7;j++)
            {
                b = rand.nextInt(3)+1;
                switch(b)
                {
                    case 1:
                        yblocos.add(new YellowBlock(k,l,100,30,this));
                        getChildren().add(yblocos.get(y++).getBlock());
                    break;
                    case 2:
                        gblocos.add(new GreenBlock(k,l,100,30,this));
                        getChildren().add(gblocos.get(g++).getBlock());
                    break;
                    case 3:
                        bblocos.add(new BlueBlock(k,l,100,30,this));
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
        if(ganhou)
            ganhou();
        else if(perdeu)
            perdeu();
        perdeu = ganhou = false;
        if(!terminou)
            escolherDificuldade();
        //instanciar os objetos do jogo
        contador = new Score();
        contador.zerar();
        Ball bolinha = new Ball(400,600,10,this,dificuldade);
        Racket raquete = new Racket(350,660,100,15,this);
        
        adicionarBlocos();
        tp1 = new Text(340, 340, "PAUSED");
        tp1.setStyle("-fx-font: 30 arial;");
        tp2 = new Text(330, 400, "[ESC] Continue\n\n[ENTER] Exit");
        tp2.setStyle("-fx-font: 20 arial;");
        pontos = new Text(50, 43, "Pontos: " + contador.getPontos());
        pontos.setStyle("-fx-font: 30 arial;");
        // adicionar os componentes no painel
        getChildren().add(contador.getRetangulo());
        getChildren().addAll(bolinha.getImagem());
        getChildren().addAll(raquete.getRaquete());
        getChildren().add(pontos);
        adicionarCoracoes();
        if(!terminou)
            escolherPeriferico();
        
        som1.volumeProperty().set(0.07);
        som1.play();
        
        
        //processo cíclico
        //movendo as bolinhas e verificando colisão
        timer=new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                if(!pause)
                {
                    bolinha.mover();
                    if(!mouse)
                        raquete.moverT2();
                    if (ganhou||perdeu)
                    {
                        this.stop();
                        finalizar();
                    }
                    if(bolinha.perdeu())
                    {
                        contador.subtrair(15);
                        bolinha.reposicionar(350,600);
                        perdeVida();
                    }
                    if(vidas == 0)
                        perdeu = true;
                    else if(yblocos.isEmpty()&&bblocos.isEmpty()&&gblocos.isEmpty())
                        ganhou = true;
                    if(bolinha.colidiu(raquete))
                    {
                        bolinha.mudaBolinha(raquete.raquete.getX());
                    }
                    for (YellowBlock bloco: yblocos)
                    {
                        if(bolinha.colidiu(bloco))
                        {
                            contador.somar(bloco.getPontos());
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
                                contador.somar(bloco.getPontos(bolinha));
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
                            contador.somar(bloco.getPontos(raquete));
                            getChildren().remove(bloco.getBlock());
                            bblocos.remove(bloco);
                        }else if(bloco.getBlock().getY()>=getHeight())
                        {
                            contador.subtrair(30);
                            getChildren().remove(bloco.getBlock());
                            bblocos.remove(bloco);
                            perdeVida();
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
                                contador.somar(bloco.getPontos());
                                getChildren().remove(bloco.getBlock());
                                gblocos.remove(bloco);
                            }
                            bolinha.mudaBolinha(bloco.getPosX(),bloco.getPosY());
                            break;
                        }
                        if(bloco.ativado())
                        {
                            if(bloco.getPosY()<240)
                                bloco.MoverY();
                            else
                                bloco.moverX();
                        }
                    }
                    pontos.setText("Pontos: " + contador.getPontos());
                }
            }
                    
        };
        timer.start();
        if(mouse)
            getScene().setOnMouseMoved(e->{raquete.moverM((int)e.getSceneX()-50);});
        else
            getScene().setOnKeyPressed(e->{raquete.moverT1(e.getCode().toString());});
        getScene().setOnKeyReleased(e->{pausar(e.getCode().toString(), timer);});
    }
}