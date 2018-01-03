
package types;

import delta.Delta;
import delta.Unknown;
import solution.CandidatesList;
import typeSystem.TYPE;

//There is no value represented by null
public abstract class TypeT 
{ public abstract TYPE typeOf();
  public abstract double weight();
  public abstract CandidatesList refine(TypeT obj);
  
  //Main method to get the difference between orig and targ
  public static Delta delta(TypeT orig, TypeT targ)
  { //double whole_weight=orig.weight()+targ.weight();
    return new CandidatesList(new Unknown(orig, targ),new CandidatesList()).solutions().fstDelta();
  } 
}
