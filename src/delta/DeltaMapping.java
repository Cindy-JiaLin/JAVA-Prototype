
package delta;

import similarity.Sim;
import solution.CandidatesList;
import solution.ListOfStepList;
import solution.StepList;

public class DeltaMapping extends Delta
{ private final StepList stepList;
  public DeltaMapping(StepList stepList){ this.stepList=stepList;}      
  public StepList getStepList(){ return this.stepList;}        
  @Override
  public String toString(){ return "DeltaMapping: "+this.stepList.toString();}  
  @Override
  public double weight(){ return this.stepList.weight();}   
  public double increase(){ return this.stepList.increase();}
  public double decrease(){ return this.stepList.decrease();}
  @Override
  public Sim sim(){ return this.stepList.sim();} 
  @Override
  public CandidatesList refine(){ return wrap_DeltaMapping(this.stepList.refine_StepList());}    
  
  //This wrap_DeltaSet is used to title each stepList by DeltaMapping in the listoflist.
  private CandidatesList wrap_DeltaMapping(ListOfStepList listofStepList)
  { if(!listofStepList.isEmptyListOfStepList())
    { return wrap_DeltaMapping(listofStepList.rest()).insert_cand(new DeltaMapping(listofStepList.head()));}
    else{ return new CandidatesList();}
  } 
    
}
