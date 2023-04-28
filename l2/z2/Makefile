all: main

main: WierszTrojkataPascala.o WierszTrojkataPascalaTest.o
	g++ -o z2 WierszTrojkataPascala.o WierszTrojkataPascalaTest.o
 
WierszTrojkataPascala.o: WierszTrojkataPascala.cpp
	g++ -c WierszTrojkataPascala.cpp

WierszTrojkataPascalaTest.o: WierszTrojkataPascalaTest.cpp
	g++ -c WierszTrojkataPascalaTest.cpp

clean:
	rm -f main *.o *~
