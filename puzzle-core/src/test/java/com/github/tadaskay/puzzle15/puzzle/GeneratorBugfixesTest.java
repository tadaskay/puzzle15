package com.github.tadaskay.puzzle15.puzzle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneratorBugfixesTest {

    private Generator generator = new DefaultGenerator();
    private SolvabilityChecker checker = new DefaultSolvabilityChecker();

    @Test
    public void testFixGeneratorSolvedStateBeingMutable() {
        // given
        Integer[][] solvedState = generator.createSolvedState();
        // when
        solvedState[0][0] = 0;
        Integer[][] tiles = generator.generateTiles();
        // then
        assertThat(checker.isSolvable(tiles), is(true));
    }

}
