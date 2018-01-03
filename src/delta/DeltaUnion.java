
package delta;

import similarity.Sim;
import solution.CandidatesList;

public class DeltaUnion extends Delta
{ private final String label;
  private final Delta delta;
  public DeltaUnion(String label, Delta delta){ this.label=label; this.delta=delta;} 
  @Override
  public String toString(){ return this.label+"."+this.delta.toString();}    
  @Override
  public double weight(){ return this.delta.weight();}
  public double increase(){ return this.delta.increase();}
  public double decrease(){ return this.delta.decrease();}
 
  @Override
  public Sim sim(){ return this.delta.sim();}
  @Override
  public CandidatesList refine()
  { return new CandidatesList(new DeltaUnion(this.label, this.delta.refine().solutions().fstDelta()),new CandidatesList());}   
}
