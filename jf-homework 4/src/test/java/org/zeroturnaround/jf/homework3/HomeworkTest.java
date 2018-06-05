package org.zeroturnaround.jf.homework3;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.zeroturnaround.jf.homework3.task2.Task2.unique;

public class HomeworkTest {
    List<Integer> a = Arrays.asList(1, 2, 3);
    List<Integer> b = Arrays.asList(2, 5, 7);
    List<Integer> c = Arrays.asList(3, 15, 7);
    List<Double> d = Arrays.asList(3.0, 5.0, 7.1);
    List<String> s = Arrays.asList("a", "b");

    @Test
    public void compilesTest() {
        List<Number> compiles = unique(a,b,c,d);
        List<Number> expected = Arrays.asList(1, 5.0, 5, 3.0, 7.1, 15);
        Assert.assertEquals(expected, compiles);
    }

    @Test
    public void compilesAgainTest() {
        List<Object>compilesAgain = unique(a,b,c,d,s);
        List<Object> expected = Arrays.asList(1, "a", "b", 5.0, 5, 3.0, 7.1, 15);
        Assert.assertEquals(expected, compilesAgain);
    }
}
