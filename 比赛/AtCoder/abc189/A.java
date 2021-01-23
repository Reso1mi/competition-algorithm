import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        String s = new Scanner(System.in).next();
        System.out.println(((s.charAt(0)==s.charAt(1)) && (s.charAt(1)==s.charAt(2))) ?"Won":"Lost");
    }
}