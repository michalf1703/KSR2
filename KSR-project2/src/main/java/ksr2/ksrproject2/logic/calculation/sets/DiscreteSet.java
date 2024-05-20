package ksr2.ksrproject2.logic.calculation.sets;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiscreteSet implements ClassicSet {
    private final List<Double> elements;

    public DiscreteSet(List<Double> elements) {
        if (elements.isEmpty())
            throw new IllegalArgumentException("Set cannot be empty");
        this.elements = elements;
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public double getSize() {
        return elements.size();
    }

    // Dopełnienie zbioru dyskretnego
    public DiscreteSet complement(double startOfUniverseOFDiscourse, double endOfUniverseOFDiscourse) {
        if (startOfUniverseOFDiscourse > endOfUniverseOFDiscourse)
            throw new IllegalArgumentException("Start of universe of discourse must be less than end of universe of discourse");
        List<Double> complementElements = new ArrayList<>();
        for (double i = startOfUniverseOFDiscourse; i <= endOfUniverseOFDiscourse; i++) {
            if (!elements.contains(i)) {
                complementElements.add(i);
            }
        }
        return new DiscreteSet(complementElements);
    }

    // Suma zbiorów dyskretnych
    public DiscreteSet union(DiscreteSet otherSet) {
        List<Double> unionElements = new ArrayList<>(elements);
        for (double element : otherSet.getElements()) {
            if (!unionElements.contains(element)) {
                unionElements.add(element);
            }
        }
        return new DiscreteSet(unionElements);
    }

    // Część wspólna zbiorów dyskretnych
    public DiscreteSet intersection(DiscreteSet otherSet) {
        List<Double> intersectionElements = new ArrayList<>();
        for (double element : elements) {
            if (otherSet.getElements().contains(element)) {
                intersectionElements.add(element);
            }
        }
        return new DiscreteSet(intersectionElements);
    }
}
