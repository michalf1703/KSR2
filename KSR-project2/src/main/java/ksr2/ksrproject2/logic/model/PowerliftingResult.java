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
    private String weightClass;

    public PowerliftingResult(double age, double bodyWeight, double squatStrenghtLevel, double benchpressStrenghtLevel, double deadliftStrenghtLevel, double totalPowerliftStrenghtLevel, double dotsLevel, double wilksLevel, double glossbrennerLevel, double goodliftLevel) {
        this.age = age;
        this.bodyWeight = bodyWeight;
        this.squatStrenghtLevel = squatStrenghtLevel;
        this.benchpressStrenghtLevel = benchpressStrenghtLevel;
        this.deadliftStrenghtLevel = deadliftStrenghtLevel;
        this.totalPowerliftStrenghtLevel = totalPowerliftStrenghtLevel;
        this.dotsLevel = dotsLevel;
        this.wilksLevel = wilksLevel;
        this.glossbrennerLevel = glossbrennerLevel;
        this.goodliftLevel = goodliftLevel;
        if (bodyWeight > 120) {
            this.weightClass = "Weight class 120+";
        } else if (bodyWeight > 86 && bodyWeight <= 100) {
            this.weightClass = "Weight class 86-100";
        } else {
            this.weightClass = "Other weight class";
        }
    }

    @Override
    public String toString() {
        return "PowerliftingResult{" +
                "age=" + age +
                ", bodyWeight=" + bodyWeight +
                ", squatStrenghtLevel=" + squatStrenghtLevel +
                ", benchpressStrenghtLevel=" + benchpressStrenghtLevel +
                ", deadliftStrenghtLevel=" + deadliftStrenghtLevel +
                ", totalPowerliftStrenghtLevel=" + totalPowerliftStrenghtLevel +
                ", dotsLevel=" + dotsLevel +
                ", wilksLevel=" + wilksLevel +
                ", glossbrennerLevel=" + glossbrennerLevel +
                ", goodliftLevel=" + goodliftLevel +
                ", weightClass='" + weightClass + '\'' +
                '}';
    }
}
