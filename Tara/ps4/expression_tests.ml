(* 
                         CS 51 Problem Set 4
                 A Language for Symbolic Mathematics
                               Testing
 *)

open Expression ;;
open ExpressionLibrary ;;

open CS51Utils ;; 
open Absbook ;;

let test () =
  unit_test (contains_var (parse "x+3")) "contains_var sum left";
  unit_test (not (contains_var (parse "2"))) "contains_var number";

  (*  Additional tests go here... *)

  () ;;

test ();;
