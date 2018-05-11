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

    public static boolean actualizarValorEnCuadro(int row, int col, int value){
        //Obteniendo valor pasado
        int pastVal = matrizMaestra[row][col];

        //Obteniendo conjuntos de referencia
        SetADT tempFila = filas[row];
        SetADT tempColumna = columnas[col];
        SetADT tempCuadrote = matrizGrande[ (row/3 % 3) ][ (col/3 % 3) ];

        //Uniendo conjuntos de referencia para formar el inverso del markup.
        //Markup inverso tiene todos los elementos que el número no puede ser.
        ArraySet<Integer> prohibidos = new ArraySet<>();
        prohibidos.addAll(tempFila,tempColumna,tempCuadrote);

        if (value != pastVal && !prohibidos.contains(value)){//Distinto de anterior y no contenido en prohibidos
            //Ya se confirmó que no era el valor anterior, y era movimiento valido.
            matrizMaestra[row][col] = value;

            //Actualizando conjuntos
            System.out.println("Actualizando " + tempFila.toString() + "," + tempColumna.toString() +":"
                    + tempCuadrote.toString() + " con valor " + value);

            tempFila.add(value);
            tempColumna.add(value);
            tempCuadrote.add(value);

            return true;
        }else{
            System.out.println("Violación!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return false;
        }

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
