
package Logic;

import javafx.scene.paint.Color;

public abstract class Ekran {
  public static final YonluHareket BOY = new YonluHareket(500, 500);
  public static final YonluHareket ORTA = BOY.coklu(0.5f);
  public static final float GENISLIK = BOY.x;
  public static final float YUKSEKLIK = BOY.y;
  public static Color renk;
  
  public Ekran(){ 
      renk = Color.BLACK;
  }
  
  public boolean sinirKontrol(OyunObject oyunObject){
      return solSinir(oyunObject) && sagSinir(oyunObject);
  }
  
  public boolean sinirKontrol(Oynat oynat){
      return (solSinir(oynat) && oynat.surat.x < 0) || (sagSinir(oynat) && oynat.surat.x >0);
  }
  
   private boolean solSinir(OyunObject oyunObject) {
    return oyunObject.pozisyon.x > 0;
  }
  
  private boolean sagSinir(OyunObject oyunObject) {
    return oyunObject.pozisyon.x < Oyun.GENISLIK - oyunObject.olcek.x;
  }
  
}
