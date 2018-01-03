

package delta;

import similarity.Sim;
import solution.CandidatesList;
import utility.ListOfLabelandDeltas;

public class DeltaProduct extends Delta
{ private final ListOfLabelandDeltas listofLabelandDeltas;
  public DeltaProduct(ListOfLabelandDeltas listofLabelandDeltas){ this.listofLabelandDeltas=listofLabelandDeltas;}         
  @Override
  public String toString(){ return "DeltaProduct:("+this.listofLabelandDeltas.toString()+")";}     
  @Override
  public double weight(){ return this.listofLabelandDeltas.weight();}  
  public double increase(){ return this.listofLabelandDeltas.increase();}
  public double decrease(){ return this.listofLabelandDeltas.decrease();}

  @Override
  public Sim sim(){ return this.listofLabelandDeltas.sim();} 
  @Override
  public CandidatesList refine(){ return new CandidatesList(new DeltaProduct(this.listofLabelandDeltas.refine()),new CandidatesList());}        
}
