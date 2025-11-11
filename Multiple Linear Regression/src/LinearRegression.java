public class LinearRegression {
    private double[] weights;
    private double bias;
    private DataScaler scaler;

    //Getters and setters de los atributos
    public DataScaler getScaler() {
        return scaler;
    }
    public void setScaler(DataScaler scaler) {
        this.scaler = scaler;
    }
    public double getBias() {
        return bias;
    }
    public void setBias(double bias) {
        this.bias = bias;
    }
    public double[] getWeights() {
        return weights;
    }
    public void setWeights(double[] weights) {
        this.weights = weights;
    }
    // Métodos públicos de la clase
    public void data_scaling(DataScaler s){
        if (s == null) {
            throw new IllegalArgumentException("El escalador no puede ser null.");
        }
        this.scaler = s;
    }
    public void fit(double[][] X, double[] y){
        // Validaciones básicas
        if (X == null || y == null || X.length == 0) {
            throw new IllegalArgumentException("X y y no pueden ser nulos ni vacíos.");
        }
        if (X.length != y.length) {
            throw new IllegalArgumentException("La cantidad de filas en X debe coincidir con el tamaño de y.");
        }

        // Si el usuario decidió escalar los datos
        if (scaler != null) {
            scaler.fit(X);         // Aprende media y std
            X = scaler.transform(X); // Devuelve una nueva matriz escalada
        }

        // Agregar columna de 1's al inicio (para el bias)
        double[][] Z = MatrixOps.addIntercept(X);  // Z es n × (p+1)

        // Calcular Z^T
        double[][] ZT = MatrixOps.transpose(Z);

        // Calcular (Z^T Z)
        double[][] ZTZ = MatrixOps.matMul(ZT, Z);

        // Calcular (Z^T y)
        double[] ZTy = MatrixOps.matVec(ZT, y);

        // Resolver (Z^T Z) * beta = Z^T y
        double[] beta = LinearSolvers.solveGaussian(ZTZ, ZTy);

        // Primer valor → bias
        bias = beta[0];

        // Restante → weights
        weights = new double[beta.length - 1];
        for (int i = 1; i < beta.length; i++) {
            weights[i - 1] = beta[i];
        }
    }
    public double[] predict(double[][] X){
        // Validaciones básicas
        if (X == null || X.length == 0) {
            throw new IllegalArgumentException("X no puede ser nulo ni vacío.");
        }

        int rows = X.length;
        int cols = X[0].length;

        // Validación de rectangularidad
        for (int i = 1; i < rows; i++) {
            if (X[i].length != cols) {
                throw new IllegalArgumentException("La matriz X no es rectangular.");
            }
        }

        // Si se usó un escalador en fit, también se aplica aquí
        if (scaler != null) {
            X = scaler.transform(X);
        }
        // Crear vector de predicciones
        double[] yHat = new double[rows];
        // Cálculo de predicción:
        // y_hat[i] = bias + Σ_j (weights[j] * X[i][j])
        for (int i = 0; i < rows; i++) {
            double sum = bias;
            for (int j = 0; j < weights.length; j++) {
                sum += weights[j] * X[i][j];
            }
            yHat[i] = sum;
        }
        return yHat;
    }
    public double score(double[][] X, double[] y, String metric){
        // Validaciones básicas
        if (X == null || y == null || X.length == 0) {
            throw new IllegalArgumentException("X y y no pueden ser nulos ni vacíos.");
        }
        if (X.length != y.length) {
            throw new IllegalArgumentException("La cantidad de filas en X debe coincidir con el tamaño de y.");
        }
        if (weights == null) {
            throw new IllegalStateException("El modelo no ha sido entrenado. Llama primero a fit(X, y).");
        }

        // Obtener predicciones
        double[] yHat = predict(X); // predict ya considera si hay scaler

        // Normalizar la métrica a minúsculas (si es null se usa mse por defecto)
        String m = (metric == null) ? "mse" : metric.trim().toLowerCase();

        switch (m) {
            case "mse":
                return RegressionMetrics.mse(y, yHat);
            case "rmse":
                return RegressionMetrics.rmse(y, yHat);
            case "mae":
                return RegressionMetrics.mae(y, yHat);
            case "r2":
                return RegressionMetrics.r2(y, yHat);
            default:
                throw new IllegalArgumentException(
                        "Métrica no soportada: " + metric +
                                ". Use: 'mse', 'rmse', 'mae' o 'r2'."
                );
        }
    }
}
