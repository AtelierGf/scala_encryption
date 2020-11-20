import calc
import sympy
import random

def GenKey(k):
	prime_min=1<<(k-1)
	prime_max=1<<k
	p=calc.GenPrime(prime_min,prime_max) 	
	g=sympy.primitive_root(p) 
	x=random.randint(0,p-1)
	h=calc.mod(g,x,p)		
	return (p,g,h),x


if __name__=="main":
	GenKey(8)
