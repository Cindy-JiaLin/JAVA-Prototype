
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
  
  //When the difference |lwb-upb| is small enough, these two double value are considered identical
  public boolean isComplete()
  { return (Math.abs(this.lwb-this.upb) < 1.0e-6);}
  
  //When this.upb<that.lwb the old candidate will be pruned
  //The new candidate will be inserted into the rest candidates list
  //See this method used in insert_cand method in CandidatesList class
  public boolean pruneOld(Sim that){ return this.upb<that.lwb;}  
  //When that.upb<this.lwb the new candidate will be pruned
  //See this method used in insert_cand method in CandidatesList class
  public boolean pruneNew(Sim that){ return that.upb<this.lwb;}
  //When this.lwb==that.lwb
  public boolean equalLwb(Sim that){ return (Math.abs(this.lwb-that.lwb) < 1.0e-6);}
  //When this.upb>=that.upb
  //The old candidate's upb greater or equal to the new candidate's upb
  //This should be used in the condition of equalLwb.
  //This the old candidate will be kept and the new one will be inserted into the rest candidates list.
  //Otherwise when this.upb<that.upb, also under the equalLwb condition
  //The new candidate will be added to be the first one the new candidates list.
  public boolean higherUpb(Sim that){ return this.upb>=that.upb;}
  //When this.lwb>that.lwb
  //The old candidate will be kept and the new one will be inserted into the rest candidates list
  //Otherwise this.lwb<that.lwb this new candidate will be chosen as the first one.
  public boolean higherLwb(Sim that){ return this.lwb>that.lwb;}
  
  
@Override
  public String toString()
  { double lwb_2_decimal=Math.round(this.lwb*100);
    double lwb=lwb_2_decimal/100;
    double upb_2_decimal=Math.round(this.upb*100);
    double upb=upb_2_decimal/100;
    StringBuilder buf=new StringBuilder();
    buf.append("["); buf.append(lwb); buf.append(","); buf.append(upb); buf.append("]");
    return buf.toString();   
  }        
    
}
