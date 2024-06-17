package ksr2.ksrproject2.logic.summarization;


import ksr2.ksrproject2.logic.calculation.sets.DiscreteSet;
import ksr2.ksrproject2.logic.calculation.sets.FuzzySet;
import ksr2.ksrproject2.logic.calculation.sets.ClassicSet;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Quantifier {
    private String name;
    private FuzzySet fuzzySet;


    public String getName() {
        return name;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

}
