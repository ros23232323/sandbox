import scala.util.Random

val n = 20
Random.nextInt(n+1)
var arr = new Array[Int](n)
//var a =  1 to 10 map  (Random.nextInt(n))


(1 to n).map(_ => Random.nextInt(n)).toArray

var array1 = 1 to 5 toArray

for(i <- array1)
  println (i)
