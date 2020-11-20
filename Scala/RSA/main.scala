object Main extends App{
	import enc_utils.calcs._
	import enc_utils.rsaEnc._

	val (pk,sk) = genKey(10)

	val ptxt1 = genPtxt(pk._2)
	val ctxt1 = encrypt(pk,ptxt1)	

	val ptxt2 = genPtxt(pk._2)
	val ctxt2 = encrypt(pk,ptxt2)	

	val result = decrypt(sk,ctxt1)

	println("------enc and dec test-------")
	println(s"ptxt:$ptxt1")
	println(s"ctxt:$ctxt1")
	println(s"result of dec:$result")

	val addPtxt = mod(ptxt1*ptxt2,1,pk._2)
	val addCtxt = decrypt(sk,mod(ctxt1*ctxt2,1,sk._2))

	println("-----------HE test-----------")
	println(s"result of ptxt:$addPtxt")
	println(s"result of ctxt:$addCtxt")
}
