all: main

main: main.o Czworokat.o Okrag.o Piecokat.o Szesciokat.o
	g++ -o main main.o Czworokat.o Okrag.o Piecokat.o Szesciokat.o
 
main.o: main.cpp
	g++ -c main.cpp

Czworokat.o: Czworokat.cpp
	g++ -c Czworokat.cpp

Okrag.o: Okrag.cpp
	g++ -c Okrag.cpp

Piecokat.o: Piecokat.cpp
	g++ -c Piecokat.cpp

Szesciokat.o: Szesciokat.cpp
	g++ -c Szesciokat.cpp

clean:
	rm -f main *.o *~

