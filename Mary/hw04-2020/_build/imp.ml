;; open Assert

(* Recall the `transform` and `fold` functions from hw03. They are provided
   here for you to use throughout the homework as needed. *)

let rec transform (f: 'a -> 'b) (l: 'a list) : 'b list =
  begin match l with
  | [] -> []
  | x :: xs -> f x :: transform f xs
  end

let rec fold (combine: 'a -> 'b -> 'b) (base: 'b) (l: 'a list) : 'b =
  begin match l with
  | [] -> base
  | x :: xs -> combine x (fold combine base xs)
  end

(******************************************************************************)
(* Problem 1: Options  ********************************************************)
(******************************************************************************)

(* OCaml provides a generic 'a option type, which allows an algorithm to
   indicate that it was unable to come up with a useful value to return.

      type 'a option = None | Some of 'a

   For example, consider this version of `assoc` for lists:

      let rec assoc : (key: 'k) (l : ('k * 'v) list) : 'v =
        begin match l with
        | [] -> failwith "Not_found"
        | (k2,v):: l -> if key = k2 then v else assoc k l
        end

   Here the return type 'v is sort of a lie, isn't it?  If the key isn't
   found in the list, `assoc` hits a failwith instead of returning an
   actual value.

   Use options to re-implement `assoc` with a more truthful type: *)
   (* type 'v option = None | Some of 'v *)
let rec assoc (k: 'k) (l: ('k * 'v) list) : 'v option =
 begin match l with
        | [] -> None
        | (k2,v):: l -> if k = k2 then Some v else assoc k2 l 
        end  

let test () : bool =
  assoc 1 [(1, 2)] = Some 2
;; run_test "assoc key exists" test

let test () : bool =
  assoc 3 [(1, 2)] = None
;; run_test "assoc key doesn't exist" test


(* Write a function that converts an optional optional value into just
   an optional value.  (If you are confused by this description, just
   let the types be your guide. There is basically only one way to do
   it!) *)
let join_option (x: 'a option option) : 'a option =
    begin match x with 
    | None -> None
    | Some y -> y
    end

let test () : bool =
  join_option (Some (Some 3)) = Some 3
;; run_test "join_option Some option" test


(* Write a function that takes a list of optional values and returns a
   list containing all of the values that are present (i.e., stripping
   off the `Some`s and dropping the `None`s). *)
let rec cat_option (l: 'a option list) : 'a list =
  begin match l with
  | [] -> []
  | None::xs -> cat_option xs
  | Some x::xs -> x::(cat_option xs) 
  end

let test () : bool =
  cat_option [ Some 1; None; Some 2; Some 0; None; None] = [1;2;0]
;; run_test "cat_option list contains Some and None options" test


(* Write a function that transforms a list using a partial function. *)
let rec partial_transform (f: 'a -> 'b option) (l: 'a list) : 'b list =
      begin match l with
      | [] -> []
      | x::xs -> begin match f x with 
          | None -> partial_transform f xs
          | Some y -> y::(partial_transform f xs)
          end
      end
         (* | None -> partial_transform f xs
          | Some y -> y::(partial_transform f xs)
              end *)
   

let test () : bool =
  let f = fun x -> if x > 0 then Some (x * x) else None in
  partial_transform f [0; -1; 2; -3] = [4]
;; run_test "partial_transform positive squaring" test


(******************************************************************************)
(* Problem 2: Mutability, Aliasing and Refs  **********************************)
(******************************************************************************)

(* Implement a function `iter` that calls a side-effecting function on each
   element of a list. Do not use any List library functions. *)
let rec iter (f: 'a -> unit) (l: 'a list) : unit =
    begin match l with 
    | [] -> ()
    | x::xs -> f x; iter f xs
    end

(* Here's a test for `iter`, using it to increment each mutable
   reference in an int ref list. *)

let test () : bool =
  let l = [ref 0; ref 1; ref 2] in
  iter (fun r -> r.contents <- 1 + r.contents) l;
  l = [ref 1; ref 2; ref 3]
;; run_test "iter non-empty list" test


(* Now let's explore references in more detail, starting with the
   basics:

   The notation 'a ref is just shorthand for a mutable record with one
   field called 'contents':

      type 'a ref = { mutable contents : 'a }

   To get the current value stored in a reference `r`, use
   `r.contents`.  To update the value in `r` to some new value `x`,
   use `r.contents <- x`.

   The '=' operator is used to check for structural equality between
   refs, whereas '==' is used to check for "reference equality." *)


(* Write a function to increment the contents of a reference cell
   containing an int. The increment function should return the old
   value of the reference. *)
let ref_incr (r : int ref) : int =
   let x = r.contents in
   r.contents <- r.contents + 1
   ; x
   

let test () : bool =
  let r = { contents = 0 } in
  ref_incr r = 0 && ref_incr r = 1 && r.contents = 2
;; run_test "ref_incr incrementing twice" test


(* Write a function to swap the contents of two reference cells.  Note
   that swap returns unit, and the "<-" operator will return unit.
   You can also return unit with "()" *)
let swap (r1: 'a ref) (r2: 'a ref) : unit =
    let temp = r1.contents in
  r1.contents <- r2.contents;
  r2.contents <- temp

let test () : bool =
  let r1 = { contents = 5 } in
  let r2 = { contents = 6 } in
  let _ = swap r1 r2 in
  (6, 5) = (r1.contents, r2.contents)
;; run_test "swap refs with different contents" test


(* One bothersome issue with mutable state is the possibilty of
   *aliasing* between mutable bindings. *)

(* Write a function that determines if two ref cells are aliased by
   using reference equality. *)
let refs_aliased (r1: 'a ref) (r2: 'a ref) : bool =
  r1 == r2

let test () : bool =
  let r1 = { contents = 5 } in
  let r2 = { contents = 5 } in
  let b = refs_aliased r1 r2 in
  (false, true, true) = (b, r1.contents = 5, r2.contents = 5)
;; run_test "refs_aliased different records with same value not aliased" test

let test () : bool =
  let r1 = { contents = 5 } in
  let r2 = r1 in
  let b = refs_aliased r1 r2 in
  (true, true, true) = (b, r1.contents = 5, r2.contents = 5)
;; run_test "refs_aliased aliased records are aliased" test


(***** KUDOS Problem (int_refs_aliased) *********)

(* Write another function to test whether two int refs are aliased
   without using reference equality (i.e. do NOT use OCaml's '==' or
   '!=' operators for this problem). At the end of the function, both
   refs *must* be in the same state they started in!  *)
let int_refs_aliased (r1: int ref) (r2: int ref) : bool =
  failwith "int_refs_aliased: unimplemented"

let test () : bool =
  let r1 = { contents = 5 } in
  let r2 = { contents = 5 } in
  let b = int_refs_aliased r1 r2 in
  (false, true, true) = (b, r1.contents = 5, r2.contents = 5)
;; run_test "int_refs_aliased dif. records with same value are not aliased" test

let test () : bool =
  let r1 = { contents = 5 } in
  let r2 = r1 in
  let b = int_refs_aliased r1 r2 in
  (true, true, true) = (b, r1.contents = 5, r2.contents = 5)
;; run_test "int_refs_aliased aliased records are aliased" test


(******************************************************************************)
(* Problem 3: Equality and Aliases ********************************************)
(******************************************************************************)

(* Write a function that, given a value x and a list of values,
   determines whether any of the elements in the list is an alias of
   x. (HINT: use reference equality!)  Do NOT use the 'refs_aliased'
   function to help with this problem. *)
let rec contains_alias (x: 'a) (l: 'a list) : bool =
  begin match l with
  | [] -> false
  | hd::tl -> if hd == x then true else contains_alias x tl
  end

let test () : bool =
  let r1 = { contents = 5 } in
  let r2 = { contents = 5 } in
  not (contains_alias r1 [r2])
;; run_test "contains_alias singleton no alias" test

let test () : bool =
  let r1 = { contents = 5 } in
  let r2 = { contents = 5 } in
  contains_alias r1 [r2; r1]
;; run_test "contains_alias multi-element list alias exists" test


(* Let's briefly review equality...  - 2 values are *structurally*
   equal when the values they point to on the heap *look* the same.  -
   2 values are *reference* equal when they *point to* the same
   value on the heap

   Complete the test result below with a list of boolean values in
   such a way that the test passes. Make sure you understand why each
   comparison returns true or false before continuing to the next part
   of the homework assignment.  (Hint: If you get confused, draw an
   ASM diagram!) *)
let equality_test_results () : bool list =
 [true; true; true; false; true; false; true; true; true; false; true; 
     false; true; true; true; false] 

let test () : bool =
  let r = { contents = 5 } in
  let o = Some r in 
  [ r = r;  (* true*)  
    r == r;   (* true*) 
    r = { contents = 5 };    (* true*) 
    r == { contents = 5 };   (*false*)
    { contents = 5 } = { contents = 5 };  (* true*) 
    { contents = 5 } == { contents = 5 };  (*false*)
    r.contents = r.contents;   (* true*) 
    r.contents == r.contents;  (* true*) 
    Some r = Some r;  (*true*)
    Some r == Some r;   (* false *)
    Some r = o;    (* true*) 
    Some r == o;   (* false*) 
    o = o;  (* true*) 
    o == o;   (* true*) 
    contains_alias o [o]; (*true*)
    contains_alias (Some r) [Some r]  (* false*)
  ] = equality_test_results ()
;; run_test "interactions between == and options" test


(* Now write a function that determines whether a given value x is
   aliased with one of a list of optional values. *)
let rec contains_alias_option (x: 'a) (l: 'a option list) : bool =
  begin match l with
  | [] -> false
  | None::tl -> contains_alias_option x tl
  | (Some hd)::tl -> if x == hd then true else contains_alias_option x tl
  end
  
  

let test () : bool =
  let r = { contents = 5 } in
  let o1 = Some r in
  contains_alias_option r [o1]
;; run_test "contains_option_alias singleton contains alias" test

let test () : bool =
  let r = { contents = 5 } in
  let o2 = Some { contents = 5 } in
  not (contains_alias_option r [o2])
;; run_test "contains_option_alias singleton does not contain alias" test
