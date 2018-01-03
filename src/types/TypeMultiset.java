
package types;

import delta.Deletion;
import delta.Insertion;
import delta.Unknown;
import delta.UnknownRest;
import delta.DeltaMultiset;
import solution.CandidatesList;
import solution.StepList;
import typeSystem.TYPE;
import utility.ListOfDeltas;

/**
 *
 * @author Cinderella
 */
public class TypeMultiset extends TypeT
{ private final TYPE baseType;
  private final TypeT head;
  private final TypeMultiset rest;
  // Constructor for empty Multiset
  public TypeMultiset(TYPE baseType){ this.baseType=baseType; this.head=null; this.rest=null;}
  // Constructor for non-empty Multiset
  public TypeMultiset(TYPE baseType, TypeT head, TypeMultiset rest)
  { if(!head.typeOf().equals(baseType)||!rest.baseType.equals(baseType)) throw new RuntimeException("head type doesn't match the baseType of this Multiset");
    this.baseType=baseType; this.head=head; this.rest=rest;
  }

  public static TypeMultiset EMPTY_MSET(TYPE baseType){ return baseType.getEmptyMultiset(); }
  
  public boolean isEmptyMultiset(){ return this.head==null&&this.rest==null;}
  
  @Override
  public TYPE typeOf(){ return TYPE.MSET(this.baseType);}  
  public TypeT getFst()
  { if(!this.isEmptyMultiset()){ return this.head;}
    else{ throw new RuntimeException("This is an empty multiset, no head to be returned.");}
  }
  public TypeMultiset getRest()
  { if(!this.isEmptyMultiset()) return this.rest;
    else return EMPTY_MSET(this.baseType);  
  }
  // add an element in this multiset
  public TypeMultiset put(TypeT a)
  { if(!a.typeOf().equals(this.baseType))
    { throw new RuntimeException("The a.typeOf()="+a.typeOf()+" is different from"+this.baseType+" cannot be added to such a multiset.");}
    else{ return new TypeMultiset(this.baseType, a, this);}
  } 
  // append an element at the end of this multiset
  public TypeMultiset append(TypeT a)
  { if(!a.typeOf().equals(this.baseType))
    { throw new RuntimeException("The a.typeOf()="+a.typeOf()+" is different from"+this.baseType+" cannot be appended.");}
    else if(!this.isEmptyMultiset()){ return new TypeMultiset(this.baseType, this.head, this.rest.append(a));}
    else { return new TypeMultiset(this.baseType, a, new TypeMultiset(this.baseType));}
  }      
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeMultiset)
    { TypeMultiset that=(TypeMultiset)obj;
      if(!this.baseType.equals(that.baseType)){ throw new RuntimeException("They have different baseType, cannot be compared.");}
      else if(!this.isEmptyMultiset()&&!that.isEmptyMultiset()) { return this.head.equals(that.head)&&this.rest.equals(that.rest);}
      else if(this.isEmptyMultiset()&&that.isEmptyMultiset()) return true;
      else return false;
    }  
    else { throw new RuntimeException("This obj="+obj+" is not of TypeSet");}
  } 
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    buf.append("{{");
    if(!isEmptyMultiset())dump(buf);
    buf.append("}}"); 
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.head);
    if(!rest.isEmptyMultiset()){ buf.append(","); this.rest.dump(buf);}  
  } 
 
  @Override
  public double weight()
  { if(!this.isEmptyMultiset()) return this.head.weight()+this.rest.weight();  
    else return 0.0;
  }
  
  //This oneToMore method is used to pair every element in "this" TypeMultiset to t
  private ListOfDeltas oneToMore(TypeT t)
  { if(this.isEmptyMultiset()) return new ListOfDeltas();
    else return new ListOfDeltas(new Unknown(this.head, t), this.rest.oneToMore(t));
  }        
  //Pair every two elements between this and that sets   
  //Return list of Unknowns which contains every two elements between "this" and that set
  private ListOfDeltas getEveryPair(TypeMultiset that)
  { if (this.isEmptyMultiset()||that.isEmptyMultiset()) { return new ListOfDeltas();}
    else//!this.isEmptySet()&&!that.isEmptySet()
    { return this.oneToMore(that.head).concat(this.getEveryPair(that.rest));}  
  }        
         
  private TypeMultiset getRestSet(TypeT t)
  { if(this.isEmptyMultiset()) return this;
    else if(this.head.equals(t)) return this.rest;
    else return this.rest.getRestSet(t).put(this.head);
  }        
  private static CandidatesList allPairPaths(ListOfDeltas listUnknowns, TypeMultiset x, TypeMultiset y)
  { if(listUnknowns.isEmptyListOfDeltas()||x.isEmptyMultiset()||y.isEmptyMultiset()) return new CandidatesList();
    else//x and y both are nonempty Multisets
    { if(!(listUnknowns.head() instanceof Unknown)) throw new RuntimeException("Here should be instance of Unknown");
      else
      { Unknown fstUnknown=(Unknown)listUnknowns.head();
        DeltaMultiset fstPath=new DeltaMultiset(new StepList(fstUnknown, new StepList(new UnknownRest(x.getRestSet(fstUnknown.orig()), y.getRestSet(fstUnknown.targ())), new StepList())));
        return new CandidatesList(fstPath, new CandidatesList()).concat(allPairPaths(listUnknowns.rest(), x, y));
      }  
    }
  }        
  //Get all Possible deletion path
  //"this" is the same set with x
  private CandidatesList allDelPaths(TypeMultiset x, TypeMultiset y)
  { if(this.isEmptyMultiset()) return new CandidatesList();
    else
    { DeltaMultiset fstPath=new DeltaMultiset(new StepList(new Deletion(this.head), new StepList(new UnknownRest(x.getRestSet(this.head), y), new StepList())));
      return new CandidatesList(fstPath, new CandidatesList()).concat(this.rest.allDelPaths(x, y));
    }
  }  
  //Get all Possible insertion path
  //"this" is the same set with y
  private CandidatesList allInsPaths(TypeMultiset x, TypeMultiset y)
  { if(this.isEmptyMultiset()) return new CandidatesList();
    else
    { DeltaMultiset fstPath=new DeltaMultiset(new StepList(new Insertion(this.head), new StepList(new UnknownRest(x, y.getRestSet(this.head)), new StepList())));
      return new CandidatesList(fstPath, new CandidatesList()).concat(this.rest.allInsPaths(x, y));
    }
  }  
  
