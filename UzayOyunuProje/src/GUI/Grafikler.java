package GUI;

import Logic.Dusman;
import Logic.Oyun;
import Logic.OyunObject;
import Logic.UzayGemisi;
import com.sun.istack.internal.logging.Logger;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;

public class Grafikler extends JPanel{
    //private BufferedImage resim;
   //public UzayGemisi uzayGemisi;
    public Grafikler(){
    /*try{
        resim =ImageIO.read(new FileImageInputStream(new File("gemi.png")));
    } catch(IOException ex){
        Logger.getLogger(Grafikler.class).log(Level.SEVERE,null,ex);
    }*/
    
    }
    
    public static void ortamCiz(Oyun ortam, GraphicsContext ctx) {
       arkaPlan(ortam,ctx);
       skorCiz(ortam.uzayGemisi,ctx);
       lifePointsCiz(ortam.uzayGemisi,ctx);
       uzayGemisiCiz(ortam.uzayGemisi,ctx);
       
       if(!ortam.uzayGemisi.getKaro().bosKontrol()){
           oyunObjectCiz(ortam.uzayGemisi.getKaro(),ctx);
       }
       
       for(Dusman[] dusman : ortam.getDusmanlar()){
           for(Dusman dusman2 : dusman){
               dusmanCiz(dusman2,ctx);
               oyunObjectCiz(dusman2.getKaro(),ctx);
           }
       }
       
       
    }

    public static void arkaPlan(Oyun ortam, GraphicsContext ctx) {
    ctx.setFill(Oyun.renk);
    ctx.fillRect(0, 0, Oyun.GENISLIK,Oyun.YUKSEKLIK);
    }

    public static void oyunObjectCiz(OyunObject oyunObject, GraphicsContext ctx) {
      ctx.setFill(oyunObject.renk);
      ctx.fillRect(oyunObject.pozisyon.x, oyunObject.pozisyon.y, oyunObject.olcek.x,oyunObject.olcek.y);
    }

    public static void uzayGemisiCiz(UzayGemisi uzayGemisi,/*Graphics g*/GraphicsContext ctx) {
     //g.drawImage(resim,uzayGemisi, 490, resim.getWidth()/10, resim.getHeight()/10,this);
     ctx.setFill(uzayGemisi.renk);
    
    // Body
    float bodyScaleX = uzayGemisi.olcek.x;
    float bodyScaleY = (uzayGemisi.olcek.y / 2) - 10;
    float bodyPosX = uzayGemisi.pozisyon.x;
    float bodyPosY = uzayGemisi.pozisyon.y;
    ctx.fillRect(bodyPosX+5, bodyPosY-5, bodyScaleX-10, bodyScaleY-5);
    ctx.fillRect(bodyPosX, bodyPosY, bodyScaleX, bodyScaleY);
    
    // Cannon
    float cannonWidth = 2.5f;
    float cannonLength = 15;
    float cannonPosX = uzayGemisi.pozisyon.x + (uzayGemisi.olcek.x / 2) - (cannonWidth / 2);
    float cannonPosY = uzayGemisi.pozisyon.y - cannonLength;
    ctx.fillRect(cannonPosX - 3, cannonPosY + 3, (cannonWidth + 6), cannonLength);
    ctx.fillRect(cannonPosX, cannonPosY, cannonWidth, cannonLength);
    
    }

    public static void dusmanCiz(Dusman  dusman, GraphicsContext ctx) {
      ctx.setFill(dusman.renk);
      ctx.fillRect(dusman.pozisyon.x, dusman.pozisyon.y, dusman.olcek.x, dusman.olcek.x);
    }

    public static void skorCiz(UzayGemisi  uzayGemisi, GraphicsContext ctx) {
    ctx.setFill(Color.GREENYELLOW);
    ctx.fillText("Skor: " + uzayGemisi.getSkor(), 10, Oyun.YUKSEKLIK - 10);
    }

    public static void lifePointsCiz(UzayGemisi uzayGemisi,GraphicsContext ctx) {
    ctx.setFill(Color.BLUE);
    ctx.fillText("Lives: " + uzayGemisi.getLifePoints(), Oyun.GENISLIK - 57.5f, Oyun.YUKSEKLIK - 10);
        
    }

    public static void pauseCiz(GraphicsContext ctx) {
    ctx.setFill(Color.AQUAMARINE);
    ctx.fillText("Durduruldu ",Oyun.GENISLIK/2-25,Oyun.YUKSEKLIK/2);
    }

    public static void tekrarCiz(GraphicsContext ctx) {
    ctx.setFill(Color.AQUAMARINE);
    ctx.fillText("Tekrar oynamak için ENTER a basın.",Oyun.GENISLIK / 2-55,Oyun.YUKSEKLIK /2);
    }
    
}
