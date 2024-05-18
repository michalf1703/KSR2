package ksr2.ksrproject2.logic.summarization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.awt.Label;
import java.util.List;

@AllArgsConstructor
@Getter
public class LinguisticVariable {
    private String name;
    private List<Label> labels;
}
