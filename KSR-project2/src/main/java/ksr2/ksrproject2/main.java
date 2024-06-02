package ksr2.ksrproject2;

import ksr2.ksrproject2.logic.utilis.DetalisReader;
import ksr2.ksrproject2.logic.model.PowerliftingResult;

import java.util.List;

class Main {
    public static void main(String[] args) {
        List<PowerliftingResult> results = DetalisReader.readData();

        for (PowerliftingResult result : results) {
            System.out.println(result.toString());
        }
    }
}