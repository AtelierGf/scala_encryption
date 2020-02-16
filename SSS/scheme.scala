package secretSharing

object scheme {
	import secretSharing.utils._
	def secretShare (secret:Int,k:Int,n:Int,prime:Int,coeffs:Seq[Int]) = {
	    def distFun(x:Int):Int = secret + mod((1 to k-1).foldLeft(0)((sum,l) => sum + coeffs(l)* power(x,l)),1,prime) 
		for (i <- 1 to n) yield (i,distFun(i)) 
	}	

	def restore (shares:Seq[(Int,Int)],sharesId:Seq[Int],prime:Int) = {
		def restFun(x:Int,sharesId:Seq[Int]):Int = mod( sharesId.foldLeft(0)((sum,j)=> sum + interpolation(x,j,sharesId) * shares(j)._2 ),1,prime )
		def interpolation(x:Int,j:Int,sharesId:Seq[Int]) = ( for(l <- sharesId if l != j) yield l ).foldLeft(1){
			(a,l) =>  a * ((x-shares(l)._1) * modInv( shares(j)._1-shares(l)._1,prime)) 
		}
		restFun(0,sharesId)
	}
	def addShares(s1:Seq[(Int,Int)],s2:Seq[(Int,Int)]) = (0 to s1.size-1).map(x => (s1(x)._1,s1(x)._2 + s2(x)._2))
}
