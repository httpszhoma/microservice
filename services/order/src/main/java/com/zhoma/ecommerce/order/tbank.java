package com.zhoma.ecommerce.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class tbank {

    public static long nearestMultiple(long a, long d) {
        return (d - a % d) % d;
    }

    public static long minOperations(long n, long x, long y, long z, long[] arr) {
        long totalOperations = 0;

        for (long i = 0; i < n; i++) {
            long opsX = nearestMultiple(arr[(int) i], x);
            long opsY = nearestMultiple(arr[(int) i], y);
            long opsZ = nearestMultiple(arr[(int) i], z);

            totalOperations += Math.min(Math.min(opsX, opsY), opsZ);
        }

        return totalOperations;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long n = scanner.nextLong();
        long x = scanner.nextLong();
        long y = scanner.nextLong();
        long z = scanner.nextLong();
        long[] arr = new long[(int) n];

        for (long i = 0; i < n; i++) {
            arr[(int) i] = scanner.nextLong();
        }
        Arrays.sort(arr);
        int first = (int)x;
        if(gcd(x,y)==x||gcd(x,y)==y){
            first = (int) Math.max(x,y);
        }
        int secound = Math.max()
        System.out.println(minOperations(n, x, y, z, arr));
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


}
