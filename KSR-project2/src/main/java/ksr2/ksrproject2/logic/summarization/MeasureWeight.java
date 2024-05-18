package ksr2.ksrproject2.logic.summarization;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class MeasureWeight {
    private Map<String,Double> weights;

    public boolean areCorrect() {
    return weights.values().stream().mapToDouble(Double::doubleValue).sum() == 1.0;
    }
}
