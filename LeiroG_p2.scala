
val PATH = "u.user"
val df = spark.read.option("delimiter","|").csv(PATH).toDF("UserID", "Age", "Gender", "Occupation", "ZipCode")

//df.show()

// Información básica del DataFrame
println(s"Número de registros: ${df.count()}")
df.printSchema()

// Estadísticas básicas de atributos numéricos
df.describe().show()

// Análisis de atributos categóricos
println(s"Número de valores distintos en Gender: ${df.select("Gender").distinct().count()}")
df.groupBy("Gender").count().show()
println(s"Número de valores distintos en Occupation: ${df.select("Occupation").distinct().count()}")
df.groupBy("Occupation").count().show()

// Consulta para encontrar el número de mujeres programadoras con edades entre 20 y 50 años
val numWomenProgrammers = df.filter("Gender == 'F' and Occupation == 'programmer' and Age >= 20 and Age <= 50").count()
println(s"Número de mujeres programadoras con edades entre 20 y 50 años: $numWomenProgrammers")

// Cerrar sesión de Spark
//spark.stop()