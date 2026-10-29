package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatriceTest {
    // @Test
    // void testAdd() {
    //     // given / arrange
    //     Calculatrice calc = new Calculatrice();
    //     int a = 4;
    //     int b = 5;

    //     // when / act
    //     int result = calc.add(a, b);

    //     // then / assert
    //     Assertions.assertEquals(9, result);
    // }

    @Test
    void shouldReturn11When5And6() {
        // given / arrange
        Calculatrice calc = new Calculatrice();
        int a = 5;
        int b = 6;

        // when / act
        int result = calc.add(a, b);

        // then / assert
        Assertions.assertEquals(11, result);
    }

    @Test
    void shouldReturn15When6And9() {
        // given / arrange
        Calculatrice calc = new Calculatrice();
        int a = 6;
        int b = 9;

        // when / act
        int result = calc.add(a, b);

        // then / assert
        Assertions.assertEquals(15, result);
    }
}
