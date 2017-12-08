package com.github.millasa;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FolderFilesToCucumberScenariosConverter {

    public static List<CucumberScenario> convertFilesInDirToCucumberScenarios(File dir) throws IOException {
        GherkinParser gherkinParser = new GherkinParser();
        List<CucumberScenario> scenarios = new ArrayList<>();
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (FilenameUtils.getExtension(child.getName()).equals("feature")) {
                    GherkinDocument gherkinDocument = gherkinParser.parse(child.getPath());
                    List<ScenarioDefinition> scenarioDefinitions = gherkinDocument.getFeature().getChildren();

                    for (ScenarioDefinition scenarioDefinition : scenarioDefinitions) {
                        CucumberScenario scenario = new CucumberScenario(
                                scenarioDefinition.getKeyword(),
                                gherkinDocument.getFeature().getName(),
                                scenarioDefinition.getName(),
                                ScenarioParser.getTagNames(scenarioDefinition),
                                ScenarioParser.getStepsWithKeywords(scenarioDefinition),
                                ScenarioParser.getExamples(scenarioDefinition));
                        scenarios.add(scenario);
                    }
                }
            }
        }
        return scenarios;
    }
}
