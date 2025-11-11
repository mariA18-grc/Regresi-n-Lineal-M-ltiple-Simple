public class LinearSolvers {
    // Resolver el sistema lineal A · x = b usando eliminación Gaussiana con pivoteo parcial
    public static double[] solveGaussian(double[][] A, double[] b){ //Funciona correctamente
        // Validaciones básicas
        if (A == null || b == null || A.length == 0) {
            throw new IllegalArgumentException("A y b no pueden ser nulos ni vacíos.");
        }
        int n = A.length;
        int m = A[0].length;
        if (m != n) {
            throw new IllegalArgumentException("A debe ser cuadrada: A es " + n + "x" + m);
        }
        if (b.length != n) {
            throw new IllegalArgumentException("Dimensión incompatible: b tiene tamaño " + b.length + " y A es " + n + "x" + m);
        }
        // Verificar rectangularidad de A
        for (int i = 1; i < n; i++) {
            if (A[i].length != m) {
                throw new IllegalArgumentException("La matriz A no es rectangular.");
            }
        }
        // Construir matriz aumentada [A | b]
        double[][] M = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, M[i], 0, n);
            M[i][n] = b[i];
        }

        final double EPS = 1e-12; // tolerancia para detección de singularidad

        // Eliminación hacia adelante con pivoteo parcial
        for (int k = 0; k < n; k++) {
            // Buscar pivote en columna k (máximo valor absoluto desde fila k)
            int p = k;
            double max = Math.abs(M[k][k]);
            for (int i = k + 1; i < n; i++) {
                double val = Math.abs(M[i][k]);
                if (val > max) { max = val; p = i; }
            }

            // Si el pivote es ~0, el sistema es singular o casi singular
            if (max < EPS) {
                throw new ArithmeticException("Sistema singular o mal condicionado (pivote ~ 0 en columna " + k + ").");
            }

            // Intercambiar filas si es necesario
            if (p != k) {
                double[] tmp = M[p];
                M[p] = M[k];
                M[k] = tmp;
            }

            // Eliminar entradas por debajo del pivote
            for (int i = k + 1; i < n; i++) {
                double factor = M[i][k] / M[k][k];
                M[i][k] = 0.0; // se anula explícitamente
                for (int j = k + 1; j <= n; j++) { // incluye la columna de b
                    M[i][j] -= factor * M[k][j];
                }
            }
        }
        // Sustitución hacia atrás
        double[] x_y = new double[n];   //Devuelve el valor x y y del sistema
        for (int i = n - 1; i >= 0; i--) {
            double s = M[i][n]; // término independiente
            for (int j = i + 1; j < n; j++) {
                s -= M[i][j] * x_y[j];
            }
            double piv = M[i][i];
            if (Math.abs(piv) < EPS) {
                throw new ArithmeticException("Sistema singular en sustitución hacia atrás (pivote ~ 0 en fila " + i + ").");
            }
            x_y[i] = s / piv;
        }
        return x_y;
    }
}
