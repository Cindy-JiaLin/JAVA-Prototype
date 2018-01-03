
package delta;

import similarity.Sim;
import solution.CandidatesList;
import types.TypeReal;
import types.TypeT;


public class Change extends Delta
{ private final TypeT orig, targ;
  public Change(TypeT orig, TypeT targ){ this.orig=orig; this.targ=targ;}        
  @Override
  public String toString(){ return "Chg.("+this.orig.toString()+","+this.targ.toString()+")";}    
  @Override
  public double weight(){ return this.orig.weight()+this.targ.weight();} 
  public double increase()
  { if(orig.typeOf().isREAL()&&targ.typeOf().isREAL()&&orig.typeOf().equals(targ.typeOf()))
    { TypeReal r1=(TypeReal) orig;
      TypeReal r2=(TypeReal) targ;
      double acc=orig.typeOf().getAcc();
 
      if(Math.abs(r1.getValue()-r2.getValue())<acc)
      { return 2*(1-(Math.abs(r1.getValue()-r2.getValue())/acc));}
      else return 0.0;
    }
    else return 0.0;
  }
  public double decrease(){ return (weight()-increase());}
  
  @Override
  public Sim sim()
  { if(orig.typeOf().isREAL()&&targ.typeOf().isREAL()&&orig.typeOf().equals(targ.typeOf()))
    { TypeReal r1=(TypeReal) orig;
      TypeReal r2=(TypeReal) targ;
      double acc=orig.typeOf().getAcc();
      double v=1-(Math.abs(r1.getValue()-r2.getValue())/acc);
      if(Math.abs(r1.getValue()-r2.getValue())<acc){ return new Sim(v,v);}
      else{ return new Sim(0.0, 0.0);}
    }//Consider about real numbers
    else{ return new Sim(0.0,0.0);}//Except real numbers, the similarity interval of Change of other primitive type is [0.0, 0.0]
  }        
  @Override
  //If seen Change as a type of difference between two values of PRIMITIVE TYPE
  //This refine() method should never be called.
  public CandidatesList refine(){ return new CandidatesList(this,new CandidatesList());}        
}
