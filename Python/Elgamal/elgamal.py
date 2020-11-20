import calc,elgamal_keygenerate,elgamal_encrypt,elgamal_decrypt
import sympy
import math
import random

def elgamal():
	pk,sk=elgamal_keygenerate.GenKey(16)#8bit key
	p,k,g=pk
	m=random.randint(0,p-1)
	print(m)
	c=elgamal_encrypt.encrypt(m,pk)
	print(c)
	d=elgamal_decrypt.decrypt(c,pk,sk)
	print(d)

elgamal()

