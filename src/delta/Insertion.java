
package delta;

import similarity.Sim;

import types.TypeT;

import solution.CandidatesList;

public class Insertion extends Delta
{ private final TypeT t;
  public Insertion(TypeT t){ this.t=t;}        
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("ins."); buf.append(this.t);
    return buf.toString();      
  }    
  @Override
  public double weight(){ return this.t.weight();}        
    
  @Override
  public Sim sim(){ return new Sim(0.0,0.0);}       
  @Override
  public CandidatesList refine(){ return new CandidatesList(this,new CandidatesList());}  
}
