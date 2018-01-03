
package types;

import delta.Deletion;
import delta.Insertion;
import delta.Unknown;
import delta.UnknownRest;
import delta.DeltaRec;
import delta.Id;
import solution.CandidatesList;
import solution.StepList;
import typeSystem.TYPE;
import utility.ListOfLabelandTypeTs;

public class TypeRec extends TypeT
{ private final TYPE T;
  private final TypeT typeBody;
  public TypeRec(TYPE T, TypeT typeBody)
  {//In the constructor of TypeRec, the TYPE of typeBody has already been UNION of PRODUCT TYPE.
   //This case has been considered in the equals method in TYPE class.
    if(!T.isREC()||!T.equals(typeBody.typeOf()))
    { throw new RuntimeException("This recursive type does not match its TYPE="+T);}
    this.T=T; this.typeBody=typeBody;
  }
  @Override
  public TYPE typeOf() { return this.T;}
  public TypeT getBody(){ return this.typeBody;}
  
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeRec)
    { TypeRec that=(TypeRec)obj;
      return this.T.equals(that.T)&&this.typeBody.equals(that.typeBody);
    }
    else { throw new RuntimeException("This obj="+obj+" is not instance of TypeRecursive.");}
  }          
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder(); buf.append(this.typeBody); return buf.toString();}          
 
  @Override
  public double weight() { return this.typeBody.weight();}
 @Override
  public CandidatesList refine(TypeT obj) 
  { throw new UnsupportedOperationException("Not supported yet, this refine will be implemented in Unknown refine"); 
  } 
}
