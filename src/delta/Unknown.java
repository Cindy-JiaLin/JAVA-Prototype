
package delta;
import types.TypeT;
import similarity.Sim;
import solution.CandidatesList;
import types.TypeList;
import types.TypeMultiset;
import types.TypeProduct;
import types.TypeRec;
import types.TypeSet;
import types.TypeUnion;
import utility.ListOfLabelandTypeTs;

//Since in TypeT there is no value represented by null
//The orig and targ in Unknown are never be null.
public class Unknown extends Delta
{ private final TypeT orig, targ;
  public Unknown(TypeT orig, TypeT targ){ this.orig=orig; this.targ=targ;}
  public TypeT orig(){ return this.orig;}
  public TypeT targ(){ return this.targ;}
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("Unknown: "); buf.append(this.orig); buf.append(","); buf.append(this.targ);
    return buf.toString();
  }   
  @Override
  public double weight(){ return this.orig.weight()+this.targ.weight();}        

  // The initial similarity interval of uknown is [0,1].
  @Override
  public Sim sim(){ return new Sim(0.0,1.0);} 
  @Override
  public CandidatesList refine()
  { if(this.orig.typeOf().equals(this.targ.typeOf()))
    { // Compare two empty lists  
      if(this.orig.typeOf().isLIST()&&this.targ.typeOf().isLIST())
      { TypeList list1=(TypeList) this.orig;
        TypeList list2=(TypeList) this.targ;
        if(list1.isEmptyList()&&list2.isEmptyList())
        { return new CandidatesList(new Id(list1), new CandidatesList());}
        else return list1.refine(list2);
      } 
      // Compare two empty sets
      else if(this.orig.typeOf().isSET()&&this.targ.typeOf().isSET())
      { TypeSet set1=(TypeSet) this.orig;
        TypeSet set2=(TypeSet) this.targ;
        if(set1.isEmptySet()&&set2.isEmptySet())
        { return new CandidatesList(new Id(set1), new CandidatesList());}
        else return set1.refine(set2);
      }
      // Compare two empty multisets
      else if(this.orig.typeOf().isMSET()&&this.targ.typeOf().isMSET())
      { TypeMultiset mset1=(TypeMultiset) this.orig;
        TypeMultiset mset2=(TypeMultiset) this.targ;
        if(mset1.isEmptyMultiset()&&mset2.isEmptyMultiset())
        { return new CandidatesList(new Id(mset1), new CandidatesList());}
        else return mset1.refine(mset2);
      } 
      else if(this.orig.typeOf().isREC() && this.targ.typeOf().isREC())
      { TypeRec rec1 =(TypeRec) this.orig;
        TypeRec rec2 =(TypeRec) this.targ;
        if(rec1.getBody().typeOf().isUNION() && rec2.getBody().typeOf().isUNION())
        { TypeUnion insideUnion1 = (TypeUnion) rec1.getBody();
          TypeUnion insideUnion2 = (TypeUnion) rec2.getBody();
          if(insideUnion1.isNil()&&insideUnion2.isNil())
          { return new CandidatesList(new Id(rec1), new CandidatesList());}
          else return rec1.refine(rec2);
        }    
        else throw new RuntimeException("The type body of recursive type is only union currently.");
      }   
      else return this.orig.refine(this.targ);
    }
    else 
    { throw new RuntimeException("orig="+this.orig+" and targ="+this.targ+" are values of different TYPE cannot be compared");}
  }
}
