all: lab5

lab5: lab5.ml
	ocamlbuild -use-ocamlfind lab5.byte

clean:
	rm -rf _build *.byte

run: ./lab5.byte