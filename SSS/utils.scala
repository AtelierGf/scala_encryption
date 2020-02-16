package secretSharing

object utils {
	def power(x:Int,p:Int):Int = if(p==0) 1 else x * power(x,p-1)
	def mod(x:Int,power:Int,modulo:Int):Int = (1 to power).foldLeft(1)((a ,i) => a * ( x % modulo + modulo ) % modulo )
	def modInv(a:Int,modulo:Int) = {
    	def extEuclid(a:Int,b:Int):(Int,Int) = if (b==0) (1,0) else ((t:(Int,Int)) => (t._2,t._1 - a/b * t._2))(extEuclid(b,a%b))
		mod(extEuclid(a,modulo)._1,1,modulo)
	}
}
