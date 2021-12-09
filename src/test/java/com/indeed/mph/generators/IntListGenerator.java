package com.indeed.mph.generators;

import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntListGenerator extends ComponentizedGenerator<List> {

    public IntListGenerator() {
        super(List.class);
    }

    boolean generatedEmptyList = false;

    @Override
    public List generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        // make sure we generate the empty list at least once
        if (!generatedEmptyList) {
            generatedEmptyList = true;
            return new ArrayList();
        }

        // the size of the list
        int rng = sourceOfRandomness.nextInt(0, 20);
        int listSize = 0;
        if (rng >= 0 && rng <= 16) {
            listSize = sourceOfRandomness.nextInt(0, 100);
        } else if (rng >= 17 && rng <= 18) {
            listSize = sourceOfRandomness.nextInt(1000, 10000);
        } else if (rng >= 19 && rng <= 20) {
            listSize = sourceOfRandomness.nextInt(100000, 1000000);
        }

        // the stuff in the list
        return IntStream.range(0, listSize).mapToObj(i -> sourceOfRandomness.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE)).collect(Collectors.toList());
    }

    @Override
    public int numberOfNeededComponents() {
        return 1;
    }
}
