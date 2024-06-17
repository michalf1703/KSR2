package ksr2.ksrproject2.logic.calculation.sets;

import ksr2.ksrproject2.logic.calculation.membershipFunctions.MembershipFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class FuzzySet {
    private final MembershipFunction membershipFunction;
    private final ClassicSet universeOfDiscourse;

    public double getMembershipDegree(double x) {
        return membershipFunction.getValue(x);
    }

    public double getCardinality() {
        if (universeOfDiscourse instanceof DiscreteSet) {
            return ((DiscreteSet) universeOfDiscourse).getElements()
                    .stream()
                    .mapToDouble(this::getMembershipDegree)
                    .sum();
        } else {
            return membershipFunction.getArea();
        }
    }

    public ClassicSet getSupport() {
        if (universeOfDiscourse instanceof DiscreteSet) {
            List<Double> supportElements = ((DiscreteSet) universeOfDiscourse).getElements()
                    .stream()
                    .filter(val -> getMembershipDegree(val) > 0)
                    .collect(Collectors.toList());
            return new DiscreteSet(supportElements);
        } else {
            return new ContinuousSet(membershipFunction.getLeftBoundary(), membershipFunction.getRightBoundary());
        }

    }

    public double getDegreeOfFuzziness(List<Double> databaseValues) {
        System.out.println("elementy bazy: " + databaseValues.size());
        System.out.println("licznik: " + getSupport(databaseValues).getSize());
        return getSupport(databaseValues).getSize() / databaseValues.size();
    }

    public ClassicSet getSupport(List<Double> databaseValues) {
        List<Double> list = databaseValues.stream()
                .filter(val -> getMembershipDegree(val) > 0)
                .collect(Collectors.toList());
        return new DiscreteSet(list);
    }

    public ClassicSet getAlphaCut(double alpha) {
        ClassicSet support = getSupport();
        if (support instanceof DiscreteSet) {
            List<Double> alphaCut = ((DiscreteSet) support).getElements()
                    .stream()
                    .filter(val -> getMembershipDegree(val) >= alpha)
                    .collect(Collectors.toList());
            return new DiscreteSet(alphaCut);
        } else {
            throw new UnsupportedOperationException("Not implemented yet.");
        }
    }

    public double getDegreeOfFuzziness() {

        return getSupport().getSize() / universeOfDiscourse.getSize();
    }

    public FuzzySet complement() {
        return null;
        //return new FuzzySet(x -> 1 - membershipFunction.getValue(x), universeOfDiscourse);
    }

    public FuzzySet union(FuzzySet otherSet) {
        return null;
       // return new FuzzySet(x -> Math.max(membershipFunction.getValue(x), otherSet.membershipFunction.getValue(x)), universeOfDiscourse);
    }

    public FuzzySet intersect(FuzzySet otherSet) {
        return null;
      //  return new FuzzySet(x -> Math.min(membershipFunction.getValue(x), otherSet.membershipFunction.getValue(x)), universeOfDiscourse);
    }

    public boolean isEmpty() {
        return getSupport().isEmpty();
    }


    public double getCardinality(List<Double> databaseValues) {
        return databaseValues.stream()
                .mapToDouble(this::getMembershipDegree)
                .sum();
    }
}
