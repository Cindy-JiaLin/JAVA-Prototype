

package delta;

import similarity.Sim;
import solution.CandidatesList;
import solution.ListOfStepList;
import solution.StepList;

public class DeltaList extends Delta
{ private final StepList stepList;
  public DeltaList(StepList stepList){ this.stepList=stepList;}      
  public StepList getStepList(){ return this.stepList;}        
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append(this.sim()); buf.append(", DeltaList: "); buf.append(this.stepList);
    return buf.toString();
  }  
  @Override
  public double weight(){ return this.stepList.weight();}       
  
  @Override
  public Sim sim(){ return this.stepList.sim();} 
  @Override
  public CandidatesList refine(){ return wrap_DeltaList(this.stepList.refine_StepList());}    
  
  //This wrap_DeltaList is used to title each stepList by DeltaList in the listoflist.
  private CandidatesList wrap_DeltaList(ListOfStepList listofStepList)
  { if(!listofStepList.isEmptyListOfStepList())
    { return wrap_DeltaList(listofStepList.rest()).insert_cand(new DeltaList(listofStepList.head()));}
    else{ return new CandidatesList();}
  } 
}
