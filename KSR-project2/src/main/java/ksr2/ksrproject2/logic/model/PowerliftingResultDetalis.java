package ksr2.ksrproject2.logic.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PowerliftingResultDetalis {
    private long id;
    private double age;
    private double bodyWeight;
    private double squatStrenghtLevel;
    private double benchpressStrenghtLevel;
    private double deadliftStrenghtLevel;
    private double totalPowerliftStrenghtLevel;
    private double dotsLevel;
    private double wilksLevel;
    private double glossbrennerLevel;
    private double goodliftLevel;

}
