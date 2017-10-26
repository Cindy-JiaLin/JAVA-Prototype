
package delta;

import similarity.Sim;

import types.TypeT;

import solution.CandidatesList;

public class Id extends Delta
{ private final TypeT t;
  public Id(TypeT t){ this.t=t;}  
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("cpy."); buf.append(this.t);
    return buf.toString();      
  }        
  @Override
  public double weight(){ return this.t.weight()*2;}        
  
  @Override
  public Sim sim(){ return new Sim(1.0,1.0);}       
  @Override
  public CandidatesList refine(){ return new CandidatesList(this,new CandidatesList());}  
}
