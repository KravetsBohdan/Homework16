package com.bkravets.utilities;

import com.bkravets.models.Person;
import com.bkravets.models.Sex;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StreamUtilitiesTest {
    static List<Person> people;

    @BeforeAll
    static void beforeAll() {
        people = List.of(new Person("Vasya", 16, Sex.MAN),
                new Person("Petya", 23, Sex.MAN),
                new Person("Olena", 42, Sex.WOMAN),
                new Person("Ivan Ivanovich", 69, Sex.MAN),
                new Person("Katy", 62, Sex.WOMAN)
        );
    }

    @Test
    void shouldGetWomenByAgeBetweenEighteenAndSixty() {

        List<Person> actualList = StreamUtilities
                .getWomenByAgeBetweenEighteenAndSixty(people);

        Assertions.assertThat(actualList)
                .containsExactly(new Person("Olena", 42, Sex.WOMAN));

    }

    @Test
    void shouldGetAverageAgeAmongMen() {

        double averageAge = StreamUtilities.getAverageAgeAmongMen(people);

        Assertions.assertThat(averageAge)
                .isEqualTo(36.0);
    }

    @Test
    void shouldNotGetAverageAgeAmongMenThrowsExceptionWhenEmptyList() {
        Assertions.assertThatThrownBy(() -> StreamUtilities.getAverageAgeAmongMen(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("List cannot be empty");
    }


    @Test
    void shouldCountWorkablePeople() {
        int peopleNumber = StreamUtilities.countWorkablePeople(people);

        Assertions.assertThat(peopleNumber)
                .isEqualTo(2);
    }
}