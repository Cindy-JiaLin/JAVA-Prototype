
package types;

import delta.DeltaMapping;
import delta.DeltaSet;
import solution.CandidatesList;
import typeSystem.TYPE;
import utility.LabelandTYPE;
import utility.LabelandTypeT;
import utility.ListOfLabelandTYPEs;
import utility.ListOfLabelandTypeTs;

public class TypeMapping extends TypeT
{ private final TYPE pairBaseTYPE;
  private final LabelandTypeT a;
  private final LabelandTypeT b;
  private final TypeMapping rest;
  public TypeMapping(TYPE pairBaseTYPE)
  { this.pairBaseTYPE=pairBaseTYPE; this.a=null; this.b=null; this.rest=null;}
  public TypeMapping(TYPE pairBaseTYPE, LabelandTypeT a, LabelandTypeT b, TypeMapping rest)
  { if(!a.getTypeT().typeOf().equals(pairBaseTYPE.getDOM())||
       !b.getTypeT().typeOf().equals(pairBaseTYPE.getCOD())||
       !rest.typeOf().equals(TYPE.MAPPING(pairBaseTYPE.getLabel1(), pairBaseTYPE.getLabel2(), pairBaseTYPE.getDOM(), pairBaseTYPE.getCOD()))
      )
    throw new RuntimeException("Elements TYPEs do not match the mapping TYPE");
    this.pairBaseTYPE=pairBaseTYPE; this.a=a; this.b=b; this.rest=rest;
  }   
  
  public static TypeMapping EMPTY_MAPPING(TYPE pairBaseTYPE){ return pairBaseTYPE.getEmptyMapping(); }
  public boolean isEmptyMapping(){ return this.a==null&&this.b==null&&this.rest==null;}
  
  @Override
  public TYPE typeOf(){ return TYPE.MAPPING(pairBaseTYPE.getLabel1(), pairBaseTYPE.getLabel2(),this.pairBaseTYPE.getDOM(),this.pairBaseTYPE.getCOD());}  

  //This method is used to convert a TypeMapping to a TypeSet
  public TypeSet map_to_set()
  { LabelandTYPE aLabelandTYPE=new LabelandTYPE(a.getLabel(),this.pairBaseTYPE.getDOM());
    LabelandTYPE bLabelandTYPE=new LabelandTYPE(b.getLabel(),this.pairBaseTYPE.getCOD());
    ListOfLabelandTYPEs pairLabelandTYPEs=new ListOfLabelandTYPEs(aLabelandTYPE, new ListOfLabelandTYPEs(bLabelandTYPE, new ListOfLabelandTYPEs()));
    
    ListOfLabelandTypeTs pairLabelandTypeTs=new ListOfLabelandTypeTs(a,new ListOfLabelandTypeTs(b,new ListOfLabelandTypeTs()));
    if(this.isEmptyMapping()) return new TypeSet(TYPE.PRODUCT(pairLabelandTYPEs));
    else
    { return new TypeSet(TYPE.PRODUCT(pairLabelandTYPEs), new TypeProduct(TYPE.PRODUCT(pairLabelandTYPEs),pairLabelandTypeTs), this.rest.map_to_set());} 
  }        
  
  @Override
  public double weight() { return this.map_to_set().weight(); }

  @Override
  public CandidatesList refine(TypeT obj) 
  { if(obj.typeOf().equals(this.typeOf()))
    { TypeMapping that=(TypeMapping)obj;
      return setDelta_to_mapDelta(this.map_to_set().refine(that.map_to_set()));
    }
  else { throw new RuntimeException(obj+" is NOT of TypeMapping.");}      
  }
  //This setDelta_to_mapDelta method is used to change the name of list of setDelta to mapDelta
  private CandidatesList setDelta_to_mapDelta(CandidatesList setDeltaList)
  { if(setDeltaList.isEmptyCandidatesList()) return new CandidatesList();
    else
    { if(setDeltaList.fstDelta() instanceof DeltaSet)
      { DeltaSet setDelta=(DeltaSet)setDeltaList.fstDelta();
        return new CandidatesList(new DeltaMapping(setDelta.getStepList()), setDelta_to_mapDelta(setDeltaList.restCands()));
      }  
      else throw new RuntimeException("Check error: each Delta in the setDeltaList must be DeltaSet.");
    }
  }           
}
