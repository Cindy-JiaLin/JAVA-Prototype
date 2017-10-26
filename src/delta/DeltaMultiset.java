
package delta;

import similarity.Sim;
import solution.CandidatesList;
import solution.ListOfStepList;
import solution.StepList;

public class DeltaMultiset extends Delta
{ private final StepList stepList;
  public DeltaMultiset(StepList stepList){ this.stepList=stepList;}      
  public StepList getStepList(){ return this.stepList;}        
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append(this.sim()); buf.append("\nDeltaMultiset: "); buf.append(this.stepList);
    return buf.toString();
  }  
  @Override
  public double weight(){ return this.stepList.weight();}       
  @Override
  public Sim sim(){ return this.stepList.sim();} 
  @Override
  public CandidatesList refine(){ return wrap_DeltaMultiset(this.stepList.refine_StepList());}    
  
  //This wrap_DeltaMultiset is used to title each stepList by DeltaMultiset in the listoflist.
  private CandidatesList wrap_DeltaMultiset(ListOfStepList listofStepList)
  { if(!listofStepList.isEmptyListOfStepList())
    { return wrap_DeltaMultiset(listofStepList.rest()).insert_cand(new DeltaMultiset(listofStepList.head()));}
    else{ return new CandidatesList();}
  } 
    
}
