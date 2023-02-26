def op(fold_f: (Double, Double) => Double)(f: Double => Double, lista: List[Double]): Double = lista match {
  case Nil => 2 - fold_f(1.0, 1.0) // caso base, lista vacía, la suma es cero y en la multiplicacion es 1
  case x :: xs => fold_f(f(x), op(fold_f)(f, xs)) // caso recursivo, se suma/multiplica la cabeza y se llama recursivamente con la cola
}

def factorial(n: Int): Double = {
  if (n < 0) throw new IllegalArgumentException("El argumento debe ser mayor o igual a cero.")
  else if (n == 0) 1
  else op((x, y) => x * y)((x: Double) => x, (1.0 to n by 1.0).toList)
}

// Función auxiliar para el sumatorio
def sumatorio = op((x, y) => x + y)(_,_)

// Función auxiliar para el productorio
def productorio = op((x, y) => x * y)(_,_)

println(sumatorio((x: Double) => x * x, (1.0 to 10.0 by 1.0).toList)) // Result: 385.
println(productorio((x: Double) => x, List(1.0, 30.0, 5.0, 70.0, -2.0))) // Result: -21000.
println(factorial(5)) // Result: 120.