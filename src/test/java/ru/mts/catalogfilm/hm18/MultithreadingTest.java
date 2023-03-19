package ru.mts.catalogfilm.hm18;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MultithreadingTest {

    @Test
    public void testWithSmallNumbers() throws InterruptedException {
        int numberThreads = 3;
        int number = 1_000_000;
        long expectedResult = 3_000_000L;

        long actualResult = Solution.incrementCounter(numberThreads, number);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testWithBigNumbers() throws InterruptedException {
        int numberThreads = 10;
        int number = 100_000_000;
        long expectedResult = 1_000_000_000L;

        long actualResult = Solution.incrementCounter(numberThreads, number);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
