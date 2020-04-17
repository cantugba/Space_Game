
package Logic;

public class Oynat extends OyunObject{
    public YonluHareket surat;
    protected float hiz;
    
    public Oynat(){
        surat = YonluHareket.sifir;
        hiz =10;
    }
    
    public float getAnlikHız(){
        return hiz;
        
    }
    
    public void hareket(){
        pozisyon = pozisyon.ekle(surat);
    }
    //bosta mı kontrol
    public boolean bosKontrol(){
    return surat == YonluHareket.sifir;
    }
    //  0,0 
    public void dondur(){
        surat = YonluHareket.sifir;
    }
}
