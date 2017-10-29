
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
  { StringBuilder buf=new StringBuilder();
    buf.append("UnknownRest: "); buf.append(this.orig);buf.append(","); buf.append(this.targ);
    return buf.toString();
  }   
  @Override
  public double weight(){ return this.orig.weight()+this.targ.weight();}        
  
  @Override
  public Sim sim(){ return new Sim(0.0,1.0);} 
  
  @Override
  public CandidatesList refine(){ return this.orig.refine(this.targ);}        
}
