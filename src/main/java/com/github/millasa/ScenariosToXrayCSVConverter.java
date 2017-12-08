package com.github.millasa;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScenariosToXrayCSVConverter {

    public static String generateCSVFileName(Date currentDateTime) {
        String fileName = new SimpleDateFormat("'cucumber_scenarios_'yyyyMMdd_HHmm'.csv'").format(currentDateTime);
        return fileName;
    }

    public static String convertScenariosToCSVFile(List<CucumberScenario> scenarios, String fileName) throws IOException {
        final String[] HEADERS = { "ID", "Feature", "Summary", "Description", "Steps", "Status", "File" };
        File csvFile = new File(fileName);

        try (FileWriter csvFileWriter = new FileWriter(csvFile)) {
            CSVPrinter printer = new CSVPrinter(csvFileWriter, CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter(';'));
            int testID = 0;

            for (CucumberScenario scenario : scenarios) {
                List<String> testDetails = new ArrayList<>();
                testDetails.add(String.valueOf(++testID));
                testDetails.add(scenario.feature);
                testDetails.add(scenario.name);
                String tagsList = String.join("\n", scenario.tags);
                String scenarioTypeAndName = String.format("%s: %s\n", scenario.type, scenario.name);
                String stepsList = String.join("\n", scenario.steps);
                String examplesList = "";
                if (scenario.type.equals("Scenario Outline")) {
                    examplesList = String.join("\n", scenario.examples);
                }
                testDetails.add(String.join("\n", tagsList, scenarioTypeAndName, stepsList, examplesList));
                testDetails.add("Dummy step");
                testDetails.add("In Dev");

                printer.printRecord(testDetails);
            }
            return csvFile.getAbsolutePath();
        }
    }
}
