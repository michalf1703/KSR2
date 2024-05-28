package ksr2.ksrproject2.view.model;


import lombok.Getter;
@Getter
public class SingleSubjectSummaryDTO {
    private final String textValue;
    private final double goodnessOfSummary;
    private final double degreeOfTruth_T1;
    private final double degreeOfImprecision_T2;
    private final double degreeOfCovering_T3;
    private final double degreeOfAppropriateness_T4;
    private final double degreeOfSummary_T5;
    private final double degreeOfQuantifierImprecision_T6;
    private final double degreeOfQuantifierCardinality_T7;
    private final double degreeOfSummarizerCardinality_T8;
    private final double degreeOfQualifierImprecision_T9;
    private final double degreeOfQualifierCardinality_T10;
    private final double lengthOfQualifier_T11;

    public SingleSubjectSummaryDTO(String textValue,
                                   double goodnessOfSummary,
                                   double degreeOfTruth_T1,
                                   double degreeOfImprecision_T2,
                                   double degreeOfCovering_T3,
                                   double degreeOfAppropriateness_T4,
                                   double degreeOfSummary_T5,
                                   double degreeOfQuantifierImprecision_T6,
                                   double degreeOfQuantifierCardinality_T7,
                                   double degreeOfSummarizerCardinality_T8,
                                   double degreeOfQualifierImprecision_T9,
                                   double degreeOfQualifierCardinality_T10,
                                   double lengthOfQualifier_T11) {
        this.textValue = textValue;
        this.goodnessOfSummary = goodnessOfSummary;
        this.degreeOfTruth_T1 = degreeOfTruth_T1;
        this.degreeOfImprecision_T2 = degreeOfImprecision_T2;
        this.degreeOfCovering_T3 = degreeOfCovering_T3;
        this.degreeOfAppropriateness_T4 = degreeOfAppropriateness_T4;
        this.degreeOfSummary_T5 = degreeOfSummary_T5;
        this.degreeOfQuantifierImprecision_T6 = degreeOfQuantifierImprecision_T6;
        this.degreeOfQuantifierCardinality_T7 = degreeOfQuantifierCardinality_T7;
        this.degreeOfSummarizerCardinality_T8 = degreeOfSummarizerCardinality_T8;
        this.degreeOfQualifierImprecision_T9 = degreeOfQualifierImprecision_T9;
        this.degreeOfQualifierCardinality_T10 = degreeOfQualifierCardinality_T10;
        this.lengthOfQualifier_T11 = lengthOfQualifier_T11;
    }

}