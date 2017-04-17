package com.github.tadaskay.puzzle15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class GeneratorTest {

    private static final int RUNS = 100;
    private Generator generator = new DefaultGenerator();
    private SolvabilityChecker checker = new DefaultSolvabilityChecker();

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[RUNS][0]);
    }

    @Test
    public void generatesSolvablePuzzle() {
        // when
        Integer[][] tiles = generator.generateTiles();
        // then
        assertThat(checker.isSolvable(tiles), is(true));
    }

}
