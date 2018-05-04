/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2.EstructurasSoporte;

public class ElementNotFoundException extends RuntimeException {
   /** Sets up this exception with an appropriate message.
    * EDg2
    */
    public ElementNotFoundException(){
        super("ElementNotFoundException: " +
                "element is not in this collection\n");
    }
    
    public ElementNotFoundException(String collection) {
        super("ElementNotFoundException: " +
                "element is not in this " +  collection + "\n");
   }
}
