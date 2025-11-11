public class StandardScaler implements DataScaler{
    private double[] mean;
    private double[] std;

    @Override
    public void fit(double[][] X) {
        if (X == null || X.length == 0) {
            throw new IllegalArgumentException("X no puede ser nula o vacía.");
        }
        int rows = X.length;
        int cols = X[0].length;

        mean = new double[cols];
        std = new double[cols];

        // Verificar rectangularidad
        for (int i = 1; i < rows; i++) {
            if (X[i].length != cols) {
                throw new IllegalArgumentException("La matriz X no es rectangular.");
            }
        }
        // Calcular la media por columna
        for (int j = 0; j < cols; j++) {
            double sum = 0.0;
            for (int i = 0; i < rows; i++) {
                sum += X[i][j];
            }
            mean[j] = sum / rows;
        }
        // Calcular desviación estándar por columna
        for (int j = 0; j < cols; j++) {
            double variance = 0.0;
            for (int i = 0; i < rows; i++) {
                double diff = X[i][j] - mean[j];
                variance += diff * diff;
            }
            // Usamos varianza poblacional /rows, no / (rows-1)
            std[j] = Math.sqrt(variance / rows);
            // Evitar división por cero (columna constante)
            if (std[j] == 0.0) {
                std[j] = 1.0;
            }
        }
    }

    @Override
    public double[][] transform(double[][] X){
        if (mean == null || std == null) {
            throw new IllegalStateException("Debes llamar a fit(X) antes de transform(X).");
        }

        int rows = X.length;
        int cols = X[0].length;

        double[][] Z = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Z[i][j] = (X[i][j] - mean[j]) / std[j];
            }
        }
        return Z;
    }
}
