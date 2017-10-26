
package delta;

import similarity.Sim;
import solution.CandidatesList;
import types.TypeList;
import types.TypeMultiset;
import types.TypeSet;
import types.TypeT;

//This UnknownRest is used to store the tail list, set, multiset or mappings
//Hence, the orig and targ never be null.
public class UnknownRest extends Delta
{ private final TypeT orig, targ;
  public UnknownRest(TypeT orig, TypeT targ){ this.orig=orig; this.targ=targ;}
  
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("UnknownRest: "); buf.append(this.orig);buf.append(","); buf.append(this.targ);
    return buf.toString();
  }   
  @Override
  public double weight(){ return this.orig.weight()+this.targ.weight();}        
  
  @Override
  public Sim sim()
  { if(this.orig.typeOf().isSET()&&this.targ.typeOf().isSET())
    { TypeSet origSet=(TypeSet)this.orig;
      TypeSet targSet=(TypeSet)this.targ;
      if (origSet.isEmptySet()&&targSet.isEmptySet()) return new Sim(1.0,1.0);
      else return new Sim(0.0, 1.0);
    }  
    else if(this.orig.typeOf().isMSET()&&this.targ.typeOf().isMSET())
    { TypeMultiset origMSet=(TypeMultiset)this.orig;
      TypeMultiset targMSet=(TypeMultiset)this.targ;
      if (origMSet.isEmptyMultiset()&&targMSet.isEmptyMultiset()) return new Sim(1.0,1.0);
      else return new Sim(0.0, 1.0);
    }    
    else if(this.orig.typeOf().isLIST()&&this.targ.typeOf().isLIST())
    { TypeList origList=(TypeList)this.orig;
      TypeList targList=(TypeList)this.targ;
      if(origList.isEmptyList()&&targList.isEmptyList()) return new Sim(1.0,1.0);
      else return new Sim(0.0, 1.0);   
    } 
    else return new Sim(0.0,1.0);
  } 
  @Override
  public CandidatesList refine(){ return this.orig.refine(this.targ);}        
}
