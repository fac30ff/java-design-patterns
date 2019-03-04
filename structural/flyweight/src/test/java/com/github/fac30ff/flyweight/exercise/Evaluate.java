package com.github.fac30ff.flyweight.exercise;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Evaluate {
    @Test
    public void test() {
        Sentence s = new Sentence("alpha beta gamma");
        s.getWord(1).capitalize = true;
        assertEquals("alpha BETA gamma", s.toString());
    }
}
