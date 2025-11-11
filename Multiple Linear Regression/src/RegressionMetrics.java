public class RegressionMetrics {
    // Error cuadrático medio (Mean Squared Error)
    public static double mse(double[] y, double[] yHat){ //Funciona correctamente
        // Validaciones básicas
        if (y == null || yHat == null || y.length == 0) {
            throw new IllegalArgumentException("y y yHat no pueden ser nulos ni vacíos.");
        }
        if (y.length != yHat.length) {
            throw new IllegalArgumentException("Las longitudes de y y yHat deben ser iguales.");
        }

        double sum = 0.0;
        int n = y.length;

        for (int i = 0; i < n; i++) {
            double error = y[i] - yHat[i];
            sum += error * error;
        }
        return sum / n;
    }

    // Raíz del error cuadrático medio (Root Mean Squared Error)
    public static double rmse(double[] y, double[] yHat){ //Funciona correctamente
        // Validaciones básicas
        if (y == null || yHat == null || y.length == 0) {
            throw new IllegalArgumentException("y y yHat no pueden ser nulos ni vacíos.");
        }
        if (y.length != yHat.length) {
            throw new IllegalArgumentException("Las longitudes de y y yHat deben ser iguales.");
        }

        double sum = 0.0;
        int n = y.length;

        for (int i = 0; i < n; i++) {
            double error = y[i] - yHat[i];
            sum += error * error;
        }

        // MSE
        double mse = sum / n;

        // RMSE = sqrt(MSE)
        return Math.sqrt(mse);
    }

    // Error absoluto medio (Mean Absolute Error)
    public static double mae(double[] y, double[] yHat){  //Funciona correctamente
        // Validaciones básicas
        if (y == null || yHat == null || y.length == 0) {
            throw new IllegalArgumentException("y y yHat no pueden ser nulos ni vacíos.");
        }
        if (y.length != yHat.length) {
            throw new IllegalArgumentException("Las longitudes de y y yHat deben ser iguales.");
        }

        double sum = 0.0;
        int n = y.length;

        for (int i = 0; i < n; i++) {
            sum += Math.abs(y[i] - yHat[i]);
        }
        return sum / n;
    }

    // Coeficiente de determinación R^2
    public static double r2(double[] y, double[] yHat){
        // Validaciones básicas
        if (y == null || yHat == null || y.length == 0) {
            throw new IllegalArgumentException("y y yHat no pueden ser nulos ni vacíos.");
        }
        if (y.length != yHat.length) {
            throw new IllegalArgumentException("Las longitudes de y y yHat deben ser iguales.");
        }

        int n = y.length;

        // Calcular la media de y
        double sum = 0.0;
        for (double value : y) {
            sum += value;
        }
        double mean = sum / n;

        // Suma de cuadrados total (variación total de y)
        double ssTotal = 0.0;

        // Suma de cuadrados del error (variación no explicada por el modelo)
        double ssRes = 0.0;

        for (int i = 0; i < n; i++) {
            double diffTotal = y[i] - mean;
            ssTotal += diffTotal * diffTotal;

            double diffRes = y[i] - yHat[i];
            ssRes += diffRes * diffRes;
        }
        // R^2 = 1 - (SS_res / SS_total)
        return 1.0 - (ssRes / ssTotal);
    }
}
