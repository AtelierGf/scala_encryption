import calc
import random

def encrypt(m,pk):
	p,g,h=pk
	r=random.randint(0,p-1)	
	c1=calc.mod(g,r,p)	
	c2=calc.mod(calc.mod(h,r,p)*m,1,p)#m*h^r
	return (c1,c2)

if __name__=="main":
	encrypt()
