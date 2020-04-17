package Logic;
import Logic.Oynat;
import Logic.YonluHareket;
import javafx.scene.paint.Color;


public abstract class OyunParcalari extends Oynat {
    protected Oynat karo;
    
    public OyunParcalari(){
        pozisyon = YonluHareket.sifir;
        olcek = YonluHareket.sifir;
        surat = YonluHareket.sifir;
        renk = Color.WHITE;
        hiz = 5;
        karo = new Oynat();
        
    }
    
    //  mermi atma
    
    public abstract void ates(YonluHareket yon);
    public abstract boolean vurusKontrol();
    
    public Oynat getKaro(){
        return karo;
    }
    
    
}
