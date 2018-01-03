
package delta;

import similarity.Sim;

import solution.CandidatesList;

public abstract class Delta
{ public abstract double weight(); 
  public abstract double increase();
  public abstract double decrease();
  public abstract Sim sim();
  public abstract CandidatesList refine(); 
}