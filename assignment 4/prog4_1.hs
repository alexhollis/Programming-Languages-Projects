import Data.List
import Control.Monad


-- Authors: Brian Mauldin, Alex Hollis, Supriya Thapa, Ronnie Brewer 
-- Description: Program generates all possible full binary trees. See README.md for instructions
-- on how to run. See FullBinaryTreeProofs_Group1.pdf for proofs on internal node count, leaf count, and number
-- of possible binary trees for a given number of interl nodes "n" count.

data FBTree = Empty | Node FBTree FBTree deriving (Show, Eq)

possibleTrees :: [[FBTree]]
possibleTrees = [Empty] : (map go . drop 1 . inits) possibleTrees where
    go smaller = do
      (ls, rs) <- zip smaller (reverse smaller)
      liftM2 Node ls rs


getPossibleTrees :: Int -> [FBTree]
getPossibleTrees n = last (take (n + 1) possibleTrees)

getInternalNodesCount :: Int -> Int
getInternalNodesCount n = n

getLeafCount :: Int -> Int
getLeafCount n = n + 1

printPossibleBinaryTrees :: Int -> ([FBTree], Int, Int)
printPossibleBinaryTrees n = ((getPossibleTrees n), (getInternalNodesCount n), (getLeafCount n))
  
  
  
