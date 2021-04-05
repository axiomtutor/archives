;; open Assert
;; open HigherOrder

(******************************************************************************)
(* PROBLEM 3: BINARY TREES                                                    *)
(******************************************************************************)

(* We've defined a datatype that corresponds to a generic binary tree. These
   are like the binary trees you saw in lecture and in Homework 2, but instead
   of working only with helices and strings, these are generic, and so can
   contain any type. (Note that once you use a tree with data of a particular
   type, you can't add data of another type later on. So you can't put a
   string into an `int tree`, or vice versa.)

   In this file, we'll define some operations on binary trees and binary
   search trees. We have provided comprehensive testing for the more complex
   tree methods so that you only need to implement these functions. *)

type 'a tree =
  | Empty
  | Node of 'a tree * 'a * 'a tree

let leaf x = Node (Empty, x, Empty)

(* Here's an example binary tree.

       5
      / \
     7   9

   Its OCaml representation is below: *)

let example_tree: int tree =
  Node (Node (Empty, 7, Empty), 5, Node (Empty, 9, Empty))

(* Now it's your turn. Write the following tree:

         7
        / \
       4   6
      / \
     2   9
        / \
       5   8
 *)

let your_tree: int tree = Empty (* replace this with your tree *)


(* A "complete" binary tree is one where:
   - every leaf is the same distance from the root
   - every node has either 0 or 2 children  For example:

           19
          /  \
        52    16
       / \    / \
      5   2  4   1

   Write a function `is_complete` that tests whether a tree is complete.

   Hint: you'll probably want to write a helper function (e.g., one that
   calculates the height of a tree). *)


