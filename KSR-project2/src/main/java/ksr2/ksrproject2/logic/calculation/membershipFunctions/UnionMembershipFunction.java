package ksr2.ksrproject2.logic.calculation.membershipFunctions;

public class UnionMembershipFunction implements MembershipFunction{
    private final MembershipFunction function1;
    private final MembershipFunction function2;

    public UnionMembershipFunction(MembershipFunction function1, MembershipFunction function2) {
        this.function1 = function1;
        this.function2 = function2;
    }

    @Override
    public double getValue(double x) {
        return Math.max(function1.getValue(x), function2.getValue(x));
    }

    @Override
    public double getArea() {
        return function1.getArea() + function2.getArea();
    }

    @Override
    public double getLeftBoundary() {
        return Math.min(function1.getLeftBoundary(), function2.getLeftBoundary());
    }

    @Override
    public double getRightBoundary() {
        return Math.max(function1.getRightBoundary(), function2.getRightBoundary());
    }
}
