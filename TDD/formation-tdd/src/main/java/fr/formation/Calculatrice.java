package fr.formation;

import java.util.stream.Stream;

import fr.formation.exception.NegativeNotAllowedException;

public class Calculatrice {
    public int add(int a, int b) {
        // if (a == 6) {
        //     return 15;
        // }

        // return 11;

        return a + b;
    }

    public int add(String value) {
        if (value == null || value.isBlank()) {
            return 0;
        }

        // String[] intStrings = value.split("[,;\\n]");
        // int total = 0;

        // for (String intString : intStrings) {
        //     int intVal = Integer.parseInt(intString);

        //     total += intVal;
        // }

        // return total;

        // if (value.contains("-")) {
        //     throw new NegativeNotAllowedException();
        // }

        return Stream.of(value.split("[,;\\n]"))
            .mapToInt(s -> {
                int val = Integer.parseInt(s);

                if (val < 0) {
                    throw new NegativeNotAllowedException();
                }

                return val;
            })
            .sum()
        ;
    }
}
