package com.github.tadaskay.puzzle15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.List;

import static com.github.tadaskay.puzzle15.SolvabilityCheckerTest.Params.solvable;
import static com.github.tadaskay.puzzle15.SolvabilityCheckerTest.Params.unsolvable;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SolvabilityCheckerTest {

    private SolvabilityChecker checker = new DefaultSolvabilityChecker();

    @Parameters
    public static List<Params> data() {
        return asList(
            solvable(new Integer[][]{
                new Integer[]{12, 1, 10, 2},
                new Integer[]{7, 11, 4, 14},
                new Integer[]{5, 0, 9, 15},
                new Integer[]{8, 13, 6, 3},
            }),
            solvable(new Integer[][]{
                new Integer[]{1, 8, 2},
                new Integer[]{0, 4, 3},
                new Integer[]{7, 6, 5},
            }),
            solvable(new Integer[][]{
                new Integer[]{13, 2, 10, 3},
                new Integer[]{1, 12, 8, 4},
                new Integer[]{5, 0, 9, 6},
                new Integer[]{15, 14, 11, 7},
            }),
            solvable(new Integer[][]{
                new Integer[]{6, 13, 7, 10},
                new Integer[]{8, 9, 11, 0},
                new Integer[]{15, 2, 12, 5},
                new Integer[]{14, 3, 1, 4},
            }),
            unsolvable(new Integer[][]{
                new Integer[]{3, 9, 1, 15},
                new Integer[]{14, 11, 4, 6},
                new Integer[]{13, 0, 10, 12},
                new Integer[]{2, 7, 8, 5},
            })
        );
    }

    @Parameter
    public Params params;

    @Test
    public void testSolvability() {
        assertThat(checker.isSolvable(params.tiles), is(params.shouldBeSolvable));
    }

    static class Params {
        Integer[][] tiles;
        boolean shouldBeSolvable;

        Params(Integer[][] tiles, boolean shouldBeSolvable) {
            this.tiles = tiles;
            this.shouldBeSolvable = shouldBeSolvable;
        }

        static Params solvable(Integer[][] tiles) {
            return new Params(tiles, true);
        }

        static Params unsolvable(Integer[][] tiles) {
            return new Params(tiles, false);
        }
    }

}
