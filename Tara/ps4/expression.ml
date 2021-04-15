(*
                         CS 51 Problem Set 4
                 A Language for Symbolic Mathematics
 *)

(*======================================================================
Before working on this problem set, read the problem set 4 writeup. It
provides context and crucial information for completing the
problems. In addition, make sure that you are familiar with the
problem set procedures in the document "Problem set procedures for
CS51".

We provide a type definition whose values represent arithmetic
expressions over floating point numbers with a single variable. The
type definition can be found at the top of `expressionLibrary.ml`,
along with enumerated type definitions for the unary and binary
operators, and other useful functions. You will be using this
algebraic data type for this part of the problem set. We refer to
these arithmetic expressions (represented by the `expression` type) as
the "object language". It is the object of our interest, the object of
our software's manipulation, as distinct from the "metalanguage"
(OCaml) in which our manipulations are written.

The module `ExpressionLibrary` is opened here to provide you with
access to the `expression` data type and helpful functions that you
will use for this part of the problem set.
......................................................................*)

open ExpressionLibrary ;;

(*......................................................................
Tips:

1. READ THE WRITEUP, particularly for the definition of the derivative
   function.
 
2. Use the type definitions provided at the top of
   `expressionLibrary.ml` as a reference, and don't change any of the
   code in that file. It provides functions such as `parse` and
   `to_string_smart` that will be helpful in this problem set.
......................................................................*)

(*......................................................................
Problem 1: The function `contains_var` tests whether an expression
contains a variable `x`. For example:

# contains_var (parse "x^4") ;;
- : bool = true
# contains_var (parse "4+3") ;;
- : bool = false
......................................................................*)

let rec contains_var (e : expression) : bool =
  match e with 
  | Num _ -> false 
  | Var -> true
  | Binop _, eL, eR -> if contains_var eL then true else contains_var eR
  | Unop _, e -> contains_var e ;;

(*......................................................................
Problem 2: The function `evaluate` evaluates an expression for a
particular value of `x`. Don't worry about specially handling the
"divide by zero" case. For example:

# evaluate (parse "x^4 + 3") 2.0
- : float = 19.0
......................................................................*)

let rec evaluate (e : expression) (x : float) : float =
  match e with 
  | Num n -> n 
  | Var -> x 
  | Binop bO, eL, eR -> let vL, vR = evaluate eL, evaluate eR in  
                        match bO with 
                        | Add -> vL +. vR
                        | Sub -> vL -. vR
                        | Mul -> vL *. vR
                        | Div -> vL /. vR
                        | Pow -> vL ^ vR
  | Unop uO, e -> let v = evaluate e in 
                  match v with 
                  | Sin -> sin v
                  | Cos -> cos v
                  | Ln -> log v
                  | Neg -> -1. *. v ;;

(*......................................................................
Problem 3: The `derivative` function returns the expression that
represents the derivative of the argument expression. We provide the
skeleton of the implementation here along with a few of the cases;
you're responsible for filling in the remaining parts that implement
the derivative transformation provided in the figure in the
writeup. See the writeup for details.
......................................................................*)

let rec derivative (e : expression) : expression =
  match e with
  | Num _ -> Num 0.
  | Var -> failwith "derivative: Var not implemented"
  | Unop (u, e1) ->
     (match u with
      | Sin -> failwith "derivative: Sin not implemented"
      | Cos -> Binop (Mul, Unop (Neg, Unop (Sin, e1)), derivative e1)
      | Ln -> failwith "derivative: Ln not implemented"
      | Neg -> Unop(Neg, derivative e1))
  | Binop (b, e1, e2) ->
     match b with
     | Add -> Binop (Add, derivative e1, derivative e2)
     | Sub -> Binop (Sub, derivative e1, derivative e2)
     | Mul -> Binop (Add, Binop (Mul, e1, derivative e2),
                          Binop (Mul, derivative e1, e2))
     | Div -> failwith "derivative: div not implemented"
     | Pow -> failwith "derivative: Pow not implemented" ;;
     
(* A helpful function for testing. See the writeup. *)
let checkexp strs xval =
  print_string ("Checking expression: " ^ strs ^ "\n");
  let parsed = parse strs in
  (print_string "contains variable : ";
   print_string (string_of_bool (contains_var parsed));
   print_endline " ";
   print_string "Result of evaluation: ";
   print_float (evaluate parsed xval);
   print_endline " ";
   print_string "Result of derivative: ";
   print_endline " ";
   print_string (to_string (derivative parsed));
   print_endline " ") ;;
  
(*......................................................................
Problem 4: Zero-finding. See writeup for instructions.
......................................................................*)

let find_zero (expr : expression)
              (guess : float)
              (epsilon : float)
              (limit : int)
            : float option =
  failwith "find_zero not implemented" ;;

(*......................................................................
Problem 5: Challenge problem -- exact zero-finding. This problem is
not counted for credit and is not required. Just leave it
unimplemented if you do not want to do it. See writeup for
instructions.
......................................................................*)

let find_zero_exact (e : expression) : expression option =
  failwith "find_zero_exact not implemented" ;;

(*======================================================================
Reflection on the problem set

After each problem set, we'll ask you to reflect on your experience.
We care about your responses and will use them to help guide us in
creating and improving future assignments.

........................................................................
Please give us an honest (if approximate) estimate of how long (in
minutes) this problem set took you to complete. 
......................................................................*)

let minutes_spent_on_pset () : int =
  failwith "time estimate not provided" ;;

(*......................................................................
It's worth reflecting on the work you did on this problem set. Where
did you run into problems and how did you end up resolving them? What
might you have done in retrospect that would have allowed you to
generate as good a submission in less time? Please provide us your
thoughts on these questions and any other reflections in the string
below.
......................................................................*)

let reflection () : string =
  "...your reflections here..." ;;
