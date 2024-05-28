package ksr2.ksrproject2.logic.summarization;


import ksr2.ksrproject2.logic.calculation.sets.DiscreteSet;
import ksr2.ksrproject2.logic.calculation.sets.FuzzySet;
import ksr2.ksrproject2.logic.calculation.sets.ClassicSet;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Quantifier {
    private String name;
    private FuzzySet fuzzySet;

    // wypukły jest wtedy gdy każdy jego przekrój alfa bedzie wypukły, czyli nie ma dziury
  /*  public boolean isConvex() {
        // Sprawdzenie czy każdy alfa-cut jest spójny
        for (double alpha = 0.0; alpha <= 1.0; alpha += 0.1) {
            ClassicSet alphaCut = getAlphaCut(alpha);
            if (!alphaCut.isConvex()) {
                return false; // Jeśli przynajmniej jeden alfa-cut nie jest spójny, zwracamy false
            }
        }

        return true; // Jeśli każdy alfa-cut jest spójny, zwracamy true
    }
    */

    // zbior rozmyty jest normalny kiedy wysokość wynosi 1
 /*   public boolean isNormal() {
        return fuzzySet.getHeight() == 1;
    }
*/
    //wysokość zbioru rozmytego to najwyższa wartość funkcji przynależności
   /*
    public double getHeight() {
        if (fuzzySet.getUniverseOfDiscourse() instanceof DiscreteSet) {
            return ((DiscreteSet) fuzzySet.getUniverseOfDiscourse()).getElements()
                    .stream()
                    .mapToDouble(this::getMembershipDegree)
                    .max()
                    .orElse(0);
        } else {
            throw new UnsupportedOperationException("Not implemented yet.");
        }
    }
*/

    public String getName() {
        return name;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

}
