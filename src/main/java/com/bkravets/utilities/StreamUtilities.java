package com.bkravets.utilities;

import com.bkravets.models.Person;
import com.bkravets.models.Sex;

import java.util.List;

public class StreamUtilities {

    public static List<Person> getWomenByAgeBetweenEighteenAndSixty(List<Person> people) {
        return people.stream()
                .filter(person -> person.sex().equals(Sex.WOMAN))
                .filter(person -> person.age() >= 18 && person.age() < 60)
                .toList();
    }

    public static double getAverageAgeAmongMen(List<Person> people) {
        return people.stream()
                .filter(person -> person.sex().equals(Sex.MAN))
                .mapToInt(Person::age)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("List cannot be empty"));
    }

    public static int countWorkablePeople(List<Person> people) {
        return (int) people.stream()
                .filter(person -> person.age() >= 18)
                .filter(StreamUtilities::isOnPension)
                .count();
    }

    private static boolean isOnPension(Person person) {
        return (person.sex().equals(Sex.MAN) && person.age() < 60) ||
                (person.sex().equals(Sex.WOMAN) && person.age() < 55);
    }

}