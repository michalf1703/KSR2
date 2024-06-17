package ksr2.ksrproject2.logic.calculation.sets;


import lombok.Getter;

@Getter
public class ContinuousSet implements ClassicSet{
    private final double startOfUniverseOFDiscourse;
    private final double endOfUniverseOFDiscourse;

    public ContinuousSet(double startOfUniverseOFDiscourse, double endOfUniverseOFDiscourse) {
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

    public ContinuousSet complement() {
        return new ContinuousSet(endOfUniverseOFDiscourse, startOfUniverseOFDiscourse);
    }

    public ContinuousSet intersection(ContinuousSet other) {
        if (startOfUniverseOFDiscourse > other.endOfUniverseOFDiscourse || endOfUniverseOFDiscourse < other.startOfUniverseOFDiscourse)
            throw new IllegalArgumentException("Sets do not intersect");
        return new ContinuousSet(Math.max(startOfUniverseOFDiscourse, other.startOfUniverseOFDiscourse),
                Math.min(endOfUniverseOFDiscourse, other.endOfUniverseOFDiscourse));
    }

    public ContinuousSet union(ContinuousSet other) {
        if (startOfUniverseOFDiscourse > other.endOfUniverseOFDiscourse || endOfUniverseOFDiscourse < other.startOfUniverseOFDiscourse)
            throw new IllegalArgumentException("Sets do not intersect");
        return new ContinuousSet(Math.min(startOfUniverseOFDiscourse, other.startOfUniverseOFDiscourse),
                Math.max(endOfUniverseOFDiscourse, other.endOfUniverseOFDiscourse));
    }

}
