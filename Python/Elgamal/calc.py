import random
import sympy
import math

def GenPrime(a,b):
	while True:
		n=random.randint(a,b)
		i=2
		nIsPrime=True
		while i<=math.sqrt(n):
			if n%i==0:
				nIsPrime=False
				break
			i+=1
		if(nIsPrime):
			return n

def mod(a,b,p):
	result=1
	for i in range(b): 
		result=(result*a)%p
	return result


def GenInverse(p,a):
	x1=p
	x2=a
	y1=0
	y2=1
	while True:
		t=x1
		q=x1//x2
		x1=x2				
		x2=t%x2
		t=y1
		y1=y2
		y2=t-q*y2
		if x2==1:
			if y2<0:
				return p+y2
			return y2

def GenCoprime(x):
	a=0
	while True:
		a=random.randint(0,x-1)
		if sympy.gcd(x,a)==1:
			return a 
			
