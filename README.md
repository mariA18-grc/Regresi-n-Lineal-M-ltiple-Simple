# Regresión Lineal Múltiple- Multiple y Simple
Autores\
Sara Arboleda Quintero\
María Paula Gómez García

**Link Video Explicación:**  https://youtu.be/eFGtqE75Pw0?si=wCE77ZdgYaYPceKP 

# Descripción general
El propósito de este proyecto, donde se escogió el lenguaje de programación JAVA para su desarrollo, es crear un modelo de regresión lineal desde cero, sin apoyarse en el uso de librerías o clases externas que faciliten el cálculo de las operaciones matriciales requeridas, o para el cálculo de algunas métricas que demuestren el funcionamiento del modelo. La funcionalidad de este modelo es analizar la relación entre unas variables independientes y una variable dependiente continua. Este proyecto forma parte del ejercicio propuesto en la Práctica #3 que busca afianzar los conocimientos en los lenguajes de programación POO (Programación orientada a objetos) que sirven para crear proyectos complejos mediante clases y métodos.

En este proyecto se implementaron dos modelos de regresión lineal en Java:

**Regresión Lineal Simple:** para analizar la relación entre la temperatura y las ventas de helados.

**Regresión Lineal Múltiple:** para predecir el puntaje de un examen a partir de múltiples variables (horas de estudio, horas de sueño, asistencia y notas previas).

Ambos modelos buscan encontrar una relación matemática entre las variables independientes (X) y la variable dependiente (Y), permitiendo hacer predicciones a partir de nuevos datos.

## Estructura del código

### Regresión Lineal Simple:

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

### Regresión Lineal Múltiple:

Métodos utilizados en la regresión lineal múltiple:

setScaler(), getScaler(): Se encargan de escalar los datos de cierta matriz, en caso de que sus valores sean muy variados.

setBias(), getBias(): Tienen como función establecer el bias para el modelo (valor de base).

setWeights(), getWeights(): Se utilizan para calcular los pesos del modelo, que se arrojan al entrenar el modelo con el 70% de los datos para los cuales se realizará el estudio.

dataScaling(): Se lleva a cabo el escalamiento de los datos.

fit(): Método por medio del cual el modelo aprende con los datos ofrecidos, donde ejecuta las operaciones matriciales necesarias para obtener los pesos y el bias.

predict(): Halla y_hat de acuerdo con los pesos y bias proporcionado anteriormente. La predicción se lleva a cabo con el 30% de los datos restantes.

score(): Se hace la medición de las métricas de la regresión y ayuda a comprobar que tan preciso fue el modelo a la hora de hacer la predicción de acuerdo con los datos que ya había "aprendido" anteriormente.

## Problemas y soluciones hallados durante el desarrollo de la práctica

**- Diferencias entre el modelo de regresión lineal múltiple y regresión lineal simple:**  Antes de empezar la construcción de ambos modelos, era necesario comprender en qué variaba cada uno de ellos y cuál era el más adecuado para cada uno de los datasets provistos. Entre estos detalles, entender las nuevas variables que aparecían en el modelo de regresión lineal múltiple y entender cuál era su rol.

**- Escribir los datasets en los arreglos bidimensionales:**  Dado que el dataset de student_exam_scores.csv contenía demasiadas filas o datos diferentes (200 datos distintos), escribirlos a mano era bastante complejo y hacía exhaustivamente largo el código, por ello, era necesario hacer una implementación de lectura de datos adicional y así, facilitar esta tarea.

**- Estructuración del programa en diferentes clases y métodos para mayor facilidad en la implementación:**  Dado que el desarrollo de este modelo se vuelve un poco más complejo cuando se trata de la regresión lineal múltiple, hacer un diagrama de clases era muy importante para así tener mayor claridad de qué parte se encargaría cada clase en específico. Con ello, lograr un programa más claro y fácil de leer, donde se entienda claramente el rol de cada clase para la ejecución del modelo.

## Conclusiones

En la regresión lineal simple, se demostró cómo la temperatura afecta directamente las ventas de helados: a mayor temperatura, más ventas.

En la regresión múltiple, el modelo muestra cómo distintas variables académicas influyen conjuntamente en el rendimiento del estudiante.

Ambos modelos permiten hacer predicciones basadas en datos reales, siendo herramientas fundamentales en la inteligencia artificial, la economía, la meteorología y la educación.


