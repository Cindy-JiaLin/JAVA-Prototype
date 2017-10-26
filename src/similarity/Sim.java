
package similarity;

// Since Java has no built-in pair data structure, 
// this Sim class is used to represent the final similarity interval between 
// two objects.
// Actually, each similarity interval has a corresponding delta.
public class Sim 
{ private final double lwb, upb;
  
  public Sim(double lwb, double upb)
  { this.lwb=lwb; this.upb=upb;}  
  public double lwb(){ return this.lwb;}
  public double upb(){ return this.upb;}
  
  public boolean isComplete()
  { return (Math.abs(this.lwb-this.upb) < 1.0e-6);}
  
@Override
  public String toString()
  { long lwb_2_decimal=Math.round(this.lwb*100);
    double lwb=lwb_2_decimal/100;
    long upb_2_decimal=Math.round(this.upb*100);
    double upb=upb_2_decimal/100;
    StringBuilder buf=new StringBuilder();
    buf.append("["); buf.append(lwb); buf.append(","); buf.append(upb); buf.append("]");
    return buf.toString();   
  }        
    
}
