package Regre;

import java.util.Scanner;

public class LinealSimple {

    // Variables del modelo
    public double pendiente;
    public double intercepto;

    // Metodo para entrenar modelo
    public void entrenar(double[] x, double[] y) {
        int n = x.length;
        double sumaX = 0, sumaY = 0, sumaXY = 0, sumaX2 = 0;

        // Calcular las sumas necesarias
        for (int i = 0; i < n; i++) {
            sumaX += x[i];
            sumaY += y[i];
            sumaXY += x[i] * y[i];
            sumaX2 += x[i] * x[i];
        }

        //  Calcular la pendiente (m) y el intercepto (b)
        pendiente = (n * sumaXY - sumaX * sumaY) / (n * sumaX2 - (sumaX * sumaX));
        intercepto = (sumaY - pendiente * sumaX) / n;
    }

    // Metodo para predecir una salida
    public double predecir(double x) {
        return pendiente * x + intercepto;
    }

    // Metodo para calcular el R2
    public double calcularR2(double[] x, double[] y) {
        int n = x.length;
        double mediaY = 0;
        for (double valor : y) mediaY += valor;
        mediaY /= n;

        double ssTotal = 0;
        double ssResidual = 0;

        for (int i = 0; i < n; i++) {
            double yPred = predecir(x[i]);
            ssTotal += Math.pow(y[i] - mediaY, 2);
            ssResidual += Math.pow(y[i] - yPred, 2);
        }

        return 1 - (ssResidual / ssTotal);
    }

    // Metodo para calcular el MSE
    public double calcularMSE(double[] x, double[] y) {
        double mse = 0;
        for (int i = 0; i < x.length; i++) {
            double yPred = predecir(x[i]);
            mse += Math.pow(y[i] - yPred, 2);
        }
        return mse / x.length;
    }
}