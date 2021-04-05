(******************************************************************************)
(* PROBLEM 7: WRITING TEST CASES                                              *)
(******************************************************************************)

;; open Assert
;; open Deque

(* `DequeTest` is used to test the deque implementation from deque.ml.

   Read through the module, then write your test cases in the space
   provided below.  Your TAs will be grading the completeness of your
   tests.

   Note: Remember the difference between structural and reference
   equality; think about why you shouldn't be directly comparing
   deques with the '=' of structural equality. *)

;; print_endline ("\n--- Running tests for Deque ---")

(* Here is a test to help get you started. *)

let test () : bool =
  is_empty (create ())
;; run_test "is_empty: call on empty returns true" test


(* Now, it's your turn! Make sure to comprehensively test all the other
   functions you implemented in deque.ml. It will probably be helpful to
   have the files deque.ml/mli open as you work.

   We provide many test cases for you, so your job here is to finish writing
   tests for `remove_head`, `remove_tail`, `delete_last`, `delete_first`, and
   `reverse`.

   Your TAs will be manually grading the completeness of your test cases. *)

(* ---------- Write your own test cases below. ---------- *)

let test () : bool =
  let d = create () in
  insert_tail 1 d; insert_tail 2 d; insert_tail 3 d;
  to_list d = [1;2;3]
;; run_test "to_list and insert_tail" test

(* INSERT_HEAD TESTS *)
let test () : bool =
  let d = create () in
  insert_head 1 d;
  valid d && peek_head d = 1 && peek_tail d = 1
;; run_test "insert_head into empty" test

let test () : bool =
  let d = create () in
  insert_head 1 d;
  insert_head 2 d;
  valid d && peek_head d = 2 && peek_tail d = 1
;; run_test "insert_head into singleton" test

let test () : bool =
  let d = create () in
  insert_head 1 d;
  insert_head 2 d;
  insert_head 3 d;
  valid d && peek_head d = 3 && peek_tail d = 1
;; run_test "insert_head into non-empty, multi-element" test

(* insert data type not in queue *)

(* INSERT_TAIL TESTS *)
let test () : bool =
  let d = create () in
  insert_tail 1 d;
  valid d && peek_head d = 1 && peek_tail d = 1
;; run_test "insert_tail into empty" test

let test () : bool =
  let d = create () in
  insert_tail 1 d;
  insert_tail 2 d;
  valid d && peek_head d = 1 && peek_tail d = 2
;; run_test "insert_tail into singleton" test

let test () : bool =
  let d = create () in
  insert_tail 1 d;
  insert_tail 2 d;
  insert_tail 3 d;
  valid d && peek_head d = 1 && peek_tail d = 3
;; run_test "insert_tail into non-empty, multi-element" test

(* REMOVE_HEAD *)
let test () : bool =
  let d = create () in
  insert_head 1 d;
  remove_head d = 1 && is_empty d
;; run_test "remove_head singleton" test

(* head and tail only *)
let test () : bool =
  let d = create () in
  insert_head 1 d;
  insert_tail 2 d;
  remove_head d = 1 && not(is_empty d)
;; run_test "remove_head with head and tail" test

(* multiple elements *)
let test () : bool =
  let d = create () in
  insert_head 1 d;
  insert_head 2 d;
  insert_tail 3 d;
  remove_head d = 2 && not(is_empty d)
;; run_test "remove_head with multiple elements" test


(* REMOVE_TAIL *)
let test () : bool =
  let d = create () in
  insert_tail 1 d;
  remove_tail d = 1
;; run_test "remove_tail singleton" test



(* DELETE_LAST *)

(* empty queue *)
let test () : bool =
  let d = create () in
  delete_last 6 d;
  is_empty d
;; run_test "delete_last from empty dequeue " test


(* singleton queue *)
let test () : bool =
  let d = create () in
  insert_head 4 d;
  delete_last 4 d;
  to_list d = [] && is_empty d
;; run_test "delete_last single elt in dequeue" test

(* deletes single elt in non empty dequeue *)
let test () : bool =
  let d = create () in
  insert_head 3 d;
  insert_tail 4 d;
  delete_last 3 d;
  to_list d = [4] && not(is_empty d)
;; run_test "delete_last single elt in non empty dequeue" test

(* multiple values for deletion *)
let test () : bool =
  let d = create () in
  insert_head 3 d;
  insert_head 4 d;
  insert_tail 5 d;
  insert_tail 5 d;
  delete_last 5 d;
  to_list d = [4; 3; 5] && not(is_empty d)
;; run_test "delete_last all instances of elt in dequeue " test

(* value for deletion not in list *)
let test () : bool =
  let d = create () in
  insert_head 1 d;
  insert_head 2 d;
  delete_last 4 d;
  to_list d = [2; 1] && not(is_empty d)
;; run_test "delete_last elt not in dequeue" test


(* DELETE_FIRST *)
(* empty queue *)
let test () : bool =
  let d = create () in
  delete_first 6 d;
  is_empty d
;; run_test "delete_first from empty dequeue " test


(* singleton queue *)
let test () : bool =
  let d = create () in
  insert_head 4 d;
  delete_first 4 d;
  to_list d = [] && is_empty d
;; run_test "delete_first single elt in dequeue" test

(* deletes single elt in non empty dequeue *)
let test () : bool =
  let d = create () in
  insert_head 3 d;
  insert_tail 4 d;
  delete_first 3 d;
  to_list d = [4] && not(is_empty d)
;; run_test "delete_first single elt in non empty dequeue" test

(* multiple values for deletion *)
let test () : bool =
  let d = create () in
  insert_head 3 d;
  insert_head 4 d;
  insert_tail 5 d;
  insert_tail 5 d;
  delete_first 5 d;
  to_list d = [4; 3; 5] && not(is_empty d)
;; run_test "delete_first all instances of elt in dequeue " test

(* value for deletion not in list *)
let test () : bool =
  let d = create () in
  insert_head 1 d;
  insert_head 2 d;
  delete_first 4 d;
  to_list d = [2; 1] && not(is_empty d)
;; run_test "delete_first elt not in dequeue" test

(* REVERSE *)

let test () : bool =
  let q = create () in
  insert_tail 1 q; insert_tail 2 q;
  reverse q;
  valid q
;; run_test "valid reverse" test

let test () : bool =
  let q = create () in
  let q2 = create () in
  insert_tail 1 q; insert_tail 2 q; insert_tail 3 q; insert_tail 4 q;
  insert_head 1 q2; insert_head 2 q2; insert_head 3 q2; insert_head 4 q2;
  reverse q;
  let v = valid q && valid q2 in
  v && (to_list q) = (to_list q2)
;; run_test "reverse length 3 deque" test

(* empty queue *)

(* singleton queue *)


(* ---------- Write your own test cases above. ---------- *)
