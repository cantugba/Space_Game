package Business;

import GUI.Grafikler;
import Logic.Oynat;
import Logic.Oyun;
import Logic.Dusman;
import Logic.UzayGemisi;
import Logic.YonluHareket;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
    private Oyun ortam;
    
    private GraphicsContext ctx;
    private AnimationTimer anaDongu;
    
    private static boolean donguKos = true;
  
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        // Değişkenleri başlat

        StackPane root = new StackPane(); //Bu layout a elemanlar yığında olduğu gibi önce eklenen eleman yığının en altına yerleştirilir ve sonraki elemanlar bunun üstüne eklemeli şekilde yerleştirilir ve sonuç olarak tek bir yığına yerleştirilmiş olur.
        Canvas canvas = new Canvas(Logic.Oyun.GENISLIK,Logic.Oyun.YUKSEKLIK);
        ctx=canvas.getGraphicsContext2D();
        ortam =  new Oyun();
        
        // Değişkenleri yapılandırma
        canvasConfig(canvas);
        stageConfig(stage);
        //setup
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
        //dongu
        anaDongu =new AnimationTimer(){
            
           @Override
            public void handle(long now) {
                
                if(donguKos){
                    
                    //uzayGemisi ölme
                    if(ortam.uzayGemisi.vurusKontrol()){
                        

                        ortam.uzayGemisi.renk = Color.RED;
                        ortam.uzayGemisi.dondur();
                    }
                    //uzaygemisi konum 
                    if(ortam.sinirKontrol(ortam.uzayGemisi) && !ortam.uzayGemisi.bosKontrol()){
                       ortam.uzayGemisi.hareket();
                    }
                    
                     // Update projectile position
                     if(!ortam.uzayGemisi.getKaro().bosKontrol()) {
                     (new Thread(new KaroThread())).start();
                     }
                     
                     // Update invaders' projectiles' positions
                    (new Thread(Main.this.new DusmanKaroThread())).start();
                    
                     // Update invaders' positions
                    (new Thread(Main.this.new DusmanHareketThread())).start();
                    
                     // Invaders shoot
                    (new Thread(Main.this.new DusmanAtesThread())).start();
                    
                        // Respawn invaders when all are dead
                    if(Main.this.ortam.dusmanlar.bos()){
                        Main.this.ortam.dusmanlar.yenidenOlus();
                    }
                    
                    //OYUN CİZİMİ
                    Grafikler.ortamCiz(Main.this.ortam, Main.this.ctx);
                    
                        // Draw retry upon spaceship death
                    if(Main.this.ortam.uzayGemisi.vurusKontrol()){
                        Grafikler.tekrarCiz(Main.this.ctx);
                    }
                }
            }
        };
        this.anaDongu.start();
    }

    public void restart() {
       ortam = new Oyun();
    }
    
     private void stageConfig(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Uzay İstilası");
         stage.setOnCloseRequest(e -> System.exit(0));
    }
    
     
    private void canvasConfig(Canvas canvas) {
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(new Main.KeyPressHandler());
        canvas.setOnKeyReleased(new Main.KeyRelaseHandler());
    }
    
    private class KeyRelaseHandler implements EventHandler<KeyEvent>{
      
        @Override
        public void handle(KeyEvent e) {
           atesTopu(ortam.uzayGemisi, e);
        }
        
        public void atesTopu(UzayGemisi ates,KeyEvent e){
            switch(e.getCode()){
                case A:
                case D:
                case LEFT:
                case RIGHT:
                    ates.surat = YonluHareket.sifir;
                    break;
                default:  
                    break;
            }
        }
    }
    private class KeyPressHandler implements EventHandler<KeyEvent>{
     
        @Override
        public void handle(KeyEvent e) {
            atesTopu(ortam.uzayGemisi,e);
            handleReset(e); //olmasaada olurmuş
        }
        
        public void atesTopu(UzayGemisi uzayGemisi,KeyEvent e){
            switch(e.getCode()){
                case A:
                case LEFT:
                    uzayGemisi.surat=YonluHareket.sol.coklu(uzayGemisi.getAnlikHız());
                    break;
                case D:
                case RIGHT:
                    uzayGemisi.surat =YonluHareket.sag.coklu(uzayGemisi.getAnlikHız());
                    break;
                case SPACE:
                    if(!uzayGemisi.vurusKontrol()){
                        uzayGemisi.ates(YonluHareket.yukari);
                        
                    }
                    break;
                case L:
                    ortam.dusmanlar.getRandomAltDusman().ates(YonluHareket.asagi);
                    break;
                case R:
                    ortam.dusmanlar.yenidenOlus();
                    break;
                default:
                    break;
                   
            }
        }
        
        public void handleReset(KeyEvent e){
            switch(e.getCode()){
                case ENTER:
                    restart();
                    break;
                case ESCAPE:
                    if(donguKos){
                        Grafikler.pauseCiz(ctx);
                        anaDongu.stop();
                        donguKos = false;
                    } else{
                        anaDongu.start();
                        donguKos = true;
                    }
                    break;
                default:
                    break;
            }
        }
     
    }
    
    public class KaroThread implements Runnable{
        public KaroThread(){}

        @Override
        public void run() {
            Oynat karo =ortam.uzayGemisi.getKaro();
            if(!ortam.dusmanlar.dusmaniVur(karo) && !ortam.sinirKontrol(karo) ){
                karo.hareket();
                
            }else{
                karo.dondur();
            }
         
        }
        
    }
    
    public class DusmanKaroThread implements Runnable{
        public DusmanKaroThread(){}
        
        @Override
        public void run() {
           ortam.dusmanlar.dusmanKarolariDondur(ortam);
           if(ortam.uzayGemisiVurus()){
               ortam.uzayGemisi.lifePointAzaltma();
               ortam.uzayGemisi.renk =Color.RED;
               
               try{
                   Thread.sleep(1000);
                   
               } catch(InterruptedException exc){
                   exc.printStackTrace();
               }
           }
            
           
         ortam.uzayGemisi.renk =  Color.CORAL;
                 

        }
        
    }
    
    public class DusmanHareketThread implements Runnable{
        public DusmanHareketThread(){ }

        @Override
        public void run() {
           Dusman[] satir= ortam.getDusmanlar()[0];
           if(ortam.sinirKontrol(satir[satir.length -1]) && ortam.sinirKontrol(satir[0])){
               ortam.dusmanlar.dusmanOynat();
           } else{
               ortam.dusmanlar.dusmanYonDegis();
           }
        }
    
    }
    
    public class DusmanAtesThread implements Runnable{
        private long gecenSure = System.nanoTime();
        public DusmanAtesThread(){
            
        }
        @Override
        public void run() {
            long sure =System.nanoTime();
            long dusmanAtesSuresi =2700000;
            if(sure -gecenSure > dusmanAtesSuresi){
               ortam.dusmanlar.getRandomAltDusman().ates(YonluHareket.asagi);
               gecenSure = sure;
            }
        }
        
    }
}