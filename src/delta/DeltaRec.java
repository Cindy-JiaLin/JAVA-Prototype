
package delta;

import similarity.Sim;
import solution.CandidatesList;
import solution.ListOfStepList;
import solution.StepList;

public class DeltaRec extends Delta
{ private final StepList stepList;
  public DeltaRec(StepList stepList){ this.stepList=stepList;}      
  public StepList getStepList(){ return this.stepList;}        
  @Override
  public String toString(){ return "DeltaRec: "+this.stepList.toString();}  
  @Override
  public double weight(){ return this.stepList.weight();}       
  public double increase(){ return this.stepList.increase();}
  public double decrease(){ return this.stepList.decrease();}
  
  @Override
  public Sim sim(){ return this.stepList.sim();} 
  @Override
  public CandidatesList refine(){ return wrap_DeltaRec(this.stepList.refine_StepList());}    
  
  //This wrap_DeltaRec is used to title each stepList by DeltaList in the listoflist.
  private CandidatesList wrap_DeltaRec(ListOfStepList listofStepList)
  { if(!listofStepList.isEmptyListOfStepList())
    { return wrap_DeltaRec(listofStepList.rest()).insert_cand(new DeltaRec(listofStepList.head()));}
    else{ return new CandidatesList();}
  } 
    
}
