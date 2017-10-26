
package types;

import delta.Deletion;
import delta.Insertion;
import delta.Unknown;
import delta.UnknownRest;
import delta.DeltaRec;
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
  {if(obj.typeOf().equals(this.typeOf()))
    { TypeRec that=(TypeRec)obj;
      if(this.typeBody.typeOf().isUNION()&&that.typeBody.typeOf().isUNION())
      { TypeUnion orig=(TypeUnion) this.typeBody;
        TypeUnion targ=(TypeUnion) that.typeBody;
        if(orig.isNil()||targ.isNil()) return orig.refine(targ);
        else//both of them are non-empty Rec TYPE
        { if(orig.getValue().typeOf().isPRODUCT()&&targ.getValue().typeOf().isPRODUCT())
          { TypeProduct insideOrig=(TypeProduct) orig.getValue();
            TypeProduct insideTarg=(TypeProduct) targ.getValue();
            
            ListOfLabelandTypeTs listOrig=insideOrig.getValues();
            ListOfLabelandTypeTs listTarg=insideTarg.getValues();
            
            DeltaRec path1, path2, path3;
            path1=new DeltaRec(new StepList(new Unknown(listOrig.head().getTypeT(), listTarg.head().getTypeT()), new StepList(new UnknownRest(listOrig.rest(), listTarg.rest()), new StepList())));  
            path2=new DeltaRec(new StepList(new Deletion(listOrig.head().getTypeT()), new StepList(new UnknownRest(listOrig.rest(), listTarg), new StepList())));
            path3=new DeltaRec(new StepList(new Insertion(listTarg.head().getTypeT()), new StepList(new UnknownRest(listOrig, listTarg.rest()), new StepList())));
            return new CandidatesList(path1,new CandidatesList()).insert_cand(path2).insert_cand(path3);
          }
          else{ throw new RuntimeException("The inside TYPE of this and that should be PRODUCT TYPE.");}
        }
      }
      else{ throw new RuntimeException("The unfolding TYPE of this and that should be UNION TYPE.");}
    }  
    else { throw new RuntimeException("This obj="+obj+" is not the same TypeRecursive");}
  }  
}
