object Main extends App{
	import scala.util.Random
	import secretSharing.utils._
	import secretSharing.scheme._

	val secret1:Int = 11	
	val secret2:Int = 13
	val k:Int = 3
	val n:Int = 5
	val prime:Int = 17
	val coeffs1:Seq[Int] = Seq(1,Random.nextInt(100000),Random.nextInt(100000))
	val coeffs2:Seq[Int] = Seq(1,Random.nextInt(100000),Random.nextInt(100000))

	val shares1 = secretShare(secret1,k,n,prime,coeffs1)
	val result1:Int = restore(shares1,Seq(0,2,4),prime)

	val shares2 = secretShare(secret2,k,n,prime,coeffs2)
	val result2:Int = restore(shares2,Seq(0,2,4),prime)

	val secret3:Int = mod(secret1 + secret2,1,prime)
	val result3:Int = restore(addShares(shares1,shares2),Seq(0,2,4),prime)

	println("----------result1---------")
	println(s"secret1 = $secret1")
	println(s"result1 = $result1")

	println("----------result2---------")
	println(s"secret2 = $secret2")
	println(s"result2 = $result2")

	println("------result1+result2------")
	println(s"secret1 + secret2 = $secret3")
	println(s"result1 + result2 = $result3")

}
