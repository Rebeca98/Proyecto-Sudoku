/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2;

import java.util.Iterator;


public interface SetADT<T>{

  /** Adds one element to this set, ignoring duplicates. 
    */
  public void add(T element);

  /** Adds all the elements of the parameter to this set.
    */
  public void addAll(SetADT<T> oset);
  
  /** Removes and returns a random element from this set. 
    */
  public T removeRandom();
  
  /** Removes and returns the specified element from this set. 
    */
  public T remove(T element);
  
  /** Returns true if this set contains the element.
    */
  public boolean contains(T element);
    
  /** Returns true if this set contains no elements.
    */
  public boolean isEmpty();
  
  /** Returns the number of elements in this set.
    */
  public int size();
  
  public Iterator<T> iterator();

  public SetADT<T> difference(SetADT<T> oSet);

  public SetADT<T> intersect(SetADT<T>... oSets);
}
