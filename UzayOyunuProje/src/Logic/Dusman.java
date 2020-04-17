package Logic;

import Logic.Oynat;
import Logic.Oyun;
import Logic.YonluHareket;
import javafx.scene.paint.Color;

public class Dusman extends OyunParcalari {
    
    private int deger;
    private int boyut=35;
    
    public Dusman (){
      
        this.olcek= new YonluHareket(boyut,boyut-10);
        this.pozisyon = YonluHareket.sifir;
        this.surat = YonluHareket.sag;
        renk = Color.AQUA;
        deger =1;
        
    }
    
    public String toString (){
        return "Düşman @ " + this.pozisyon;
    }
    
    public void yonDegis() {
      surat = surat.coklu(-1);
     }
    
      //ölme
     public void olme(){
       olcek = YonluHareket.sifir;  
     }

    @Override
    public boolean vurusKontrol() {
       return olcek == YonluHareket.sifir; 
    }
  
    @Override
    public void ates(YonluHareket yon) {
        if(!vurusKontrol() && (karo.bosKontrol() || karo.pozisyon.y < 0 || karo.pozisyon.y > Oyun.YUKSEKLIK)){
           Oynat karoCogalt = new Oynat();
           karoCogalt.renk = this.renk;
           karoCogalt.olcek = new YonluHareket(2.5f, 2.5f);
           karoCogalt.pozisyon = new YonluHareket(pozisyon.x + (olcek.x / 2) - (karoCogalt.olcek.x / 2), pozisyon.y + yon.y * 25);           
           karoCogalt.surat = yon.coklu(5);
           this.karo=karoCogalt;
        }
    }

    public int getDeger() {
        return deger;
    }

    public void setDeger(int deger) {
        this.deger = deger;
    }

    public int getBoyut() {
        return boyut;
    }
    
    public void yenidenOlus(){
        olcek = new YonluHareket(boyut,boyut -10);
        
    }
    
    
}
