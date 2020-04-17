
package Logic;

import javafx.scene.paint.Color;

public class Oyun extends Ekran{
    public final UzayGemisi uzayGemisi;
    public final DusmanGrubu dusmanlar;
    
    public Oyun(){
        renk = Color.BLACK;
        uzayGemisi = new UzayGemisi();
        dusmanlar = new DusmanGrubu();
        dusmanlar.uzayGemisi=uzayGemisi;
        
    }
    
    public Dusman[][] getDusmanlar(){
        return dusmanlar.getList();
    }
    
    public boolean uzayGemisiVurus(){
      
        for(Oynat [] satir: dusmanlar.getDusmanKarolari()){
            for(Oynat karo :satir){
                if(uzayGemisi.Carpisma(karo)){
                    karo.pozisyon =YonluHareket.sifir;
                    return true;
                }
            }
        }
        return false;
    }
    
    
}
