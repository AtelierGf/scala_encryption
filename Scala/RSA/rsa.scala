package enc_utils

object rsaEnc{
	import enc_utils.calcs._
	import scala.util.Random
	def genKey (bit:Int) ={
		def genTwoPrime(low:Int,high:Int):(Int,Int) = {
			val p:Int= genPrime(low,high)  
			var q:Int= genPrime(low,high)  
			while (p == q) q = genPrime(low,high)  
			(p,q)
		}
		val low:Int = 1 << (bit/2).toInt -1
		val high:Int = 1 << (bit/2).toInt

		val (p,q) = genTwoPrime(low,high)
		val n:Int =  p * q
		val o:Int = (p-1) * (q-1) / euclidAlg(p-1,q-1)
		val e:Int = genCoprime(o)

		val d:Int = modInv(e,o)
		val pk = (e,n)
		val sk = (d,n)
		(pk,sk)
	}

	def genPtxt (num:Int):Int = Random.nextInt(num-1)%num
	def encrypt (pk:(Int,Int),ptxt:Int):Int = mod(ptxt,pk._1,pk._2)	
	def decrypt (sk:(Int,Int),ctxt:Int):Int = mod(ctxt,sk._1,sk._2)
}
