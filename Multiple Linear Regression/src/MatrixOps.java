public class MatrixOps {

    // Transponer una matriz
    public static double[][] transpose(double[][] M_inicial){  //FUnciona correctamente
        // Validación: matriz no nula y con al menos una fila
        if (M_inicial == null || M_inicial.length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula ni vacía.");
        }
        int rows = M_inicial.length;
        int cols = M_inicial[0].length;

        // Validación: verificar que la matriz sea rectangular
        for (int i = 1; i < rows; i++) {
            if (M_inicial[i].length != cols) {
                throw new IllegalArgumentException("La matriz no es rectangular.");
            }
        }
        // Crear matriz transpuesta
        double[][] Transpuesta = new double[cols][rows];

        // Llenar la matriz transpuesta
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Transpuesta[j][i] = M_inicial[i][j];
            }
        }
        return Transpuesta;
    }

    // Multiplicación matriz-matriz
    public static double[][] matMul(double[][] Matriz_1, double[][] Matriz_2){ //Funciona correctamente
        // Validación: matrices no nulas y con al menos una fila
        if (Matriz_1 == null || Matriz_2 == null || Matriz_1.length == 0 || Matriz_2.length == 0) {
            throw new IllegalArgumentException("Las matrices no pueden ser nulas ni vacías.");
        }

        int aRows = Matriz_1.length;
        int aCols = Matriz_1[0].length;
        int bRows = Matriz_2.length;
        int bCols = Matriz_2[0].length;

        // Validación: verificar que ambas matrices sean rectangulares
        for (int i = 1; i < aRows; i++) {
            if (Matriz_1[i].length != aCols) {
                throw new IllegalArgumentException("La matriz A no es rectangular.");
            }
        }
        for (int i = 1; i < bRows; i++) {
            if (Matriz_2[i].length != bCols) {
                throw new IllegalArgumentException("La matriz B no es rectangular.");
            }
        }

        // Validación: dimensiones compatibles para multiplicación (A es n×p, B es p×m)
        if (aCols != bRows) {
            throw new IllegalArgumentException(
                    "Incompatibilidad de dimensiones: A es " + aRows + "x" + aCols +
                            ", B es " + bRows + "x" + bCols);
        }

        // Crear matriz resultado C (n × m)
        double[][] MxM = new double[aRows][bCols];

        // Multiplicación matriz-matriz (ordenado para mejor rendimiento de cache)
        for (int i = 0; i < aRows; i++) {
            for (int k = 0; k < aCols; k++) {
                double aik = Matriz_1[i][k]; // evitar acceder repetidamente a A[i][k]
                for (int j = 0; j < bCols; j++) {
                    MxM[i][j] += aik * Matriz_2[k][j];
                }
            }
        }
        return MxM;
    }

    // Multiplicación matriz-vector
    public static double[] matVec(double[][] Matriz, double[] vector){ //Funciona correctamente
        // Validación: matriz y vector no nulos
        if (Matriz == null || vector == null || Matriz.length == 0) {
            throw new IllegalArgumentException("La matriz y el vector no pueden ser nulos o vacíos.");
        }

        int rows = Matriz.length;
        int cols = Matriz[0].length;

        // Validación: matriz rectangular
        for (int i = 1; i < rows; i++) {
            if (Matriz[i].length != cols) {
                throw new IllegalArgumentException("La matriz no es rectangular.");
            }
        }
        // Validación: dimensiones compatibles (A es n×p, x es p)
        if (vector.length != cols) {
            throw new IllegalArgumentException(
                    "Incompatibilidad de dimensiones: A es " + rows + "x" + cols +
                            ", x es de tamaño " + vector.length
            );
        }
        // Crear vector resultado (n)
        double[] resultado = new double[rows];

        // Multiplicación matriz-vector: y[i] = Σ_j A[i][j] * x[j]
        for (int i = 0; i < rows; i++) {
            double sum = 0.0;
            for (int j = 0; j < cols; j++) {
                sum += Matriz[i][j] * vector[j];
            }
            resultado[i] = sum;
        }
        return resultado;
    }

    // Agregar columna de 1s al inicio (para el bias)
    public static double[][] addIntercept(double[][] matriz){ //No se ha probado
        // Validación: matriz no nula ni vacía
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula ni vacía.");
        }
        int rows = matriz.length;
        int cols = matriz[0].length;

        // Validación: matriz rectangular
        for (int i = 1; i < rows; i++) {
            if (matriz[i].length != cols) {
                throw new IllegalArgumentException("La matriz no es rectangular.");
            }
        }
        // Crear nueva matriz con una columna extra
        // Z tendrá tamaño: n filas, (p+1) columnas
        double[][] resultado = new double[rows][cols + 1];

        // Primera columna llena de 1's (bias/intercepto)
        for (int i = 0; i < rows; i++) {
            resultado[i][0] = 1.0;
        }

        // Copiar los valores de X desplazados una columna a la derecha
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultado[i][j + 1] = matriz[i][j];
            }
        }
        return resultado;
    }

}
