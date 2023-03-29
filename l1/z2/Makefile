all: main

main: LiczbyPierwsze.o LiczbyPierwszeTest.o
	g++ -o z2 LiczbyPierwsze.o LiczbyPierwszeTest.o

LiczbyPierwsze.o: LiczbyPierwsze.cpp
	g++ -c LiczbyPierwsze.cpp

LiczbyPierwszeTest.o: LiczbyPierwszeTest.cpp
	g++ -c LiczbyPierwszeTest.cpp

clean:
	rm -f main *.o *~
