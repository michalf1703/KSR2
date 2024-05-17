package ksr2.ksrproject2.logic.calculation.sets;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ContinousSet implements ClassicSet{
    private final double startOfUniverseOFDiscourse;
    private final double endOfUniverseOFDiscourse;

    public ContinousSet(double startOfUniverseOFDiscourse, double endOfUniverseOFDiscourse) {
        if (startOfUniverseOFDiscourse > endOfUniverseOFDiscourse)
            throw new IllegalArgumentException("Start of universe of discourse must be less than end of universe of discourse");
        this.startOfUniverseOFDiscourse = startOfUniverseOFDiscourse;
        this.endOfUniverseOFDiscourse = endOfUniverseOFDiscourse;
    }

    @Override
    public boolean isEmpty() {
        return startOfUniverseOFDiscourse < endOfUniverseOFDiscourse;
    }

    @Override
    public double getSize() {
        return endOfUniverseOFDiscourse - startOfUniverseOFDiscourse;
    }

    //dopełnienie zbioru
    public ContinousSet complement() {
        return new ContinousSet(endOfUniverseOFDiscourse, startOfUniverseOFDiscourse);
    }

    //cześć wspólna zbiorów
    public ContinousSet intersection(ContinousSet other) {
        if (startOfUniverseOFDiscourse > other.endOfUniverseOFDiscourse || endOfUniverseOFDiscourse < other.startOfUniverseOFDiscourse)
            throw new IllegalArgumentException("Sets do not intersect");
        return new ContinousSet(Math.max(startOfUniverseOFDiscourse, other.startOfUniverseOFDiscourse),
                Math.min(endOfUniverseOFDiscourse, other.endOfUniverseOFDiscourse));
    }

    //suma zbiorów
    public ContinousSet union(ContinousSet other) {
        if (startOfUniverseOFDiscourse > other.endOfUniverseOFDiscourse || endOfUniverseOFDiscourse < other.startOfUniverseOFDiscourse)
            throw new IllegalArgumentException("Sets do not intersect");
        return new ContinousSet(Math.min(startOfUniverseOFDiscourse, other.startOfUniverseOFDiscourse),
                Math.max(endOfUniverseOFDiscourse, other.endOfUniverseOFDiscourse));
    }


}
