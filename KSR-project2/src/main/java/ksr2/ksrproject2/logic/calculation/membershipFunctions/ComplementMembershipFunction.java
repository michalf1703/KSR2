package ksr2.ksrproject2.logic.calculation.membershipFunctions;

public class ComplementMembershipFunction implements MembershipFunction{
    private final MembershipFunction function;

    public ComplementMembershipFunction(MembershipFunction function) {
        this.function = function;
    }

    @Override
    public double getValue(double x) {
        return 1 - function.getValue(x);
    }

    @Override
    public double getArea() {
        return 1 - function.getArea();
    }

    @Override
    public double getLeftBoundary() {
        return function.getLeftBoundary();
    }

    @Override
    public double getRightBoundary() {
        return function.getRightBoundary();
    }
}
