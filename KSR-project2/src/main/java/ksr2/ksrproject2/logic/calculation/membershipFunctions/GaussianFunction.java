package ksr2.ksrproject2.logic.calculation.membershipFunctions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class GaussianFunction implements MembershipFunction{
    private final double centreMax;
    private final double standardDeviation;
    private final double leftLimit;
    private final double rightLimit;

    public GaussianFunction(double centreMax, double standardDeviation, double leftLimit, double rightLimit) {
        this.centreMax = centreMax;
        this.standardDeviation = standardDeviation;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    @Override
    public double getValue(double x) {
        if (x < leftLimit || x > rightLimit) {
            return 0;
        }
        double exponent = -1 * Math.pow((x - centreMax), 2) / Math.pow(2 * standardDeviation, 2);
        return Math.exp(exponent);
    }

    @Override
    public double getArea() {
        int numSteps = 1000; 
        double stepSize = (rightLimit - leftLimit) / numSteps;
        double area = 0.0;

        for (int i = 0; i < numSteps; i++) {
            double x = leftLimit + i * stepSize;
            area += getValue(x) * stepSize;
        }

        return area;
    }

    @Override
    public double getLeftBoundary() {
        return leftLimit;
    }

    @Override
    public double getRightBoundary() {
        return rightLimit;
    }
}