package enc_utils

object calcs{
	import scala.util.Random
	import scala.math.{sqrt,floor}
	def euclidAlg(m:Int,n:Int):Int = {
			if (n == 0) m else euclidAlg(n,m%n)
	}

	def mod(x:Int,power:Int,modulo:Int):Int = (1 to power).foldLeft(1)((a ,i) => a * ( x % modulo + modulo ) % modulo )

	def genPrime(low:Int,high:Int):Int = {
		def notPrime(num:Int):Boolean = {
			for( i <- 2 to floor(sqrt(num)).toInt) if ( num % i == 0 ) return true
			false
		}
		var num = Random.nextInt(high-low)+low 
		while (notPrime(num)) num = Random.nextInt(high-low)+low 
		num
	}
	def genCoprime(x:Int):Int = {
		var num:Int = Random.nextInt(x-1)
		while (euclidAlg(x,num) != 1 || num <= 1) num = Random.nextInt(x-1)
		num
	}

	def modInv(a:Int,modulo:Int) = {
    	def extEuclid(a:Int,b:Int):(Int,Int) = if (b==0) (1,0) else ((t:(Int,Int)) => (t._2,t._1 - a/b * t._2))(extEuclid(b,a%b))
		mod(extEuclid(a,modulo)._1,1,modulo)
	}


}
//	def modInv(a:Int,modulo:Int) = {
//		def extEuclid(a:Int,b:Int):(Int,Int) = {
//			if (b==0) {
//				(1,0) 
//			}else {
//				val (y,x)=extEuclid(b,a%b)
//				((y:Int,x:Int) => (x,y - a/b * x))(y,x)
//			}
//		}
//		mod(extEuclid(a,modulo)._1,1,modulo)
//	}

//	println(mod(3,1,3))
//	println(genPrime(10,100))
//	println(genCoprime(190))
	
