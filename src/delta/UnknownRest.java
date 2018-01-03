
package delta;
import types.TypeT;
import similarity.Sim;
import solution.CandidatesList;

//This UnknownRest is used to store the tail list, set, multiset or mappings
//Hence, the orig and targ never be null.
public class UnknownRest extends Delta
{ private final TypeT orig, targ;
  public UnknownRest(TypeT orig, TypeT targ){ this.orig=orig; this.targ=targ;}
  
  @Override
  public String toString()
  { if(this.orig.weight()==0&&this.targ.weight()==0) return "";
    else return "UnknownRest("+this.orig.toString()+","+this.targ.toString()+")";
  }   
  @Override
  public double weight(){ return this.orig.weight()+this.targ.weight();}  
  public double increase(){ return 0.0;}
  public double decrease(){ return 0.0;}
  
  @Override
  public Sim sim(){ return new Sim(0.0,1.0);} 
  
  @Override
  public CandidatesList refine(){ return this.orig.refine(this.targ);}        
}
