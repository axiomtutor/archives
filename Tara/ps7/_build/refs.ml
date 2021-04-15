(*
                         CS 51 Problem Set 7
                       Refs, Streams, and Music
                   Part 1: Mutable Lists and Cycles
 *)

(* The type of mutable lists. *)
type 'a mlist = Nil | Cons of 'a * 'a mlist ref ;;

 (*......................................................................
Problem 1: Write a function `has_cycle` that returns `true` if a
mutable list has a cycle, `false` otherwise. You may want a recursive
auxiliary function. Don't worry about space usage.

For instance, we can establish a cyclic and an acyclic mutable list
like this:

    # let sample_end = ref Nil ;;
    # let cyclic = Cons (1, ref (Cons (2, sample_end))) ;;
    # sample_end := cyclic ;;
    # let acyclic = Cons (3, ref (Cons(4, ref Nil))) ;;

and test for cycles using `has_cycle`:

    # has_cycle cyclic ;;
    - : bool = true
    # has_cycle acyclic ;;
    - : bool = false
......................................................................*)
  
let rec findCycle (wholeList : 'a mlist) (subList : 'a mlist) (indexCounter : int) : 'a mlist = 
  if indexCounter = 0 
    then Nil (* no cycle found *)
    else (if wholeList == subList 
            then subList 
            else 
              match wholeList with 
              | Nil -> failwith "this line shouldn't be reachable"
              | Cons (_, {contents = newLst}) -> findCycle newLst subList (indexCounter - 1) 
         )

let has_cycle (lst : 'a mlist) : bool =

  let rec trackIndex (ml : 'a mlist) (index : int) : bool = 
    match ml with 
    | Nil -> false
    | Cons (_, {contents = newml}) -> match findCycle lst newml index with
                                      | Nil -> trackIndex newml (index + 1) 
                                      | Cons _ -> true 
  in
  trackIndex lst 0 ;; 

(*......................................................................
Problem 2: Write a function `flatten` that flattens a list (removes
its cycles if it has any) destructively. Again, you may want a
recursive auxiliary function, and you shouldn't worry about space.
......................................................................*)

let flatten (lst : 'a mlist) : unit = 

  let rec trackIndex (ml : 'a mlist) (index : int) : unit = 
    match ml with 
    | Nil -> () (* no cycle *)
    | Cons (_, {contents = newml}) -> 
      match findCycle lst newml index with
      | Nil -> trackIndex newml (index + 1) (* no cycle here...next! *)
      | Cons (_, cyclePointer) -> cyclePointer := Nil (* break cycle here *)
  in
  trackIndex lst 0 ;; 

(*......................................................................
Problem 3: Write a function `mlength`, which nondestructively finds
the number of nodes in a mutable list that may have cycles.
......................................................................*)

let mlength (lst : 'a mlist) : int =
  
  let rec trackNode (ml : 'a mlist) (index : int) : int =
    match ml with 
    | Nil -> index 
    | Cons (_, {contents = newml}) -> 
      match findCycle lst newml index with
      | Nil -> trackNode newml (index + 1) 
      | Cons _ -> index
  in
  trackNode lst 0;;

(*======================================================================
Reflection on the problem set

After each problem set, we'll ask you to reflect on your experience.
We care about your responses and will use them to help guide us in
creating and improving future assignments.

........................................................................
Please give us an honest (if approximate) estimate of how long (in
minutes) this problem set (in total, not just this file) took you to
complete. (If you worked with a partner, we're asking for how much time
each of you (on average) spent on the problem set, not in total.)
......................................................................*)

let minutes_spent_on_pset () : int =
  failwith "time estimate not provided" ;;

(*......................................................................
It's worth reflecting on the work you did on this problem set, where
you ran into problems and how you ended up resolving them. What might
you have done in retrospect that would have allowed you to generate as
good a submission in less time? Please provide us your thoughts in the
string below.
......................................................................*)

let reflection () : string =
  "...your reflections here..." ;;

let noCyc = Cons (1, ref (Cons(1, ref (Cons (1, ref Nil)))));;
let rec cyc = Cons (1, ref (Cons( 1, ref cyc)));;

assert (not (has_cycle noCyc));
assert (has_cycle cyc);

let n = mlength cyc in
flatten cyc;
assert (not (has_cycle cyc));
assert (n = mlength cyc)