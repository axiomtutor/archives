open Bignum;;
open Test_simple;;

let times_test () = 
    unit_test ( times (from_int 1) (from_int 2) = from_int 2 ) 
                "small positive times test";
    unit_test ( times (from_int (-2)) (from_int (-2)) = from_int 4 ) 
                "small negative times test";;

let fast_times_test () = 
    unit_test ( times_faster (from_int 1) (from_int 2) = from_int 2 ) 
                "small positive times_fast test";
    unit_test ( times_faster (from_int 123456) (from_int 23456) = from_int 2895783936 ) 
                "large positive times_fast test";;

let main_test () = 
    times_test ();
    fast_times_test ();;

main_test ();;