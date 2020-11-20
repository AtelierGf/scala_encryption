module Lib
    ( someFunc,
    secretSharing,
    recostruction,
    ) where

someFunc :: IO ()
someFunc = putStrLn "someFunc"

type Point = [(Int,Int)]

secretSharing::Int->Int->Int->[Point]
secretSharing k n secert = do
    r <- RandomRIO(p)
    let poly = \x->secret:[r*x^i|i<-[1..k-1],random]
    let shares = [(i,poly(i))|i<-[1..n]]


lagrangeInterpolation::[(Int,Int)]->(Int->Int)
lagrangeInterpolation shares = sh

recostruction::Point->Int
recostruction shares = (lagrangeInterpolation shares) 0