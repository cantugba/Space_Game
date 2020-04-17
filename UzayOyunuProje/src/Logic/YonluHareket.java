
package Logic;

public class YonluHareket {
    public static final YonluHareket sifir = new YonluHareket(0, 0); //yön (0,0)
    public static final YonluHareket yukari = new YonluHareket(0, -1); //yön (0,-1)
    public static final YonluHareket asagi = new YonluHareket(0, 1); //yön (0,1)
    public static final YonluHareket sol = new YonluHareket(-1, 0); //yön (-1,0)
    public static final YonluHareket sag = new YonluHareket(1, 0); //yön (1,0)
    
    public float x,y;
    
    public YonluHareket(float x, float y){
        this.x = x;
        this.y= y;
        
    }
    
    public YonluHareket ekle(YonluHareket diger){
        
        return new YonluHareket(x + diger.x,y + diger.y);
        
    }
    
    public String toString(){
        
        return "<" + x + ", " + y + ">";
        
    }
    
    public YonluHareket coklu(float n){
    
        return new YonluHareket (x*n,y*n);
    
    }
    
    
    
    
}
