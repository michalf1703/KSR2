package ksr2.ksrproject2.logic.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
public class PowerliftingResult {
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

    public PowerliftingResult(double age, double bodyWeight, double squatStrenghtLevel, double benchpressStrenghtLevel, double deadliftStrenghtLevel, double totalPowerliftStrenghtLevel, double dotsLevel, double wilksLevel, double glossbrennerLevel, double goodliftLevel) {
    }
}
