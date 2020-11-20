from sympy import prime
from math import gcd

def lcm(x,y):
	return (x*y)//gcd(x,y)		

def gen_coprime(x):
	for i in range(2,x):
		if gcd(x,i)==1:return i

def genkey_rsa(p,q):
	n=p*q # n = p * q
	s=lcm(p-1,q-1) # p-1とq-1の最小公倍数s
	e=gen_coprime(s) #　sと互いに素な数e
	d=pow(e,-1,s) # d = e^-1 mod s
	return (n,e),(d,n) # 公開鍵、秘密鍵
	
def encrypt_rsa(pk,m):
	n,e=pk
	return pow(m,e,n) # c = m ^ e mod n
	
def mult_cipher(pk,x,y):#暗号文で掛け算する関数
	n,e=pk
	return (x*y)%n

if __name__=="__main__":
	p,q=prime(10**6),prime(10**6+1)
	plain_text1=200000
	pk,sk=genkey_rsa(p,q)

	while True:
		print("-"*20)
		print("平文１を入力してください:",end="")
		plain_text1=int(input())
		print("平文２を入力してください:",end="")
		plain_text2=int(input())
		print(f"平文１:{plain_text1}")
		print(f"平文２:{plain_text2}")

		cipher_text1=encrypt_rsa(pk,plain_text1)
		cipher_text2=encrypt_rsa(pk,plain_text2)
		cipher_text3=mult_cipher(pk,cipher_text1,cipher_text2)
		print(f"暗号文１:{cipher_text1}")
		print(f"暗号文２:{cipher_text2}")
		print(f"暗号文１×暗号文２:{cipher_text3}")

		plain_text3=decrypt_rsa(sk,cipher_text3)
		print(f"平文１×平文２:{plain_text3}")