
package types;

import delta.Deletion;
import delta.Insertion;
import delta.Unknown;
import delta.UnknownRest;
import delta.DeltaSet;
import solution.CandidatesList;
import solution.StepList;
import typeSystem.TYPE;
import utility.ListOfDeltas;

public class TypeSet extends TypeT
{ private final TYPE baseType;
  private final TypeT head;
  private final TypeSet rest;
  // Constructor for empty set
  public TypeSet(TYPE baseType){ this.baseType=baseType; this.head=null; this.rest=null;}
  // Constructor for non-empty set
  public TypeSet(TYPE baseType, TypeT head, TypeSet rest)
  { if(!head.typeOf().equals(baseType)||!rest.baseType.equals(baseType)) throw new RuntimeException("head type doesn't match the baseType of this set");
    this.baseType=baseType; this.head=head; this.rest=rest;
  }

  public static TypeSet EMPTY_SET(TYPE baseType){ return baseType.getEmptySet();}
  
  public boolean isEmptySet(){ return this.head==null&&this.rest==null;}
  
  @Override
  public TYPE typeOf(){ return TYPE.SET(this.baseType);}  
  public TypeT getFst()
  { if(!this.isEmptySet()){ return this.head;}
    else{ throw new RuntimeException("This is an empty set, no head to be returned.");}
  }
  public TypeSet getRest()
  { if(!this.isEmptySet()) return this.rest;
    else return EMPTY_SET(this.baseType);  
  }
  
