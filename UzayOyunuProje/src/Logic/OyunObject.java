
package Logic;

import javafx.scene.paint.Color;



public class OyunObject {
    public Color renk;
    public YonluHareket pozisyon,olcek;
    
    public OyunObject(){
        renk = Color.WHITE; //değiştir
        pozisyon =YonluHareket.sifir;
        olcek=YonluHareket.sifir;
        
    }
    
    public OyunObject(float x,float y,float z,float t){
       renk = Color.WHITE;
       pozisyon = new YonluHareket(x,y);
       olcek = new YonluHareket(z,t);
    }
    
    
    
// true eğer diğer oyun nesnesiyle çarpışıyorsa geçerli
    
    public boolean Carpisma(OyunObject diger){
      boolean ustKontrol,altKontrol,solKontrol,sagKontrol;
      ustKontrol = pozisyon.y -(olcek.y / 4 ) <= diger.pozisyon.y;
      altKontrol  = pozisyon.y + olcek.y >= diger.pozisyon.y;
      solKontrol = pozisyon.x <= diger.pozisyon.x;
      sagKontrol = pozisyon.x + olcek.x >= diger.pozisyon.x;
      
      
      return ustKontrol && altKontrol && solKontrol && sagKontrol;
    }
    
    public void highlight(Color renk ){
        this.renk = renk;
    }
}
