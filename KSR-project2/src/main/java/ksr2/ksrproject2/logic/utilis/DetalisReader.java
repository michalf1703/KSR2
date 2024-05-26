package ksr2.ksrproject2.logic.utilis;

import ksr2.ksrproject2.logic.model.PowerliftingResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetalisReader {
    public static List<PowerliftingResult> readData() {

        List<PowerliftingResult> powerliftingResults = new ArrayList<>();
        try {
            InputStream inputStream = DetalisReader.class.getResourceAsStream("/data.csv");
            if (inputStream == null) {
                throw new RuntimeException("File not found");
            }
            BufferedReader bufferedReader = new BufferedReader(new java.io.InputStreamReader(inputStream));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                    String[] data = line.split(";");

                    double age = Double.parseDouble(data[0]);
                    double bodyWeight = Double.parseDouble(data[1]);
                    double squatStrenghtLevel = Double.parseDouble(data[2]);
                    double benchpressStrenghtLevel = Double.parseDouble(data[3]);
                    double deadliftStrenghtLevel = Double.parseDouble(data[4]);
                    double totalPowerliftStrenghtLevel = Double.parseDouble(data[5]);
                    double dotsLevel = Double.parseDouble(data[6]);
                    double wilksLevel = Double.parseDouble(data[7]);
                    double glossbrennerLevel = Double.parseDouble(data[8]);
                    double goodliftLevel = Double.parseDouble(data[9]);

                    PowerliftingResult powerliftingDetalis = new PowerliftingResult(age, bodyWeight, squatStrenghtLevel, benchpressStrenghtLevel, deadliftStrenghtLevel, totalPowerliftStrenghtLevel, dotsLevel, wilksLevel, glossbrennerLevel, goodliftLevel);

                    powerliftingResults.add(powerliftingDetalis);
                }

                bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return powerliftingResults;
    }


}
