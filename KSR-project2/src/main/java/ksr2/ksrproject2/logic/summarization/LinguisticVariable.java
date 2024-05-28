package ksr2.ksrproject2.logic.summarization;

import ksr2.ksrproject2.logic.calculation.sets.ContinuousSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class LinguisticVariable {
    private String name;
    private List<ksr2.ksrproject2.logic.summarization.Label> labels;
    private ContinuousSet universeOfDiscourse;
}