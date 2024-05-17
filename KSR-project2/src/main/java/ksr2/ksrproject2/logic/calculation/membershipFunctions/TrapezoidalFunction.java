package ksr2.ksrproject2.logic.calculation.membershipFunctions;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class TrapezoidalFunction implements MembershipFunction{
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public TrapezoidalFunction(double a, double b, double c, double d) {
        if (a > b || b > c || c > d)
            throw new IllegalArgumentException("a must be less than b, b must be less than c, c must be less than d");
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    @Override
    public double getValue(double x) {
        if (x >= b && x <= c) {
            return 1.0;
        } else if (x <= a || x >= d) {
            return 0.0;
        } else if (x > a && x < b) {
            return (x - a) / (b - a);
        } else {
            return (d - x) / (d - c);
        }
    }

    @Override
    public double getArea() {
        return ((d - a) + (c - b)) / 2;
    }

    @Override
    public double getLeftBoundary() {
        return a;
    }

    @Override
    public double getRightBoundary() {
        return d;
    }
}
