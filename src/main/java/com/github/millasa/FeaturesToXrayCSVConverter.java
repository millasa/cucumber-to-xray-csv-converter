package com.github.millasa;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.github.millasa.FolderFilesToCucumberScenariosConverter.convertFilesInDirToCucumberScenarios;

public class FeaturesToXrayCSVConverter {

    public static void main(String[] args) throws IOException {
        if (args.length != 1){
            System.out.println("Program expects path to directory with .feature files.");
        }
        else {
            File dir = new File(args[0]);
            if (!dir.exists()) {
                System.out.printf("%s doesn't exist\n", dir.getAbsolutePath());
                return;
            }
            if (!dir.isDirectory()) {
                dir = dir.getParentFile();
            }
            if (dir.exists()) {
                List<CucumberScenario> scenarios = convertFilesInDirToCucumberScenarios(dir);
                if (scenarios.size() == 0){
                    System.out.println("No scenarios found in the directory.");
                    return;
                }
                Date currentDateTime = new Date();
                String fileName = ScenariosToXrayCSVConverter.generateCSVFileName(currentDateTime);
                String csvFilePath = ScenariosToXrayCSVConverter.convertScenariosToCSVFile(scenarios, fileName);
                System.out.printf("File %s was created successfully.", csvFilePath);
            }
            else {
                System.out.println("Invalid directory");
            }
        }
    }


}
