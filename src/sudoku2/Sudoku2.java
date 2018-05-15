/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2;


import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author AMARTINECI
 */
public class Sudoku2 {

    //TODO: Volver todas las cosas iterativas, recursivas.
    private static final int ASCII_INITIAL_VALUE = 65;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static final int dim = 9;

    public static int[][] matrizMaestra = new int[9][9];

    public static int[][] sudokuOne = new int[][]{
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,3,0,8,5},
            {0,0,1,0,2,0,0,0,0},
            {0,0,0,5,0,7,0,0,0},
            {0,0,4,0,0,0,1,0,0},
            {0,9,0,0,0,0,0,0,0},
            {5,0,0,0,0,0,0,7,3},
            {0,0,2,0,1,0,0,0,0},
            {0,0,0,0,4,0,0,0,9}
    };

    public static int[][] sudokuTwo = new int[][]{
            {7,9,0,0,0,0,3,0,0},
            {0,0,0,0,0,6,9,0,0},
            {8,0,0,0,3,0,0,7,6},
            {0,0,0,0,0,5,0,0,2},
            {0,0,5,4,1,8,7,0,0},
            {4,0,0,7,0,0,0,0,0},
            {6,1,0,0,9,0,0,0,8},
            {0,0,2,3,0,0,0,0,0},
            {0,0,9,0,0,0,0,5,4}
    };

    public static SetADT[][] matrizGrande = new SetADT[][]{
            {new ArraySet<Integer>("alpha"), new ArraySet<Integer>("beta"), new ArraySet<Integer>("gamma")},
            {new ArraySet<Integer>("delta"), new ArraySet<Integer>("epsilon"), new ArraySet<Integer>("zeta")},
            {new ArraySet<Integer>("eta"), new ArraySet<Integer>("tetha"), new ArraySet<Integer>("iota")}
    };

    //filas y columnas
    static SetADT[] filas = new ArraySet[9];
    static SetADT[] columnas = new ArraySet[9];

    //Conjunto referencia para apoyo
    static SetADT<Integer> REFERENCIA = new ArraySet<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UI.main(args);
        setupSudoku();

    }

    /**
     * Metodo estático encargado de manejar el nuevo valor obtenido en UI.
     * Responsabilidades básicas:
     * 1. Verificar que sea movimiento válido. i.e que no fuera el valor pasado && que no esté prohibido
     * 2. Removiendo valores anteriores de los conjuntos de referencia, y añadiendo el nuevo
     * 3. Asignando el nuevo valor a matriz maestra en caso de ser válido
     * 4. Marcando cuando hay una violación
     * @param row: Parameto de columna obtenido del TextField
     * @param col: Parametro de Fila obtenido del TextField
     * @param value: Valor que el usuario mete
     * @return :  Si es válido el movimiento
     */
    public static boolean actualizarValorEnCuadro(int[][] mat, int row, int col, int value){
        //Obteniendo valor pasado
        int pastVal = mat[row][col];

        //Obteniendo conjuntos de referencia
        SetADT tempFila = filas[row];
        SetADT tempColumna = columnas[col];
        SetADT tempCuadrote = matrizGrande[ (row/3 % 3) ][ (col/3 % 3) ];

        //Uniendo conjuntos de referencia para formar el inverso del markup.
        //Markup inverso tiene todos los numeros prohibidos
        ArraySet<Integer> prohibidos = new ArraySet<>();
        prohibidos.addAll(tempFila,tempColumna,tempCuadrote);

        System.out.println(ANSI_YELLOW + "Invocacion a metodo de actualización. " + tempFila.toString() + col + " valor pasado: " + pastVal + ANSI_RESET);

        if ( !prohibidos.contains(value) || value == 0){//Distinto de anterior y no contenido en prohibidos
            //Eliminando valor anterior antes de agregar nuevo valor
            try{
                tempFila.remove(pastVal);
                tempColumna.remove(pastVal);
                tempCuadrote.remove(pastVal);

            }catch ( EmptyCollectionException | ElementNotFoundException exc){
                //System.out.println("Elemento no estaba anteriormente. Cuadro estaba vacio ");
            }

            //Ya se confirmó que no era el valor anterior, y era movimiento valido.
            mat[row][col] = value;

            //Actualizando conjuntos
            System.out.println(ANSI_YELLOW + "Actualizando " + tempFila.toString() + tempColumna.toString() +":"
                    + " con valor " + value  + "\n"+ ANSI_RESET);

            tempFila.add(value);
            tempColumna.add(value);
            tempCuadrote.add(value);

            //Actualizando UI

            String objName = tempFila.toString() + tempColumna.toString();
            JTextField cuadroUI = UI.mapa.get(objName);
            if(value != 0){
                cuadroUI.setBackground(Color.WHITE);
                cuadroUI.setText(String.valueOf(value));
            }else{
                cuadroUI.setBackground(Color.RED);
                cuadroUI.setText(" ");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return true;
        }else{
            return false;
        }

    }

    /**
     * Metodo encargado de inicializar todas las estructuras de soporte. 3 responsabilidades:
     * 1. Llenar el conjunto de filas con conjuntos, y sus nombres
     * 2. Llenar el conjunto de columnas con conjuntos y sus nombres
     * 3. Poblando la matriz principal con numeros. Se llena de ceros inicialmente
     */
    private static void setupSudoku(){
        //Poblando filas
        char key = 'A';
        for (int letra = 0; letra< 9; letra ++){
            filas[letra] = new ArraySet<Integer>(String.valueOf(key));

            key++;
        }

        //Poblando columnas
        for (int num = 0; num < 9; num++){
            columnas[num] = new ArraySet<Integer>( String.valueOf(num+1));
        }

        //Poblando Matriz de valores. Se llena con ceros inicialmente.
        for(int row=0; row<9; row++){
            for (int col=0; col<9; col++){
                matrizMaestra[row][col] = 0;
            }
        }

        for (int refVal=1; refVal < 10; refVal++){
            REFERENCIA.add(refVal);
        }

    }

    public static boolean resuelve(int[][] sudo, int row, int col){
        boolean brinca = col + 1 < dim;
        System.out.println(ANSI_BLUE +  "Casilla actual: " + ((char)(row+ASCII_INITIAL_VALUE)) + (col+1) + " y " + !brinca + " debo brincar" + ANSI_RESET);

        if (row >= dim){
            System.out.println("LLegamos al final de la matriz. Se regresa True");
            return true;
        }else if(sudo[row][col] != 0) {
            System.out.println("Estoy en " + ((char)(row+ASCII_INITIAL_VALUE)) + (col+1) + " y econtre el valor " + sudo[row][col] );
            return  resuelve(sudo, brinca ? row:row+1 , brinca ? col+1:0 );
        }else{
            SetADT markup = getMarkup(row,col);
            Iterator iter = markup.iterator();

            System.out.println("Estoy en " + ((char)(row+ASCII_INITIAL_VALUE)) + (col+1) + " y hay un cero. Posibles sol: " + markup.listItems() );

            while (iter.hasNext()){
                int candidate = (int) iter.next();
                System.out.println("Probare con el numero " + candidate + " en " + ((char)(row+ASCII_INITIAL_VALUE)) + (col+1) );
                actualizarValorEnCuadro(sudo,row,col,candidate);

                if (resuelve(sudo, brinca ? row:row+1 , brinca ? col+1:0 )){
                    return true;
                }
                System.out.println(ANSI_RED + "Colocando un cero en: " + ((char)(row+ASCII_INITIAL_VALUE)) + (col+1) + ANSI_RESET);
                actualizarValorEnCuadro(sudo,row,col,0);
            }
            System.out.println("Ningun valor probado funcionó. Regresando false y backtraqueando. Casilla actual "+
                    ((char)(row+ASCII_INITIAL_VALUE)) + (col+1) + "\n");
            return false;
        }
    }

    private static SetADT<Integer> getMarkup(int row, int col){
        //Obteniendo numeros prohibidos
        SetADT tempFila = filas[row];
        SetADT tempColumna = columnas[col];
        SetADT tempCuadrote = matrizGrande[ (row/3 % 3) ][ (col/3 % 3) ];

        //Uniendo conjuntos de referencia para formar el inverso del markup.
        //Markup inverso tiene todos los numeros prohibidos
        ArraySet<Integer> prohibidos = new ArraySet<>();
        prohibidos.addAll(tempFila,tempColumna,tempCuadrote);

        SetADT<Integer> markup;
        markup = prohibidos.difference(REFERENCIA);

        return markup;
    }

    public static void muestraTablero(int[][] mat){
        System.out.print("\n"+"\n");
        for (int row=0; row<9; row++){
            for (int col=0; col<9; col++){
                System.out.print(mat[row][col] + "  ");

                if(((col+1) %3) == 0) {
                    System.out.print("\u001B[32m" + "| " + "\u001B[0m");
                }
            }
            if(((row+1) %3) == 0) {
                System.out.print("\u001B[32m" + "\n--------------------------------\n" + "\u001B[0m");
            }else{
                System.out.println();
            }
        }

    }

    public static void iniciaResolucion(int[][] mat){
        actualizaConjuntos(mat);

        System.out.println("Resulución: \n \n  ");
        muestraTablero(mat);
        resuelve(mat,0,0);
        muestraTablero(mat);
    }

    private static void actualizaConjuntos(int[][] mat ){
        for (int row=0; row<9 ; row++){
            for (int col=0; col<9; col++){
                int curval = mat[row][col];
                actualizarValorEnCuadro(mat,row,col,curval);
            }
        }
    }

}
