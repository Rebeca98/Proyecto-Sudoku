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
  void add(T element);

  /** Adds all the elements of the parameter to this set.
    */
  void addAll(SetADT<T> oset);
  
  /** Removes and returns a random element from this set. 
    */
  T removeRandom();
  
  /** Removes and returns the specified element from this set. 
    */
  T remove(T element);
  
  /** Returns true if this set contains the element.
    */
  boolean contains(T element);
    
  /** Returns true if this set contains no elements.
    */
  boolean isEmpty();
  
  /** Returns the number of elements in this set.
    */
  int size();
  
  Iterator<T> iterator();

  /**
   * Método que realiza la operación de diferencia de conjuntos. i.e: this - oSet o this \ oSet
   * @param oSet: Conjunto para hacer diferencia this \ oSet
   * @return: Conjunto con el resultado de la operación
   */
  SetADT<T> difference(SetADT<T> oSet);

    /**
     * Metodo que realiza la operación de intersección de conjuntos this ∩ oSets
     * @param oSets: Set o sets para hacer itersección. this ∩ oSet1 ∩ oSet2 ∩ ... ∩ oSetN
     * @return: Conjunto con el resultado de la operación
     */
  SetADT<T> intersect(SetADT<T>... oSets);

  int removeSmallest();

  String listItems();
}
