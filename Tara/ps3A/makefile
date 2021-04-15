all: bignum bignum_test 

bignum: bignum.ml
	ocamlbuild -use-ocamlfind bignum.byte

bignum_test: bignum_test.ml
	ocamlbuild -use-ocamlfind bignum_test.byte

run_bnt: 
	./bignum_test.byte

clean:
	rm -rf _build *.byte
