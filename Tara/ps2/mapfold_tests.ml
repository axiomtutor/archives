(* 
                         CS 51 Problem Set 2
            Higher Order Functional Programming -- Testing
 *)

open Mapfold ;;

open Test_simple ;;      (* a really simple unit testing framework *)
  
let test () =
  unit_test ((negate_all []) = [])
            "negate_all empty";
  unit_test ((negate_all [1; -2; 0]) = [-1; 2; 0])
            "negate_all mixed";

  (*  Additional tests go here... *)

  () ;;

test ();;
