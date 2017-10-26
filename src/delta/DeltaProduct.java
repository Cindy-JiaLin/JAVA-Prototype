

package delta;

import utility.ListOfDeltas;
import similarity.Sim;
import solution.CandidatesList;

public class DeltaProduct extends Delta
{ private final ListOfDeltas listofDeltas;
  public DeltaProduct(ListOfDeltas listofDeltas){ this.listofDeltas=listofDeltas;}         
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("DeltaProduct:("); buf.append(this.listofDeltas);buf.append(")");
    return buf.toString();
  }     
  @Override
  public double weight(){ return this.listofDeltas.weight();}        

  @Override
  public Sim sim(){ return this.listofDeltas.sim();} 
  @Override
  public CandidatesList refine(){ return new CandidatesList(new DeltaProduct(this.listofDeltas.refine()),new CandidatesList());}        
}
