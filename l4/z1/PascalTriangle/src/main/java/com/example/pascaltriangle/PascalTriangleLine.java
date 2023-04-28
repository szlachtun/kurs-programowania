package com.example.pascaltriangle;

import java.math.BigInteger;
import java.util.ArrayList;

public class PascalTriangleLine {
    private ArrayList<String> lineNumbers = new ArrayList<>();

    PascalTriangleLine(int n) {
        for (int i = 0; i <= n; i++) {
            // lineNumbers.add(Long.toString(factorial(n) / (factorial(i) * factorial(n - i))));
            BigInteger number = factorial(n).divide(factorial(i).multiply(factorial(n - i)));
            lineNumbers.add(number.toString());
        }
    }

    public BigInteger factorial(int n) {
        if (n == 0) {
            return BigInteger.valueOf(1);
        } else {
            BigInteger result = BigInteger.valueOf(1);
            for (int i = n; i >= 1; i--) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }

    public ArrayList<String> getLineNumbers() {
        return lineNumbers;
    }
}
