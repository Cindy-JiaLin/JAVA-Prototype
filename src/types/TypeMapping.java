
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
{ private final TYPE T;
  private final LabelandTypeT a;
  private final LabelandTypeT b;
  private final TypeMapping rest;
  public TypeMapping(TYPE T)
  { this.T=T; this.a=null; this.b=null; this.rest=null;}
  public TypeMapping(TYPE T, LabelandTypeT a, LabelandTypeT b, TypeMapping rest)
  { if(!a.getTypeT().typeOf().equals(T.getDOM())||
       !b.getTypeT().typeOf().equals(T.getCOD())||
       !rest.typeOf().equals(TYPE.MAPPING(T.getMembers()))
      )
    throw new RuntimeException("Elements TYPEs do not match the mapping TYPE");
    this.T=T; this.a=a; this.b=b; this.rest=rest;
  }   
       
  public static TypeMapping EMPTY_MAPPING(TYPE pairBaseTYPE){ return pairBaseTYPE.getEmptyMapping(); }
  public boolean isEmptyMapping(){ return this.a==null&&this.b==null&&this.rest==null;}
  
  @Override
  public TYPE typeOf(){ return TYPE.MAPPING(T.getMembers());}  
  public TypeMapping extend(TypeT pair)
  { if(!pair.typeOf().isPRODUCT()||
       !pair.typeOf().getMembers().equals(this.T.getMembers())||
       pair.typeOf().getMembers().size()!=2
      )
    { throw new RuntimeException(pair.typeOf()+" is not the expect pair type to added to this TypeMapping.");}
    else
    { TypeProduct newPair=(TypeProduct)pair;
      ListOfLabelandTypeTs newPairValues=newPair.getValues();
      LabelandTypeT x=newPairValues.head();
      LabelandTypeT y=newPairValues.rest().head();
      
      if(this.isEmptyMapping()){ return new TypeMapping(T, x, y, new TypeMapping(T));}
      else
      { if(x.equals(this.a)){ return this;}
        else{ return new TypeMapping(this.T, this.a, this.b, this.rest.extend(pair));}
      }
    }  
  }        
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeMapping)
    { TypeMapping that=(TypeMapping)obj;
      if(!this.T.equals(that.T))
        { throw new RuntimeException("They have different pairBaseTYPE, cannot be compared.");}
      else
      { if(!this.isEmptyMapping()&&!that.isEmptyMapping())
        { return this.a.equals(that.a)&&this.b.equals(that.b)&&this.rest.equals(that.rest);}
        else {return this.isEmptyMapping()&&that.isEmptyMapping();}//if both empty return true; else return false.
      }    
    }  
    else { throw new RuntimeException("This obj="+obj+" is not of TypeList");}
  }         
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    buf.append("[");
    if(!isEmptyMapping())dump(buf);
    buf.append("]"); 
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append("("); buf.append(this.a); buf.append(","); buf.append(this.b); buf.append(")");
    if(!rest.isEmptyMapping()){ buf.append(","); this.rest.dump(buf);}  
  }   
  //This method is used to convert a TypeMapping to a TypeSet
  public TypeSet map_to_set()
  { LabelandTYPE aLabelandTYPE=new LabelandTYPE(a.getLabel(),this.T.getDOM());
    LabelandTYPE bLabelandTYPE=new LabelandTYPE(b.getLabel(),this.T.getCOD());
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
