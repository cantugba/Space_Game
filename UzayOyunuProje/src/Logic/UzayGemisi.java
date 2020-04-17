
package Logic;

import Logic.OyunParcalari;
import Logic.Oynat;
import Logic.Oyun;
import Logic.YonluHareket;
import javafx.scene.paint.Color;


public class UzayGemisi extends OyunParcalari {
   /* javafx.scene.paint.Color fx = 
    java.awt.Color awtColor = new java.awt.Color((float) fx.getLightCyan());*/
                                             
    private int skor; //olmasada olur
    private int lifePoints; 
    
    public UzayGemisi(){
        this.olcek = new YonluHareket(50,50);
        this.pozisyon = new YonluHareket(Oyun.ORTA.x - (this.olcek.x/2),Oyun.YUKSEKLIK -olcek.y);
        this.renk = Color.LIGHTCYAN;
        this.skor =0;
        this.lifePoints = 2;
    }
    
    @Override
    public void ates(YonluHareket yon) {
        if(karo.bosKontrol() || karo.pozisyon.y < 0 || karo.pozisyon.y > Oyun.YUKSEKLIK){
            Oynat karoCogalt = new Oynat();
            karoCogalt.renk = this.renk;
            karoCogalt.olcek = new YonluHareket(2.5f, 10);
            karoCogalt.pozisyon = new YonluHareket(pozisyon.x + (olcek.x / 2) - (karoCogalt.olcek.x ), pozisyon.y + yon.y * 25);           
            karoCogalt.surat = yon.coklu(10);
            this.karo=karoCogalt;
        }
    }
    
    @Override
    public boolean vurusKontrol() {
        return lifePoints <= 0;
    }
    
    public void skorEkle(int skor) {
    this.skor += skor;
  }
  
  public int getSkor() {
    return skor;
  }
  
  public void lifePointAzaltma() {
    if(lifePoints > 0) {
      lifePoints -= 1;
    }
  }
  
  public void lifePointEkle() {
    lifePoints += 1;
  }
  
  public int getLifePoints() {
    return lifePoints;
  }
    
}
