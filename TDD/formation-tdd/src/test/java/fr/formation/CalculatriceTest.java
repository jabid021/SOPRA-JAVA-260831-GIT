package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatriceTest {
    private Calculatrice calc;

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

    // S'exécute AVANT chaque test unitaire
    @BeforeEach
    void setup() {
        this.calc = new Calculatrice();
    }

    @Test
    void shouldReturn11When5And6() {
        // given / arrange
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
        int a = 6;
        int b = 9;

        // when / act
        int result = calc.add(a, b);

        // then / assert
        Assertions.assertEquals(15, result);
    }

    @ParameterizedTest
    @CsvSource({
        "5,6,11",
        "6,9,15",
        "0,4,4"
    })
    void shouldAdditionResultOk(int a, int b, int expected) {
        // given

        // when
        int result = calc.add(a, b);

        // then
        Assertions.assertEquals(expected, result);
    }
}
