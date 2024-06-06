package ksr2.ksrproject2.view.model;


import lombok.Getter;

@Getter
public class MultiSubjectSummaryDTO {
    private final int formNumber;
    private final String textValue;
    private final double degreeOfTruth_T1;

    public MultiSubjectSummaryDTO(Integer formNumber,String textValue,
                                   double degreeOfTruth_T1) {
        this.formNumber = formNumber;
        this.textValue = textValue;
        this.degreeOfTruth_T1 = degreeOfTruth_T1;

    }

}