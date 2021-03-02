package TD3;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/******************************************************************************
 *  Compilation:  javac Matrix.java
 *  Execution:    java Matrix
 *                  https://introcs.cs.princeton.edu/java/95linear/Matrix.java
 *  A bare-bones immutable data type for M-by-N matrices.
 *
 ******************************************************************************/

final public class Matrix {
    private static int complexity;
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }

    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        //Random r = new Random();
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        if(this.N!=B.M) throw new RuntimeException("produit impossible dimension non compatibles");
        this.complexity=0;
        Matrix matrixC = new Matrix(this.M,B.N);
        for(int i=0;i<matrixC.M;i++){
            for(int j=0;j<matrixC.N;j++){
                for(int k=0;k<this.N;k++){
                    matrixC.data[i][j] +=  this.data[i][k]*B.data[k][j];
                    this.complexity++;
                }
            }
        }
        return matrixC;
    }
    public ArrayList<Matrix> decomposition(){
        ArrayList<Matrix> lesSousMatrix = new ArrayList<>();
        Matrix a = this;
        //double[][] donneesB = new double[b.M/2][b.N/2];
        double[][] donneesA = new double[a.M/2][a.N/2];
        //System.out.println("boucle numero : 1");
        for(int i=0;i<a.M/2;i++) {
            for (int j = 0; j < a.N / 2; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneesA[i][j] = a.data[i][j];

            }
        }
        //System.out.println("boucle numero : 2");
        lesSousMatrix.add(new Matrix(donneesA));
        for(int i=0;i<a.M/2;i++) {
            for (int j = a.N/2; j < a.N ; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneesA[i][j-a.N/2] = a.data[i][j];

            }
        }
        //System.out.println("boucle numero : 3");
        lesSousMatrix.add(new Matrix(donneesA));
        for(int i=a.M/2;i<a.M;i++) {
            for (int j = 0; j < a.N / 2; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneesA[i-a.M/2][j] = a.data[i][j];

            }
        }
        //System.out.println("boucle numero : 4");
        lesSousMatrix.add(new Matrix(donneesA));
        for(int i=a.M/2;i<a.M;i++) {
            for (int j = a.N/2; j < a.N ; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneesA[i-a.M/2][j-a.N/2] = a.data[i][j];

            }
        }
        lesSousMatrix.add(new Matrix(donneesA));
        return lesSousMatrix;
    }
    public Matrix timesThread(Matrix B){
        ArrayList<Matrix> arrayListA = this.decomposition();
        ArrayList<Matrix> arrayListB = B.decomposition();

        TC HG1 = new TC(arrayListA.get(0), arrayListB.get(0));
        TC HG2 = new TC(arrayListA.get(1), arrayListB.get(2));

        TC HD1 = new TC(arrayListA.get(0), arrayListB.get(1));
        TC HD2 = new TC(arrayListA.get(1), arrayListB.get(3));

        TC BG1 = new TC(arrayListA.get(2), arrayListB.get(0));
        TC BG2 = new TC(arrayListA.get(3), arrayListB.get(2));

        TC BD1 = new TC(arrayListA.get(2), arrayListB.get(1));
        TC BD2 = new TC(arrayListA.get(3), arrayListB.get(3));

        //On initialise notre géstionaire de threads
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        //submit() au lieu de execute(). submit() return un objet de type Future afin de pouvoir acceder à l'execution des différents threads
        Future<Matrix> f_HG1 = executorService.submit(HG1);
        Future<Matrix> f_HG2 = executorService.submit(HG2);

        Future<Matrix> f_HD1 = executorService.submit(HD1);
        Future<Matrix> f_HD2 = executorService.submit(HD2);

        Future<Matrix> f_BG1 = executorService.submit(BG1);
        Future<Matrix> f_BG2 = executorService.submit(BG2);

        Future<Matrix> f_BD1 = executorService.submit(BD1);
        Future<Matrix> f_BD2 = executorService.submit(BD2);

        // creation de la matrix finale avant le try
        Matrix matrix = new Matrix(0,0);

        while (!executorService.isTerminated()){
            System.out.println("attente");

            //On attend un certain temps en espérant que les threads seront tous finis. La boucle est là pour éviter les erreurs
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){}

            //On récupère les solutions en utilisant les objets Future.
            try {
                Matrix M_HG1 = f_HG1.get();
                Matrix M_HG2 = f_HG2.get();

                Matrix M_HD1 = f_HD1.get();
                Matrix M_HD2 = f_HD2.get();

                Matrix M_BG1 = f_BG1.get();
                Matrix M_BG2 = f_BG2.get();

                Matrix M_BD1 = f_BD1.get();
                Matrix M_BD2 = f_BD2.get();

                //Matrix matrixFinal = recomposerMatrix1(M_HG1, M_HG2, M_HD1, M_HD2, M_BG1, M_BG2, M_BD1, M_BD2);
                //Matrix matrixFinal = recomposerMatrix2(M_HG1, M_HG2, M_HD1, M_HD2, M_BG1, M_BG2, M_BD1, M_BD2);
                //this.data = matrixFinal.data;
                matrix = recomposerMatrix2(M_HG1, M_HG2, M_HD1, M_HD2, M_BG1, M_BG2, M_BD1, M_BD2);
            }
            catch (ExecutionException e){}
            catch (InterruptedException e){}
            executorService.shutdown();

        }
        return matrix;
    }

    private Matrix recomposerMatrix2(Matrix M_HG1, Matrix M_HG2, Matrix M_HD1, Matrix M_HD2, Matrix M_BG1, Matrix M_BG2, Matrix M_BD1, Matrix M_BD2) {
        Matrix M_HG = M_HG1.plus(M_HG2);
        Matrix M_HD = M_HD1.plus(M_HD2);
        Matrix M_BG = M_BG1.plus(M_BG2);
        Matrix M_BD = M_BD1.plus(M_BD2);
        int ligne=M_HG.M , colonne = M_HG.N;
        double[][] donneeFinal = new double[ligne*2][colonne*2];
        for(int i = 0; i < ligne;i++) {
            for (int j = 0; j < colonne; j++) {
                //System.out.println("i = " +i + " j = " + j);
                donneeFinal[i][j] = M_HG.data[i][j];
                donneeFinal[i][j+colonne] = M_HD.data[i][j];
                donneeFinal[i+ligne][j] = M_BG.data[i][j];
                donneeFinal[i+ligne][j+colonne] = M_BD.data[i][j];
            }
        }
        //System.out.println(Arrays.deepToString(donneeFinal));
        return new Matrix(donneeFinal);
    }

    private Matrix recomposerMatrix1(Matrix M_HG1, Matrix M_HG2, Matrix M_HD1, Matrix M_HD2, Matrix M_BG1, Matrix M_BG2, Matrix M_BD1, Matrix M_BD2) {
        //On recompose la matrix a partir des sous matrix
        Matrix M_HG = M_HG1.plus(M_HG2);
        Matrix M_HD = M_HD1.plus(M_HD2);
        Matrix M_BG = M_BG1.plus(M_BG2);
        Matrix M_BD = M_BD1.plus(M_BD2);

        //les data du produit final
        int ligne=M_HG.M , colonne = M_HG.N;
        double[][] donneeFinal = new double[ligne*2][colonne*2];

        //HAUT GAUCHE
        for(int i=0;i<ligne;i++) {
            for (int j = 0; j < colonne; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneeFinal[i][j] = M_HG.data[i][j];
            }
        }
        //HAUT DROITE
        for(int i=0;i<ligne;i++) {
            for (int j = 0; j < colonne; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneeFinal[i][j+colonne] = M_HD.data[i][j];

            }
        }
        //BAS GAUCHE
        for(int i=0;i<ligne;i++) {
            for (int j = 0; j < colonne; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneeFinal[i+ligne][j] = M_BG.data[i][j];

            }
        }
        //BAS DROITE
        for(int i=0;i<ligne;i++) {
            for (int j = 0; j < colonne; j++) {
                //System.out.println("i = "+ i+ " j = " +j );
                donneeFinal[i+ligne][j+colonne] = M_BD.data[i][j];
            }
        }
        return new Matrix(donneeFinal);
    }


    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }

    // print complexity of the multiplication to standard output
    static public void showComp() {
        System.out.print(" Multiplications :  " + Matrix.complexity);
        System.out.println();
    }

    // test client
    public static void main(String[] args) {

        Matrix A = Matrix.random(2, 2);
        Matrix B = Matrix.random(2, 2);
        Matrix C = A.times(B);
        C.show();
        Matrix D = A.timesThread(B);
        D.show();
        System.out.println(D.eq(C));
        //A.show();
        //A.eq(C);



        //B.show();
        //StdOut.println();
        /*Matrix D = Matrix.random(4,4);
        D.show();
        ArrayList<Matrix> x = D.decomposition();
        System.out.println(x);*/

 	/*Matrix C = A.times(B);
        C.show();
         System.out.println();*/

      /* Matrix.showComp();
         System.out.println();      */
    }
}