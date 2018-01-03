
package solution;

import delta.Delta;
import similarity.Sim;

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
  { if(!this.isEmptyCandidatesList()) return this.fstDelta;
    else throw new RuntimeException("Empty CandidatesList has no head.");
  }      
  public CandidatesList restCands()
  { if(!this.isEmptyCandidatesList()) return this.restCands;  
    else return new CandidatesList();
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
  { //The restCands never be null, even if it is an empty CandidatesList
    //The initial candidatesList=[Unknown(A,B)], hence it never be empty.
    if(!this.isEmptyCandidatesList())
    { //If the comparison is not completed, the fstDelta is a partial solution.
      if(!this.fstDelta.sim().isComplete())
      { //CandidatesList extends=this.fstDelta.refine();
        //CandidatesList inserts=this.restCands.insert_cands(extends);
        //CandidatesList again=inserts.solutions();  
        return this.restCands.insert_cands(this.fstDelta.refine()).solutions();
        //Here, whatever, the restCands is empty or not, by inserting the extends to the restCands makes sure the returned
        //CandidatesList is sorted.
      }
      else//If the first solution shows they are already completely compared, the fstDelta will be the final solution.
      { //However, if the restCands still not empty, the restCands.solutions() is used to research all other solution candidates.
        if(!this.restCands.isEmptyCandidatesList()){ return new CandidatesList(this.fstDelta, this.restCands.solutions());}
        else{ return new CandidatesList(this.fstDelta, new CandidatesList());}  
      } 
    }
    else throw new RuntimeException("This CandidatesList should never be empty.");
  }
  
      
  //Insert the newCands to a sorted curCands=this
  //this candidatesList must have been sorted.
  public CandidatesList insert_cands(CandidatesList newCands)
  { if(!newCands.isEmptyCandidatesList()) return this.insert_cand(newCands.fstDelta).insert_cands(newCands.restCands);
    else return this;
  }  
  // Insert a cand to a sorted CandidatesList
  public CandidatesList insert_cand(Delta newCand)
  { if(!this.isEmptyCandidatesList())
    { Sim curSim=this.fstDelta().sim();
      Sim newSim=newCand.sim();
     
      //Prune the impossible branch
      //curSim.upb<newSim.lwb, the curCand=this.fstDelta will be pruned
      if(curSim.pruneOld(newSim)){ return this.restCands.insert_cand(newCand);}
      //Otherwise, newSim.upb<curSim.lwb, the newCand will be pruned
      else if(curSim.pruneNew(newSim)){ return this;}
      // lower bound of them are identical, the upb of them will be compared
      else if(curSim.equalLwb(newSim))
      { //curSim.upb>=newSim.upb, curCand=this.fstDelta will be kept, the newCand will be inserted to the rest cands list
        if(curSim.higherUpb(newSim)){ return new CandidatesList(this.fstDelta, this.restCands.insert_cand(newCand));}
        //Otherwise, curSim.upb<newSim.upb, the newCand will be put in front of this cands list
        else { return new CandidatesList(newCand, this);}
      }   
      //If the lwb of them are not identical
      //When curSim.lwb>newSim.lwb, the newCand will be inserted into the rest cands
      else if(curSim.higherLwb(newSim)){ return new CandidatesList(this.fstDelta, this.restCands.insert_cand(newCand));}
      //Otherwise, curSim.lwb<newSim.lwb, the newCand will be the first one in the cands list.
      else{ return new CandidatesList(newCand, this);}
    }
    else return new CandidatesList(newCand,new CandidatesList());
  }
}
