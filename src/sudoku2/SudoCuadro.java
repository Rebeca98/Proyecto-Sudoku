/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2;

import java.lang.reflect.Array;

/**
 *
 * @author usuario
 * Clase wrapper para cuadro unitario de sudoku 
 */
public class SudoCuadro {
    
    /**
     * Atributos de clase: 3 conjuntos
     * row representa a la fila. Contiene a los números que están presentes en la fila
     * col, contiene numeros de la columna
     * square, cuadro superior. 
     * markup contiene a todos los numros posibles que el cuadro puede tener sin romper una regla 
     * 
     * No son instanciados al crear cuadro unitario, sino que guardan referencias a los estáticos cresados en setup 
     */
    SetADT<Integer> row; 
    SetADT<Integer> col; 
    SetADT<Integer> square; 
    SetADT<Integer> markup;

    private SetADT<Integer> conjRef = new ArraySet<>();



    /**
     * Constructor. Recibe referencias a conjuntos row,col,square y crea a markup
     * @param row: Referencia a objeto estatico recibida de objeto creado en setup 
     * @param col: Referencia a objeto estatico recibida de objeto creado en setup 
     * @param square: Referencia a objeto estatico recibida de objeto creado en setup 
     */
    public SudoCuadro(SetADT<Integer> row, SetADT<Integer> col) {
        this.row = row;
        this.col = col;
        
        this.markup = new ArraySet<>();
        this.poblrarRef();
    }
    
    public void assignBigSquare(SetADT<Integer> bigSquare){
        this.square = bigSquare; 
    }
    
    /**
     * Metodo diseñado para hacer el proceso de mark-up 
     * Es decir, verificar que numeros es valido colocar en el cuadro intersectando conjuntos set,col,square
     * Crea un conjunto por set, otro por row, etc... Los intersecta para encontrar resultado 
     */
    public SetADT<Integer> performMarkup(){
        SetADT<Integer> result = new ArraySet<>();

        SetADT<Integer> diffRow = this.row.difference(conjRef);
        SetADT<Integer> diffCol = this.col.difference(conjRef);
        SetADT<Integer> diffSqr = this.square.difference(conjRef);

        result = conjRef.intersect(diffCol, diffRow, diffSqr);
        return result;
    }

    private void poblrarRef(){
        for (int i=0; i<10; i++){
            this.conjRef.add(i);
        }
    }
    
    
    
    
    
}
