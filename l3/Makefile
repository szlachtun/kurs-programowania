all: main

main: Figury.o main.o
	g++ -o main Figury.o main.o

Figury.o: Figury.cpp
	g++ -c Figury.cpp

main.o: main.cpp
	g++ -c main.cpp

clean:
	rm -f main *.o *~

