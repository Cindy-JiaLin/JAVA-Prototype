
package delta;

import similarity.Sim;
import solution.CandidatesList;
import types.TypeT;

public class Deletion extends Delta
{ private final TypeT t;
  public Deletion(TypeT t){ this.t=t;}        
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("del."); buf.append(this.t);
    return buf.toString();      
  }    
  @Override
  public double weight(){ return this.t.weight();}              
  @Override
  public Sim sim(){ return new Sim(0.0,0.0);}       
  @Override
  public CandidatesList refine(){ return new CandidatesList(this,new CandidatesList());}  
}