  private boolean hasEle(TypeT a)
  { if(!a.typeOf().equals(this.baseType))
    { throw new RuntimeException("The a.typeOf()="+a.typeOf()+" is different from"+this.baseType);}
    else if(this.isEmptySet()) return false;
    else return this.head.equals(a)||this.rest.hasEle(a);
  }        
  // add an element in this set
  public TypeSet add(TypeT a)
  { if(!a.typeOf().equals(this.baseType))
    { throw new RuntimeException("The a.typeOf()="+a.typeOf()+" is different from"+this.baseType+" cannot be added to such a set.");}
    else if(this.hasEle(a)) return this;
    else return new TypeSet(this.baseType, a, this);
  } 
  // append an element at the end of this set
  public TypeSet append(TypeT a)
  { if(!a.typeOf().equals(this.baseType))
    { throw new RuntimeException("The a.typeOf()="+a.typeOf()+" is different from"+this.baseType+" cannot be appended.");}
    else if(!this.isEmptySet())
    { if(this.hasEle(a)) return this;
      else return new TypeSet(this.baseType, this.head, this.rest.append(a));
    }
    else { return new TypeSet(this.baseType, a, new TypeSet(this.baseType));}
  }          
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeSet)
    { TypeSet that=(TypeSet)obj;
      if(!this.baseType.equals(that.baseType)){ throw new RuntimeException("They have different baseType, cannot be compared.");}
      else
      { if(!this.isEmptySet()&&!that.isEmptySet())
        { return this.head.equals(that.head)&&this.rest.equals(that.rest);}
        else {return this.isEmptySet()&&that.isEmptySet();}//if both empty return true; else return false.
      }    
    }  
    else { throw new RuntimeException("This obj="+obj+" is not of TypeSet");}
  } 
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    buf.append("{");
    if(!isEmptySet())dump(buf);
    buf.append("}"); 
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.head);
    if(!rest.isEmptySet()){ buf.append(","); this.rest.dump(buf);}  
  } 
   
  @Override
  public double weight()
  { if(!this.isEmptySet()) return this.head.weight()+this.rest.weight();  
    else return 0.0;
  }
  
  //This oneToMore method is used to pair every element in "this" TypeSet to t
  private ListOfDeltas oneToMore(TypeT t)
  { if(this.isEmptySet()) return new ListOfDeltas();
    else return new ListOfDeltas(new Unknown(this.head, t), this.rest.oneToMore(t));
  }        
  //Pair every two elements between this and that sets   
  //Return list of Unknowns which contains every two elements between "this" and that set
  private ListOfDeltas getEveryPair(TypeSet that)
  { if (this.isEmptySet()||that.isEmptySet()) { return new ListOfDeltas();}
    else//!this.isEmptySet()&&!that.isEmptySet()
    { return this.oneToMore(that.head).concat(this.getEveryPair(that.rest));}  
  }        
         
  private TypeSet getRestSet(TypeT t)
  { if(this.isEmptySet()) return this;
    else if(this.head.equals(t)) return this.rest;
    else return this.rest.getRestSet(t).add(this.head);
  }        
  private static CandidatesList allPairPaths(ListOfDeltas listUnknowns, TypeSet x, TypeSet y)
  { if(listUnknowns.isEmptyListOfDeltas()||x.isEmptySet()||y.isEmptySet()) return new CandidatesList();
    else//x and y both are nonempty sets
    { if(!(listUnknowns.head() instanceof Unknown)) throw new RuntimeException("Here should be instance of Unknown");
      else
      { Unknown fstUnknown=(Unknown)listUnknowns.head();
        DeltaSet fstPath=new DeltaSet(new StepList(fstUnknown, new StepList(new UnknownRest(x.getRestSet(fstUnknown.orig()), y.getRestSet(fstUnknown.targ())), new StepList())));
        return new CandidatesList(fstPath, new CandidatesList()).concat(allPairPaths(listUnknowns.rest(), x, y));
      }  
    }
  }        
  //Get all Possible deletion path
  //"this" is the same set with x
  private CandidatesList allDelPaths(TypeSet x, TypeSet y)
  { if(this.isEmptySet()) return new CandidatesList();
    else
    { DeltaSet fstPath=new DeltaSet(new StepList(new Deletion(this.head), new StepList(new UnknownRest(x.getRestSet(this.head), y), new StepList())));
      return new CandidatesList(fstPath, new CandidatesList()).concat(this.rest.allDelPaths(x, y));
    }
  }  
  //Get all Possible insertion path
  //"this" is the same set with y
  private CandidatesList allInsPaths(TypeSet x, TypeSet y)
  { if(this.isEmptySet()) return new CandidatesList();
    else
    { DeltaSet fstPath=new DeltaSet(new StepList(new Insertion(this.head), new StepList(new UnknownRest(x, y.getRestSet(this.head)), new StepList())));
      return new CandidatesList(fstPath, new CandidatesList()).concat(this.rest.allInsPaths(x, y));
    }
  }  
  
  public CandidatesList refine(TypeT obj)
  {if(obj.typeOf().equals(this.typeOf()))
   { TypeSet that=(TypeSet)obj;//non-empty sets
     if(!this.isEmptySet()&&!that.isEmptySet())
     { ListOfDeltas allPairs=this.getEveryPair(that);
       return allPairPaths(allPairs, this, that);//.concat(this.allDelPaths(this, that)).concat(that.allInsPaths(this, that));
     }
     else if(this.isEmptySet()&&!that.isEmptySet())//this is empty, that is not empty
     { DeltaSet path=new DeltaSet(new StepList(new Insertion(that.head), new StepList(new UnknownRest(this, that.rest), new StepList())));
       return new CandidatesList(path, new CandidatesList());
     }
     else if(!this.isEmptySet()&&that.isEmptySet())//this is non-empty, that is empty
     { DeltaSet path=new DeltaSet(new StepList(new Deletion(this.head), new StepList(new UnknownRest(this.rest, that), new StepList())));
       return new CandidatesList(path, new CandidatesList());
     }
     else//both are empty sets
     { return new CandidatesList(new DeltaSet(new StepList()), new CandidatesList());}    
   }
   else{ throw new RuntimeException(obj+" is NOT of TypeSet.");}
  }        
}
