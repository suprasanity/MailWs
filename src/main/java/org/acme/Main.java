package org.acme;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

@QuarkusMain
public class Main {

    public static void main(String ... args) {
    System.out.println("BOMBASTIC SIDE EYE , CRIMINALS OFFENSIVE SIDE EYES");
        Quarkus.run(args);
    }
}