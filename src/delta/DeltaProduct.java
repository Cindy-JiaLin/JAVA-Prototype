

package delta;

import similarity.Sim;
import solution.CandidatesList;
import utility.ListOfLabelandDeltas;

public class DeltaProduct extends Delta
{ private final ListOfLabelandDeltas listofLabelandDeltas;
  public DeltaProduct(ListOfLabelandDeltas listofLabelandDeltas){ this.listofLabelandDeltas=listofLabelandDeltas;}         
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("DeltaProduct:("); buf.append(this.listofLabelandDeltas);buf.append(")");
    return buf.toString();
  }     
  @Override
  public double weight(){ return this.listofLabelandDeltas.weight();}        

  @Override
  public Sim sim(){ return this.listofLabelandDeltas.sim();} 
  @Override
  public CandidatesList refine(){ return new CandidatesList(new DeltaProduct(this.listofLabelandDeltas.refine()),new CandidatesList());}        
}
