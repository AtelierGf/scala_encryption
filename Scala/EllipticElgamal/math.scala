package EncUtils

object math{
	import scala.util.Random
	import scala.math.{sqrt,floor}

	def power(x:Int,p:Int):Int = if(p==0) 1 else x * power(x,p-1)
	def mod(x:Int,modulo:Int):Int =  ( x % modulo + modulo ) % modulo 
	def modInv(a:Int,modulo:Int):Int = {
    	def extEuclid(a:Int,b:Int):(Int,Int) = if (b==0) (1,0) else ((t:(Int,Int)) => (t._2,t._1 - a/b * t._2))(extEuclid(b,a%b))
		mod(extEuclid(mod(a,modulo),modulo)._1,modulo)
	}

	case class GField(num:Int,modulo:Int){
		def  +(other:Int):GField = GField(mod(num + other,modulo),modulo)	
		
		def  +(gf:GField):GField = GField(mod(num + gf.num,modulo),modulo)	
	
		def  -(other:Int):GField = GField(mod(num - other,modulo),modulo)	
	
		def  -(gf:GField):GField = GField(mod(num - gf.num,modulo),modulo)	
		
		def  *(other:Int):GField = GField(mod(num * other,modulo),modulo)	
		
		def  *(gf:GField):GField = GField(mod(num * gf.num,modulo),modulo)	
		
		def  /(other:Int):GField = GField(mod(num * modInv(other,modulo),modulo),modulo)	
		
		def  /(gf:GField):GField = GField(mod(num * modInv(gf.num,modulo),modulo),modulo)	
		
		def **(p:Int):GField = GField(mod(power(num,p),modulo),modulo)	

		def <(other:Int):Boolean =  this.num < other
	}
}
