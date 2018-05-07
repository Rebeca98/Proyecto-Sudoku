/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2;

public class EmptyCollectionException  extends RuntimeException {
  /**
   * Sets up this exception with an appropriate message.
   * EDg2
   */
    public EmptyCollectionException(){
        super("EmptyCollectionException: the collection is EMPTY.\n");
    }
    
    public EmptyCollectionException (String collection)
    {
        super ("EmptyCollectionException: the " + collection +
                " is EMPTY.");
    }
}
