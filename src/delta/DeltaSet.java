
package delta;

import similarity.Sim;
import solution.CandidatesList;
import solution.ListOfStepList;
import solution.StepList;

public class DeltaSet extends Delta
{ private final StepList stepList;
  public DeltaSet(StepList stepList){ this.stepList=stepList;}      
  public StepList getStepList(){ return this.stepList;}        
  @Override
  public String toString(){ return "DeltaSet: "+this.stepList.toString();}  
  @Override
  public double weight(){ return this.stepList.weight();}    
  public double increase(){ return this.stepList.increase();}
  public double decrease(){ return this.stepList.decrease();}
  
  @Override
  public Sim sim(){ return this.stepList.sim();} 
  @Override
  public CandidatesList refine(){ return wrap_DeltaSet(this.stepList.refine_StepList());}    
  
  //This wrap_DeltaSet is used to title each stepList by DeltaSet in the listoflist.
  private CandidatesList wrap_DeltaSet(ListOfStepList listofStepList)
  { if(!listofStepList.isEmptyListOfStepList())
    { return wrap_DeltaSet(listofStepList.rest()).insert_cand(new DeltaSet(listofStepList.head()));}
    else{ return new CandidatesList();}
  } 
         
}
