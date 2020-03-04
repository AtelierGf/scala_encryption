package EncUtils

object encryption{ 
	import scala.util.Random
	import scala.math.{sqrt,floor}
	import EncUtils.math._
	import EncUtils.elliptic._

	class PublicKey(val prime:Int,val curve:EC,val p:ECPoint,val y:ECPoint)
	class SecretKey(val x:GField)

	def genPrime(low:Int,high:Int):Int = {
		def isPrime(num:Int):Boolean = ( 2 to floor(sqrt(num)).toInt ).foldLeft(true)((result,i) => result & (if( num%i == 0 ) false else true))
		val num:Int = Random.nextInt(high-low)+low
		if (isPrime(num)) num else genPrime(low,high)
	}
	def chooseECP(modulo:Int,curve:EC):ECPoint = {
		var m:GField = GField(Random.nextInt(modulo),modulo)
		var n:GField = GField(Random.nextInt(modulo),modulo)
		while(curve(new ECPoint(m,n,curve)) != GField(0,modulo)){
				m = GField(Random.nextInt(modulo),modulo)
				n = GField(Random.nextInt(modulo),modulo)
		}
		new ECPoint(m,n,curve) 
	}
	def genKey(k:Int)={
		val low:Int = 1 << k -1
		val high:Int = 1 << k
		val prime:Int = genPrime(low,high)
		val curve = new EC(prime) 
		val basePoint:ECPoint = chooseECP(prime,curve)
		val sk:SecretKey = new SecretKey(GField(Random.nextInt(prime),prime))
		val y:ECPoint = basePoint * sk.x 
		val pk:PublicKey = new PublicKey(prime,curve,basePoint,y) 
		(pk,sk)
	}
	def encrypt(m:ECPoint,pk:PublicKey):(ECPoint,ECPoint)={
		val r:GField = GField(Random.nextInt(pk.prime),pk.prime)
		val c1:ECPoint = pk.p * r
		val c2:ECPoint = gfAdd(pk.y * r,m)
		(c1,c2)
	}
	def decrypt(c:(ECPoint,ECPoint),pk:PublicKey,sk:SecretKey):ECPoint= gfSub(c._2,c._1 * sk.x)
}