let rec is_complete (t: 'a tree) : bool =
  failwith "Tree.is_complete: unimplemented"

(* Here are some test cases. Make sure to write some of your own -- including
   some where the tree being tested is _not_ complete! *)

let test () : bool =
  is_complete Empty
;; run_test "is_complete: empty tree returns true" test

let test () : bool =
  is_complete (Node (Empty, 6, Empty))
;; run_test "is_complete: leaf returns true" test

let test () : bool =
  let ex_tree = Node (Node (Node (Empty, 5, Empty), 52, Node (Empty, 2, Empty)),
    19, Node (Node (Empty, 4, Empty), 16, Node (Empty, 1, Empty))) in
  is_complete ex_tree
;; run_test "is_complete: complete multi-level tree returns true" test


(* Next, write a function that finds the maximum element in a binary tree, as
   determined by the polymorphic function `max`, which can take in elements of
   various data types. (Note that your function should work for *all* binary
   trees, not just binary search trees.) Because an empty tree does not have a
   maximum element, you should call `failwith` if `tree_max` is passed an
   empty tree.

   Hint: If the function fails when called on an empty tree, then what should
   be used as the base case of the recursion? *)

let rec tree_max (t: 'a tree) : 'a =
  failwith "Tree.tree_max: unimplemented"

let test () : bool =
  tree_max Empty = 42
;; run_failing_test "tree_max: running on empty fails" test

let test () : bool =
  tree_max example_tree = 9
;; run_test "tree_max: depth-2 tree" test

(* Warning: These tests aren't exhaustive! Although your tests themselves will
   not be graded for this problem, it can be tricky to get right without
   thorough testing. *)


(******************************************************************************)
(* PROBLEM 4: BINARY SEARCH TREES                                             *)
(******************************************************************************)

(* Next, we will write some functions that operate on binary SEARCH
   trees. Recall that a binary search tree is a binary tree that
   follows some additional invariants:

   - `Empty` is a binary search tree, and
   - `Node (lt, v, rt)` is a binary search tree if both
     - `lt` is a binary search tree, and every value in `lt` is less than `v`
     - `rt` is a binary search tree, and every value in `rt` is greater than `v`

   Notice that this description is recursive, just like our datatype
   definition!

   You may assume that all of the trees that are provided to the functions in 
   this problem satisfy this invariant. *)

(* NOTE: Many of the functions in the remainder of this file are available in
   the CIS 120 lecture notes. Although it is okay to use the notes as a
   reference if you get stuck, you should ensure you _understand_ them.  The
   best way to do this is to review a function in the notes (as needed), think
   about how it works, and then rewrite it from scratch without looking at the
   notes.

   We've provided several test cases for each of these functions, which
   exercise their functionality fairly thoroughly; however, you may still find
   it helpful to make some more tests of your own, to help you understand what
   the functions should be doing. *)

(* First, write a function called `lookup` that searches a generic binary
   search tree for a particular value. You should leverage the BST invariants
   here, so your implementation should NOT have to search every subtree. *)

let rec lookup (x: 'a) (t: 'a tree) : bool =
  failwith "Tree.lookup: unimplemented"

(* Again, note that the `lookup` function should assume that its argument is a
   binary search tree. This means that elements that are out of place should
   NOT be found by `lookup` (as demonstrated by the test below, which calls
   `lookup` on a tree that is NOT a BST). *)

let test () : bool =
  not (lookup 7 example_tree)
;; run_test "lookup: assumes BST invariants" test

let test () : bool =
  let t = Node (Node (Empty, 1, Empty), 2,  Node (Empty, 3, Empty)) in
  not (lookup 5 t)
;; run_test "lookup: not in tree" test

let test () : bool =
  let t = Node (Node (Empty, 2, Empty), 5,  Node (Empty, 7, Empty)) in
  lookup 2 t
;; run_test "lookup: element in tree" test

(* The next function should return all of the elements of a binary search
   tree, sorted in ascending order. This is called the "in-order traversal" of
   a BST. *)

let rec inorder (t: 'a tree) : 'a list =
  failwith "Tree.inorder: unimplemented"

let test () : bool =
  inorder Empty = []
;; run_test "inorder: empty" test

let test () : bool =
  inorder (Node (Node (Empty, 1, Empty), 2, Node (Empty, 3, Empty))) = [1; 2; 3]
;; run_test "inorder: regular BST" test

(* Write the function that inserts an element into a binary search tree. *)

let rec insert (x: 'a) (t: 'a tree) : 'a tree =
  failwith "Tree.insert: unimplemented"

let test () : bool =
  insert 2 Empty = Node (Empty, 2, Empty)
;; run_test "insert: empty" test

let test () : bool =
  insert 2 (Node (Empty, 2, Empty)) = Node (Empty, 2, Empty)
;; run_test "insert: already in tree" test

let test () : bool =
  let t = (Node (Node (Empty, 1, Empty), 2, Node (Empty, 4, Empty))) in
  let t' = 
    Node (Node (Empty, 1, Empty), 2, Node (Node (Empty, 3, Empty), 4, Empty))
  in
  insert 3 t = t'    
;; run_test "insert: not in tree" test

(* Now, use `fold` and `insert` to write the function `tree_of_list`. *)

let tree_of_list (l: 'a list) : 'a tree =
  failwith "Tree.tree_of_list: unimplemented"


(* The `delete` function returns a tree that is like `t` except with the
   element `x` removed.  If `x` is not present, the resulting tree has the
   same shape as `t`. *)


let rec delete (x: 'a) (t: 'a tree) : 'a tree =
  failwith "Tree.delete: unimplemented"

let test () : bool =
  delete 3 Empty = Empty
;; run_test "delete: empty" test

let test () : bool =
  delete 2 (Node (Node (Empty, 2, Empty), 5, Node (Empty, 7, Empty))) =
    Node (Empty, 5, Node (Empty, 7, Empty))
;; run_test "delete: element in tree" test

let test () : bool =
  let t = (Node (Node (Empty, 2, Empty), 5, Node (Empty, 7, Empty))) in
  delete 1 t = t
;; run_test "delete: element not in tree" test
