public interface DataScaler {
    public void fit(double[][] X);
    public double[][] transform(double[][] X);
}
