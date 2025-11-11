import java.util.Arrays;
import java.io.File;
import java.util.Scanner;

public class LinearRegressionTester {
    public static void main(String[] args) {
        //-----Regresión lineal para el dataset student_exam_scores.csv
        student_exam_scores();

    }
    public static void student_exam_scores() {
        double[][] x = new double[200][4];
        double[] y = new double[200];
        //----------Escritura de los datos-------------
        File student_exam_scores = new File("student_exam_scores.csv");
        if (student_exam_scores.exists()) {
            try {
                Scanner datos = new Scanner(student_exam_scores);
                String linea = datos.nextLine(); //Para descartar el encabezado
                for (int i = 0; i < x.length; i++) {
                    String fila = datos.nextLine();
                    String[] valores = fila.split(",");
                    for (int j = 0; j < x[0].length; j++) {
                        if (j < 5) {
                            x[i][j] = Double.parseDouble(valores[j]);
                        }
                    }
                    y[i] = Double.parseDouble(valores[4]);
                }
                datos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //--------Datos de entrenamiento
        double[][] X_train = new double[140][4];
        double[] Y_train = new double[140];

        for (int i = 0; i < X_train.length; i++) {
            for (int j = 0; j < X_train[0].length; j++) {
                X_train[i][j] = x[i][j];
            }
            Y_train[i] = y[i];
        }
        //-------Datos de testeo
        double[][] X_test = new double[60][4];
        double[] Y_test = new double[60];

        int index = 0;
        for (int i = 140; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                X_test[index][j] = x[i][j];
            }
            Y_test[index] = y[i];
            index++;
        }
        //-----Regresión lineal
        double[] y_hat = modelo_regresion(X_train, Y_train, X_test, Y_test, x, y);

    }
    public static double[] modelo_regresion(double[][] X_train, double[] Y_train, double[][] X_test, double[] Y_test, double[][] x, double[] y){
        //------Crear y configurar el modelo
        LinearRegression mlr = new LinearRegression();
        mlr.data_scaling(new StandardScaler());

        // Entrenar
        mlr.fit(X_train, Y_train);

        // Mostrar parámetros aprendidos
        System.out.println("weights: " + Arrays.toString(mlr.getWeights()));
        System.out.println("bias: " + mlr.getBias());

        // Predicción
        double[] y_hat = mlr.predict(X_test);

        // Imprimir predicciones una a una (formato similar al pseudocódigo)
        System.out.println("\nPredicciones:");
        for (int i = 0; i < y_hat.length; i++) {
            System.out.printf("y_hat[%d] = %.6f (y_real = %.6f)%n", i, y_hat[i], Y_test[i]);
        }

        // ---------- Métricas ----------
        System.out.println("\nMSE  = " + mlr.score(X_test, Y_test, "mse"));
        System.out.println("RMSE = " + mlr.score(X_test, Y_test, "rmse"));
        System.out.println("MAE  = " + mlr.score(X_test, Y_test, "mae"));
        System.out.println("R2   = " + mlr.score(X_test, Y_test, "r2"));

        return y_hat;
    }
}
