
package solution;

import delta.Delta;

public class CandidatesList 
{ private final Delta fstDelta;
  private final CandidatesList restCands;
 
  public CandidatesList(){this.fstDelta=null; this.restCands=null;}// constructor for empty CandidatesList
  public CandidatesList(Delta fstDelta, CandidatesList restCands){ this.fstDelta=fstDelta; this.restCands=restCands;}        
  
  public boolean isEmptyCandidatesList(){ return this.fstDelta==null&&this.restCands==null;}
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    buf.append("[ \n");
    if(!isEmptyCandidatesList()) dump(buf);
    buf.append(" \n]");
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.fstDelta);
    if (!this.restCands.isEmptyCandidatesList()){ buf.append(", \n"); this.restCands.dump(buf);}
  }       
  public Delta fstDelta()
  { if(!this.isEmptyCandidatesList()){return this.fstDelta;}  
    else if(this.isEmptyCandidatesList()){ throw new RuntimeException("Empty CandidatesList has no head.");}
    else{ throw new RuntimeException("Illegal Constructor Usage in CandidatesList fstDelta() method.");}
  }      
  public CandidatesList restCands()
  { if(!this.isEmptyCandidatesList()){return this.restCands;}  
    else if(this.isEmptyCandidatesList()){ return new CandidatesList();}
    else{ throw new RuntimeException("Illegal Constructor Usage in CandidatesList restCands() method.");}
  }
  public CandidatesList ins(Delta d)
  { if (this.isEmptyCandidatesList()) return new CandidatesList(d, new CandidatesList());
    else return new CandidatesList(d, this);
  }        
  //Concatenate this CandidatesList and that CandidatesList
  public CandidatesList concat(CandidatesList that)
  { if(this.isEmptyCandidatesList()) return that;
    else if(that.isEmptyCandidatesList()) return this;
    else return this.ins(that.fstDelta).concat(that.restCands);
  }          
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public CandidatesList solutions()
  { if(!this.isEmptyCandidatesList())//the restCands never be null, even if it is an empty CandidatesList
    { if(!this.fstDelta.sim().isComplete())
      { if(!this.restCands.isEmptyCandidatesList())
        { return this.restCands.insert_cands(this.fstDelta.refine()).solutions();}
        else{ return this.fstDelta.refine().solutions();}  
      }
      else//
      { if(!this.restCands.isEmptyCandidatesList()){ return new CandidatesList(this.fstDelta, this.restCands.solutions());}
        else{ return new CandidatesList(this.fstDelta, new CandidatesList());}  
      } 
    }
    else if(this.isEmptyCandidatesList()){ return new CandidatesList();}
    else{ throw new RuntimeException("Illegal Constructor Usage in CandidatesList solutions() method.");}
  }
  
      
  
  public CandidatesList insert_cands(CandidatesList newCands)
  { if(!newCands.isEmptyCandidatesList())
    { //System.out.println("current cands list="+this);
      //System.out.println("new cands list="+newCands);
      return this.insert_cand(newCands.fstDelta).insert_cands(newCands.restCands);}
    else if(newCands.isEmptyCandidatesList()){ return this;}
    else{ throw new RuntimeException("Illegal Constructor Usage in CandidatesList insert_cands() method.");}
  }  
  // Insert a cand to a sorted CandidatesList
  public CandidatesList insert_cand(Delta newCand)
  { if(!this.isEmptyCandidatesList())
    { double curLwb=this.fstDelta().sim().lwb();
      double curUpb=this.fstDelta().sim().upb();
      double newLwb=newCand.sim().lwb();
      double newUpb=newCand.sim().upb();
      //Prune the impossible branch
      if(newUpb < curLwb){ return this;}
      //If newLwb==curLwb, 
      //However double value cannot be identical
      //When their difference is minor enough, we think they are equal
      if((Math.abs(newLwb-curLwb) < 1.0e-6))
      { if(newUpb >= curUpb){ return new CandidatesList(newCand, this);}
        else{ return new CandidatesList(this.fstDelta, this.restCands.insert_cand(newCand));}//(newUpb<curUpb)
      }  
      else if(newLwb > curLwb){ return new CandidatesList(newCand, this);}
      else { return new CandidatesList(this.fstDelta, this.restCands.insert_cand(newCand));}      
    }
    else if(this.isEmptyCandidatesList()){ return new CandidatesList(newCand,new CandidatesList());}
    else{ throw new RuntimeException("Illegal Constructor Usage in CandidatesList insert_cand() method.");}
  }
}
