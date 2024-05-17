package ksr2.ksrproject2.logic.calculation.membershipFunctions;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class TriangularFunction implements MembershipFunction{
    private final double a;
    private final double b;
    private final double c;

    public TriangularFunction(double a, double b, double c) {
        if (a > b || b > c)
            throw new IllegalArgumentException("a must be less than b, b must be less than c");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getValue(double x) {
        if (x >= a && x <= c) {
            if (x <= b) {
                return (x - a) / (b - a);
            } else {
                return (c - x) / (c - b);
            }
        } else {
            return 0.0;
        }
    }

    @Override
    public double getArea() {
        return (c - a) / 2;
    }

    @Override
    public double getLeftBoundary() {
        return a;
    }

    @Override
    public double getRightBoundary() {
        return c;
    }
}
