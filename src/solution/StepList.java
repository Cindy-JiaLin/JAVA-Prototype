
package solution;

import delta.Delta;
import delta.DeltaList;
import delta.DeltaMultiset;
import delta.DeltaSet;
import delta.UnknownRest;
import similarity.Sim;

//This StepList is a list of Delta
//It is used to represent the difference between TypeLists, TypeSets, TypeMSets and TypeMappings
public class StepList
{ private final Delta step;
  private final StepList rest;
  //Either this.step==null&&this.rest==null (Empty StepList)
  //Or this.step!=null && this.rest!=null
  public StepList(){ this.step=null; this.rest=null;}//Constructor for empty stepList.
  public StepList(Delta step, StepList rest){ this.step=step; this.rest=rest;}//Constructor for non-empty stepList.        
  
  public Delta head()
  { if(this.step!=null&&this.rest!=null){ return this.step;}
    else{ throw new RuntimeException("Empty stepList has no head element.");}
  }       
  public StepList rest()
  { if(this.step!=null&&this.rest!=null){ return this.rest;}
    else if(this.step==null&&this.rest==null) { return new StepList();}
    else{ throw new RuntimeException("Illegal constructor usage in StepList rest() method.");}
  }        
  public boolean isEmptyStepList(){ return this.step==null&&this.rest==null;}
  
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    buf.append("[");
    if(!isEmptyStepList()) dump(buf);
    buf.append("]");
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.step);
    if (!rest.isEmptyStepList()){ buf.append(", "); this.rest.dump(buf);}
  }  
  
  
  public double weight()
  { if(!this.isEmptyStepList()){ return this.step.weight()+this.rest.weight();}
    else if(this.isEmptyStepList()){ return 0.0;}
    else { throw new RuntimeException("Illegal constructor usage in StepList weight() method.");}
  }   
  /*Here should be a parameter whole_weight
   *
  */
  public Sim sim()
  { if(!this.isEmptyStepList()) 
    { double lwb=((this.step.weight()*(this.step.sim().lwb())+this.rest.weight()*(this.rest.sim().lwb()))/(this.step.weight()+this.rest.weight()));
      double upb=((this.step.weight()*(this.step.sim().upb())+this.rest.weight()*(this.rest.sim().upb()))/(this.step.weight()+this.rest.weight()));
      return new Sim(lwb, upb);
    }
    else if(this.isEmptyStepList()){ return new Sim(0.0,0.0);}
    else { throw new RuntimeException("Illegal constructor usage in StepList sim() method.");}
  }        
  //insert a Delta in front of this list of delta      
  public StepList insert(Delta d){ return new StepList(d,this);}
 
  //A stepList is the constructor of DeltaList, DeltaSet, DeltaMSet and DeltaMapping
  //Hence, when refine these objects, the inside stepLists need to be refined.
  //This method is used to refine the inside stepList in each Delta object.
  public ListOfStepList refine_StepList()
  { if(!this.isEmptyStepList())
    { if(!this.step.sim().isComplete())
      { if(!this.rest.isEmptyStepList())
        { return this.rest.combine_StepList(this.step.refine());}
        else
        { if(!(this.step instanceof UnknownRest))
          { return this.rest.combine_StepList(this.step.refine());}  
          else{ return release(this.step.refine());}  
        }
      }
      else{ return extend_StepList(this.step, this.rest.refine_StepList());}
    }
    else if(this.isEmptyStepList()){ return new ListOfStepList();}  
    else{ throw new RuntimeException("Illegal constructor usage in StepList refine_StepList() method.");}
  }
  // The initial idea of creating this method is to deal with the DeltaList.refine()
  // Since the DeltaList.refine() method is used to refine the StepList step by step
  // If the first delta (step) in this step list is a Deletion or an Insertion, the lwb==upb, this delta will be 
  // kept as the first step. However, the second delta (step) may contains more path.
  // What we need to do is add this first step to each of path refined by the second delta.
  // Hence, this extend_StepList method is created to connect this delta to each stepList in a list of StepList.
  // And it will be used in the refine_StepList() method, under the condition that step.lwb==step.upb 
  public ListOfStepList extend_StepList(Delta delta, ListOfStepList listoflist)
  { if (!listoflist.isEmptyListOfStepList()){ return extend_StepList(delta, listoflist.rest()).insert(listoflist.head().insert(delta));}  
    else{ return new ListOfStepList();} 
  }
  // This combine_StepList method is used in the refine_StepList() method.
  // When the first delta (step) can be refined, lwb!=upb, the cands is the result of refining from this delta.
  // Each cand in this cands will be connected to the rest stepList to build a list of stepList
  // Hence, this method is used to insert EACH element in cands to a specific stepList to build a new stepList
  // use these new stepLists to build a ListOfStepList
  public ListOfStepList combine_StepList(CandidatesList cands)
  { if(!cands.isEmptyCandidatesList())
    { return combine_StepList(cands.restCands()).insert(new StepList(cands.fstDelta(),this));} 
    else{ return new ListOfStepList();}
  }
  
  //When the CandidatesList is a list of DeltaList, DeltaSet
  //This method is used to return list of StepList
  //In other words, this method is used to dismiss the outside structure of DeltaList or DeltaSet,
  //in order to get a list of inside stepList.
  public ListOfStepList release(CandidatesList cands)
  { if(!cands.isEmptyCandidatesList())
    { if (cands.fstDelta() instanceof DeltaList)
      { DeltaList d=(DeltaList)cands.fstDelta();
        return release(cands.restCands()).insert(d.getStepList());
      }
      else if(cands.fstDelta() instanceof DeltaSet)
      { DeltaSet d=(DeltaSet)cands.fstDelta();
        return release(cands.restCands()).insert(d.getStepList());
      }    
      else if(cands.fstDelta() instanceof DeltaMultiset)
      { DeltaMultiset d=(DeltaMultiset)cands.fstDelta();
        return release(cands.restCands()).insert(d.getStepList());
      }
      else{ throw new RuntimeException("This release method will apply to the DeltaList, DeltaSet, DeltaMultiset.\n"+cands);}  
    }
    else{ return new ListOfStepList();}    
  } 
}
