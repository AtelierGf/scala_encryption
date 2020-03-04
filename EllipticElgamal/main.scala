object Main extends App{
	import EncUtils.math._
	import EncUtils.elliptic._
	import EncUtils.encryption._
	import scala.util.Random

	val (pk,sk) = genKey(10)
	val a = chooseECP(pk.prime,pk.curve)
	val b = chooseECP(pk.prime,pk.curve)

	val cipher1:(ECPoint,ECPoint) = encrypt(a,pk)
	val cipher2:(ECPoint,ECPoint) = encrypt(b,pk)

	val decResult1:ECPoint=decrypt(cipher1,pk,sk)

	val ax = a.x
	val ay = a.y

	val x1 = decResult1.x
	val y1 = decResult1.y

	println("----------------test1----------------")
	println(s"plaintext1:$ax,$ay")
	println(s"decrypt result1:$x1,$y1")

	val decResult2=decrypt(cipher2,pk,sk)

	val bx = b.x
	val by = b.y

	val x2 = decResult2.x
	val y2 = decResult2.y

	println("----------------test2----------------")
	println(s"plaintext2:$bx,$by")
	println(s"encrypt result2:$x2,$y2")

}

