import calc

def decrypt(c,pk,sk):
	p,g,h=pk
	c1,c2=c
	x=sk
	d=calc.mod(c1,x,p)
	c=calc.GenInverse(p,d)
	return calc.mod(c*c2,1,p)

if __name__=="main":
	decrypt()
