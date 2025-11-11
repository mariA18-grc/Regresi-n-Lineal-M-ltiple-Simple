# Regresión Lineal Múltiple- Multiple y Simple
En este proyecto se implementaron dos modelos de regresión lineal en Java:

Regresión Lineal Simple: para analizar la relación entre la temperatura y las ventas de helados.

Regresión Lineal Múltiple: para predecir el puntaje de un examen a partir de múltiples variables (horas de estudio, horas de sueño, asistencia y notas previas).

Ambos modelos buscan encontrar una relación matemática entre las variables independientes (X) y la variable dependiente (Y), permitiendo hacer predicciones a partir de nuevos datos.

Regresión Lineal Simple:

Metodos Utilizados en Lineal Simple

entrenar()	Calcula los coeficientes del modelo	Fórmulas de pendiente e intercepto o ecuación matricial
predecir()	Calcula valores de Y a partir de X	y = m * x + b o y = b₀ + b₁x₁ + …
calcularR2()	Evalúa el ajuste del modelo	R² = 1 - (errores al cuadrado / total de diferencias)
calcularMSE()	Mide los errores del modelo	MSE = (errores al cuadrado) / n

Variable independiente (X): Temperatura (°C)

Variable dependiente (Y): Ventas de helado (unidades)

Ecuación del modelo:
y=mx+b

Esta fórmulas se implementa en el método entrenar(), donde el modelo “aprende” los valores de la pendiente y el intercepto a partir de los datos.


#Conclusiones

En la regresión lineal simple, se demostró cómo la temperatura afecta directamente las ventas de helados: a mayor temperatura, más ventas.

En la regresión múltiple, el modelo muestra cómo distintas variables académicas influyen conjuntamente en el rendimiento del estudiante.

Ambos modelos permiten hacer predicciones basadas en datos reales, siendo herramientas fundamentales en la inteligencia artificial, la economía, la meteorología y la educación.


