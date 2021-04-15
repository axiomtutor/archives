(*
                         CS 51 Problem Set 3
                           Bignums and RSA

                          Bignums Interface
*)

type bignum = {neg: bool; coeffs: int list}

val cBASE : int

val negate : bignum -> bignum
val equal : bignum -> bignum -> bool
val inclusivePosLess : int list -> int list -> bool
val sign : int -> int
val less : bignum -> bignum -> bool
val greater : bignum -> bignum -> bool
val posNumtoList : int -> int list
val abs : int -> int
val from_int : int -> bignum
val numAlg : int list -> int option
val to_int : bignum -> int option

val trim_leading_zeroes : int list -> int list
val clean : bignum -> bignum
val rand_bignum : bignum -> bignum
val explode : string -> char list
val implode : char list -> string
val take_first : 'a list -> int -> 'a list
val split : 'a list -> int -> 'a list * 'a list
val intlog : int -> int
  
val from_string : string -> bignum
val to_string : bignum -> string
                            
val plus : bignum -> bignum -> bignum
val bigTimesInt : int list -> int -> int -> int list
val numTimesInt : int list -> int -> bignum
val bigTimesList : int list -> int list -> bignum 
val times : bignum -> bignum -> bignum

val get_init : int list -> int -> (int list) * (int list)
val times_faster : bignum -> bignum -> bignum

val minutes_spent_on_pset : unit -> int
val reflection : unit -> string
