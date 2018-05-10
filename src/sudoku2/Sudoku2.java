/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2;


/**
 *
 * @author AMARTINECI
 */
public class Sudoku2 {

    public static int[][] matrizMaestra = new int[9][9];
    public static SetADT[][] matrizGrande = new SetADT[][]{
            {new ArraySet<Integer>("alpha"), new ArraySet<Integer>("beta"), new ArraySet<Integer>("gamma")},
            {new ArraySet<Integer>("delta"), new ArraySet<Integer>("epsilon"), new ArraySet<Integer>("zeta")},
            {new ArraySet<Integer>("eta"), new ArraySet<Integer>("tetha"), new ArraySet<Integer>("iota")}
    };

    //filas y columnas
    static SetADT[] filas = new ArraySet[9];
    static SetADT[] columnas = new ArraySet[9];
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UI.main(args);
        poblarFilas();
        poblarColumnas();
        poblarMatriz();

    }

    public static void actualizarValorEnCuadro(int row, int col, int value){
        //TODO: Momentaneamente solo añade valores nuevos. No quita valores anteriores de conjuntos

        int pastVal = matrizMaestra[row][col];

        if (pastVal != value){
            eliminarValorAnterior(row,col,pastVal);
        }

        matrizMaestra[row][col] = value;

        //Actualizando conjuntos
        SetADT tempFila = filas[row];
        SetADT tempColumna = columnas[col];
        SetADT tempCuadrote = matrizGrande[ (row/3 % 3) ][ (col/3 % 3) ];

        System.out.println("Actualizando " + tempFila.toString() + "," + tempColumna.toString() +":" + tempCuadrote.toString() + " con valor " + value);

        tempFila.add(value);
        tempColumna.add(value);
        tempCuadrote.add(value);

    }

    private static void eliminarValorAnterior(int row, int col, int pastValue){
        SetADT tempFila = filas[row];
        SetADT tempColumna = columnas[col];
        SetADT tempCuadrote = matrizGrande[ (row/3 % 3) ][ (col/3 % 3) ];

        try{
            tempFila.remove(pastValue);
            tempColumna.remove(pastValue);
            tempCuadrote.remove(pastValue);

        }catch (ElementNotFoundException | EmptyCollectionException ex){
            System.out.println("Elemento no estaba anteriormente ");
        }
    }

    private static void poblarFilas(){
        char key = 'A';
        for (int letra = 0; letra< 9; letra ++){
            filas[letra] = new ArraySet<Integer>(String.valueOf(key));

            key++;
        }
    }

    private static void poblarColumnas(){
        for (int num = 0; num < 9; num++){
            columnas[num] = new ArraySet<Integer>( String.valueOf(num+1));
        }
    }

    private static void poblarMatriz(){
        for(int row=0; row<9; row++){
            for (int col=0; col<9; col++){
                matrizMaestra[row][col] = 0;
            }
        }
    }




    
}
