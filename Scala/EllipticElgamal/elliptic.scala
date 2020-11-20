package EncUtils


object elliptic{
	import EncUtils.math._
	import scala.util.Random

	class EC(val prime:Int){
			val (a,b):(GField,GField) = chooseEC
			def chooseEC:(GField,GField) = {
				var m = GField(Random.nextInt(prime),prime)
				var n = GField(Random.nextInt(prime),prime)
				while( discriminant(m,n) == GField(0,prime)) {
					m = GField(Random.nextInt(prime),prime)
					n = GField(Random.nextInt(prime),prime)
				}
				(m,n)
			}
			def apply(p:ECPoint):GField = p.x**3 + p.x * this.a + this.b - p.y**2
			def discriminant(m:GField,n:GField):GField = m**3 * 4 + n**2 * 27
	}

	class ECPoint (val x:GField,val y:GField,val curve:EC){
		def lambda(q:ECPoint):GField = {
			if (this != q) (q.y - this.y) / (q.x - this.x) else  ( this.x ** 2 * 3 + this.curve.a ) / ( this.y * 2 ) 
		}
		def +(q:ECPoint):ECPoint = {
			if (q.isInstanceOf[IfPoint]) {
				this
			}else if (this.x == q.x & (this.y + q.y) == GField(0,this.x.modulo)){
				new IfPoint(GField(-1,x.modulo),GField(-1,x.modulo),this.curve)
			}else{
				val x3:GField = lambda(q)**2 - this.x  - q.x
				new ECPoint(x3 ,lambda(q) * (this.x-x3) - this.y,this.curve)
			}
		}
		def *(k:GField):ECPoint = if (k < 2) this  else this + this.*( k - 1 )
	}

	class IfPoint (x:GField ,y:GField ,curve:EC) extends ECPoint(x,y,curve){
		override def +(q:ECPoint):ECPoint = q
		override def *(k:GField):ECPoint = this 
	}
	def gfAdd(self:ECPoint,other:ECPoint):ECPoint = new ECPoint(self.x + other.x,self.y + other.y,self.curve)
	def gfSub(self:ECPoint,other:ECPoint):ECPoint = new ECPoint(self.x - other.x,self.y - other.y,self.curve)
}
