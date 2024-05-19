package ksr2.ksrproject2.logic.summarization;


import ksr2.ksrproject2.logic.calculation.membershipFunctions.FuzzySet;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Quantifier {
    private String name;
    private FuzzySet fuzzySet;

    @Override
    public String toString() {
        return name;
    }
}
