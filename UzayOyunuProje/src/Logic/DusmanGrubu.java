package Logic;
import javafx.scene.paint.Color;
import java.util.Random;

public class DusmanGrubu {
    private Dusman[][] dusmanlar;
    UzayGemisi uzayGemisi;
    
    public DusmanGrubu(){
        dusmanlar = new Dusman[2][11]; //5e 11lik matris karo
        for (int i = 0; i < dusmanlar.length; i++) {
            for (int j = 0; j < dusmanlar[i].length; j++) {
                dusmanlar[i][j] = new Dusman();
                float bosluk =5; //dusmanlar arası bosluk
                float dusmanX = j*dusmanlar[i][j].olcek.x + bosluk*j;
                float dusmanY = i*dusmanlar[i][j].olcek.y + bosluk*i;
                
                dusmanlar[i][j].pozisyon = new YonluHareket(dusmanX + dusmanlar[i][j].olcek.x,dusmanY + 50 );
                 dusmanlar[i][j].setDeger((dusmanlar.length - i) * 1000);
            }
        }
        
    }
    
    public void dusmanOynat(){
        for(Dusman [] dusmanSatir : dusmanlar){
            for (Dusman dusman : dusmanSatir) {
                dusman.hareket();
            }
        }
    }
    
    public void dusmanYonDegis(){
        for(Dusman[] dusmanSatir: dusmanlar){
            for(Dusman dusman : dusmanSatir){
                dusman.yonDegis();
            }
        }
    }
     //en alt satırdaki kutucuk vurulmadan ustteki ölemez bu yuzden satir satir ilerliyoruz
    public boolean dusmaniVur(Oynat karo){
        boolean vur = false;
        for(Dusman[] satir : dusmanlar){
            for(Dusman dusman : satir){
                if(dusman.Carpisma(karo)){
                    vur =true;
                    dusman.olme();
                    uzayGemisi.skorEkle(dusman.getDeger());
                    break;
                }
            }
        }
        return vur;
    }
    
    public Oynat [][] getDusmanKarolari(){
        Oynat [][] dusmanKarolari= new Oynat[dusmanlar.length][dusmanlar[0].length];
        for(int i = 0; i < dusmanlar.length; i++) {
        for(int j = 0; j < dusmanlar[j].length; j++) {
        dusmanKarolari[i][j] = dusmanlar[i][j].getKaro();
      }
    }
    return dusmanKarolari;
  }
     
    public void dusmanKarolariDondur(Oyun oyun) {
      for(Oynat[] row : getDusmanKarolari()) {
       for(Oynat dusmanKarolari : row) {
        if(!oyun.sinirKontrol(dusmanKarolari)) {
          dusmanKarolari.hareket();
        } else {
          dusmanKarolari.dondur();
        }
       }
      }
    }
     //sıradaki kutucuklar
    public Dusman getRandomAltDusman(){
        Random random = new Random();
        int sonrakiSatir = random.nextInt(dusmanlar.length);
        int sonrakiSutun = random.nextInt(dusmanlar[0].length);
        
        while(!altDusman(dusmanlar[sonrakiSatir][sonrakiSutun])){
            sonrakiSatir= random.nextInt(dusmanlar.length);
            sonrakiSutun =random.nextInt(dusmanlar[0].length);
        }
        
        return dusmanlar[sonrakiSatir][sonrakiSutun];
    }

    private boolean altDusman(Dusman dusman) {
        boolean altDusman = false;
        for (int i = 0; i <dusmanlar[0].length; i++) {
            if(dusmanlar.equals(getAltDusman(i))){
            } else {
                altDusman=true;
            }
        }
        return altDusman;
    }
    
    public Dusman getAltDusman(int index){
        Dusman sıradakiDusman;
        int count=1;
        do{
            sıradakiDusman = sutun(index)[sutun(index).length-count];
            count ++;
        }while(sıradakiDusman.olcek == YonluHareket.sifir && count <= dusmanlar.length);
        return sıradakiDusman;
    }
    
    public void altDusmanVurgu(){
        for (int i = 0; i <dusmanlar.length; i++) {
            for (int j = 0; j < dusmanlar[i].length; j++) {
                if(dusmanlar[i][j].equals(getAltDusman(j))){
                    dusmanlar[i][j].renk = Color.RED;
                }
            }
        }
    }
    
    public Dusman[][] getList() {
      return dusmanlar;
    }
    
    public Dusman[] satir(int index){
        Dusman[] satir = new Dusman[dusmanlar[0].length];
        for (int i = 0; i < satir.length; i++) {
            satir[i] = dusmanlar[index][i];
        }
        return satir;
    }
    public Dusman[] sutun(int index){
        Dusman[] sutun = new Dusman[dusmanlar.length];
        for (int i = 0; i <sutun.length; i++) {
            sutun[i] = dusmanlar[i][index];
        }
        return sutun;
    }
    
    public void satirVurgu(int index){
        for(Dusman dusman : satir(index)){
            dusman.highlight(Color.RED);
        }
    }
    public void sutunVurgu(int index){
        for(Dusman dusman : sutun(index)){
            dusman.highlight(Color.RED);
        }
    }
    public Dusman[] sonSutun(){
        Dusman[] sutun;
        int count =1;
        do{
            sutun =sutun(dusmanlar[0].length - count);
            count++;
        }while(bosSutunKontrol(dusmanlar[0].length - count) && count < dusmanlar[0].length);
        
        return sutun;
    }
    
     public boolean bosSutunKontrol(int index) {
     for(Dusman dusman : sutun(index)) {
      if(!dusman.vurusKontrol()) {
        return false;
       }
      }
      return true;
    }
    public void sonSutunVurgu() {
      for(Dusman dusman : sonSutun()) {
        dusman.renk = Color.RED;
      }
    }
    
    public void yenidenOlus() {
     for(Dusman[] satir : dusmanlar) {
       for(Dusman dusman : satir) {
        dusman.yenidenOlus();
       }
     }
    }
    
    //bos mu?
    public boolean bos(){
       for(Dusman [] satir: dusmanlar){
           for(Dusman dusman : satir){
               if(!dusman.vurusKontrol()){
                   return false;
               }
           }
       }
       return true;
    }

   
   
   
}

