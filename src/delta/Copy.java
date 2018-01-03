
package delta;

import similarity.Sim;

import types.TypeT;

import solution.CandidatesList;

public class Copy extends Delta
{ private final TypeT t;
  public Copy(TypeT t){ this.t=t;}  
  @Override
  public String toString(){ return "cpy."+this.t.toString();} 
  @Override
  public double weight(){ return this.t.weight()*2;}      
  public double increase(){ return this.t.weight()*2;}
  public double decrease(){ return 0.0;}
  
  @Override
  public Sim sim(){ return new Sim(1.0,1.0);}       
  @Override
  public CandidatesList refine(){ return new CandidatesList(this,new CandidatesList());}  
}
