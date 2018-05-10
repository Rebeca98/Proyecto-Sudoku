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

    //public static HashMap<String, SudoCuadro> dictMap = new HashMap<>();
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

    private static void poblarFilas(){
        char key = 'A';
        for (int letra = 0; letra< 9; letra ++){
            filas[letra] = new ArraySet<Integer>(key);

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
