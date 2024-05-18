package ksr2.ksrproject2.logic.calculation.membershipFunctions;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class IntersectMembershipFunction implements MembershipFunction {
    private final MembershipFunction function1;
    private final MembershipFunction function2;

    public IntersectMembershipFunction(MembershipFunction function1, MembershipFunction function2) {
        this.function1 = function1;
        this.function2 = function2;
    }

    public double getValue(double x) {
        return Math.min(function1.getValue(x), function2.getValue(x));
    }

    public double getArea() {
        return Math.min(function1.getArea(), function2.getArea());
    }

    public double getLeftBoundary() {
        return Math.max(function1.getLeftBoundary(), function2.getLeftBoundary());
    }

    public double getRightBoundary() {
        return Math.min(function1.getRightBoundary(), function2.getRightBoundary());
    }
}
