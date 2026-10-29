package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.formation.exception.NegativeNotAllowedException;

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

    @Test
    void shouldReturn0WhenNull() {
        // given
        String value = null;

        // when
        int result = calc.add(value);

        // then
        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturn0WhenEmpty() {
        // given
        String value = "";

        // when
        int result = calc.add(value);

        // then
        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturn2When2() {
        // given
        String value = "2";

        // when
        int result = calc.add(value);

        // then
        Assertions.assertEquals(2, result);
    }

    @Test
    void shouldReturn4When4() {
        // given
        String value = "4";

        // when
        int result = calc.add(value);

        // then
        Assertions.assertEquals(4, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "null:0",
        "'':0",
        "2:2",
        "4:4",
        "12,5,19:36",
        "6,4,19:29",
        "12;5;19:36",
        "6;4;19:29",
        "'12\n5\n19':36",
        "'10\n5\n5':20",
        "'10,5;7':22",
    }, nullValues = "null", delimiter = ':')
    void shouldAddStringReturnOk(String value, int expected) {
        // given

        // when
        int result = calc.add(value);

        // then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-2",
        "5,-5,10",
        "4,4,-4"
    }, delimiter = ':')
    void shoudAddStringThrowNegativeNotAllowedException(String value) {
        // given

        // when & then
        Assertions.assertThrows(
            NegativeNotAllowedException.class,
            () -> calc.add(value)
        );
    }
}
