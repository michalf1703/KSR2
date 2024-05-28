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

    //liczba kardynalna - tak jakby moc zbioru
    //w przypadku zbioru rozmytego (sigmaCount) - liczy pole pod wykresem, liczy pole wykresu funkcji przynależności
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

//support - nośnik, zbiór klasyczny wszystkich elementów które spełniają warunek y>0,
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

//alfaCut - zbior klasyczny, uogólnienie definicji nośnika , jeśli za alfa przyjmiemy 0 to bedzie nośnik
//zbiór elementów dla których funkcja przynależności jest miX > alfa
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

    //stopień rozmytości - określa jak bardzo zbiór jest rozmyty w kontekście swojego uniwersum,
    //stopień rozmytości potrzebny do T2 (stopień rozmycia)
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

//miało być sprawdzenie czy jest pusty
    public boolean isEmpty() {
        return getSupport().isEmpty();
    }

    public double getDegreeOfFuzziness(Double aDouble) {
        return getMembershipDegree(aDouble);
    }

    public double getCardinality(Double aDouble) {
        return getMembershipDegree(aDouble);
    }
}