@Override
  public CandidatesList refine(TypeT obj)
  {if(obj.typeOf().equals(this.typeOf()))
   { TypeMultiset that=(TypeMultiset)obj;
     if(!this.isEmptyMultiset()&&!that.isEmptyMultiset())
     { ListOfDeltas allPairs=this.getEveryPair(that);
       return allPairPaths(allPairs, this, that);//.concat(this.allDelPaths(this, that)).concat(that.allInsPaths(this, that));
     }
     else if(this.isEmptyMultiset()&&!that.isEmptyMultiset())//this is empty, that is not empty
     { DeltaMultiset path=new DeltaMultiset(new StepList(new Insertion(that.head), new StepList(new UnknownRest(this, that.rest), new StepList())));
       return new CandidatesList(path, new CandidatesList());
     }
     else if(!this.isEmptyMultiset()&&that.isEmptyMultiset())//this is non-empty, that is empty
     { DeltaMultiset path=new DeltaMultiset(new StepList(new Deletion(this.head), new StepList(new UnknownRest(this.rest, that), new StepList())));
       return new CandidatesList(path, new CandidatesList());
     }
     else//both are empty sets
     { return new CandidatesList(new DeltaMultiset(new StepList()), new CandidatesList());}   
   }
   else{ throw new RuntimeException(obj+" is NOT of TypeMultiset.");}
  }        
    
}
