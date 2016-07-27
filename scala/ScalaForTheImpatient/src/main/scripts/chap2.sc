
val a = if(2 > 1) 0

import scala.math._

var x = 5
var x0 = 2
var y = 1
var y0 = 3

val distance = { val dx = x - x0; val dy = y - y0; sqrt(dx * dx + dy * dy) }


var r = 2
var n = 4
val aa = { r = r * n; n -= 1 }

def signum(x:Double) = if ( x > 0 ) 1 else (if ( x < 0 ) -1 else 0 )

signum(10)

signum(-10)

signum(0)


for( i <-  1 to 10) print(i)


def countdown(n:Int): Unit = {
  var tmp = n
  do{
    println(tmp)
    tmp -= 1
  }while(tmp >= 0)
}

countdown(10)


lazy val words = scala.io.Source.fromFile("/usr/share/dict/words").mkString


def unicodeCharProduct(str:String) = str.map(_.toLong).product


unicodeCharProduct("Hello")

"Hello".foldLeft(1L)((a,b) => a * b.toLong )

"Hello".map(_.toLong).product

def product(s:String) = s.foldLeft(1L)((a,b) => a * b.toLong )


def x_pow_n(x:Double, n:Int):Double = {
  if( n % 2 == 1 && n > 0){
    x * x_pow_n(x, n - 1)
  } else if ( n % 2 == 0 && n > 0){
    x_pow_n(x, n / 2)
  } else if (n < 0){
    1 / x_pow_n(x, -n)
  } else{
    1
  }
}

x_pow_n(1,2)
