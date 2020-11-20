module Main where

{--import Lib--}
import System.Random

s::Int
s=10

g::Int
g=10

h::Int
h=g^s

p::Int
p=10000

q::Int
q=10000



verifier::Int->Int->Int->String
verifier x z e = if g^z `mod` p == x*h^e `mod` p
                 then "Accept"
                 else "Reject"

commitment::Int->Int->Int->Int
commitment g r p = (g^r) `mod` p

response::Int->Int->Int->Int->Int
response r s e q = (r+s*e) `mod` q

main :: IO ()
main = do
    r <- randomRIO(0,q)
    let x = commitment g r p
    e <- read <$> getLine
    let z = response r s e q
    putStrLn $ verifier x z e


