package com.github.millasa;

import gherkin.ast.*;

import java.util.ArrayList;
import java.util.List;

public class ScenarioParser {

    public static List<String> getTagNames(ScenarioDefinition scenarioDefinition) {
        List<Tag> tags;
        List<String> tagNames = new ArrayList<>();

        if (scenarioDefinition.getKeyword().equals("Scenario")) {
            tags = ((Scenario)scenarioDefinition).getTags();
        }
        else {
            tags = ((ScenarioOutline)scenarioDefinition).getTags();
        }
        for (Tag tag : tags) {
            tagNames.add(tag.getName());
        }
        return tagNames;
    }

    public static List<String> getStepsWithKeywords(ScenarioDefinition scenarioDefinition){
        List<String> fullSteps = new ArrayList<>();

        for (gherkin.ast.Step step : scenarioDefinition.getSteps()) {
            fullSteps.add(String.format("%s %s", step.getKeyword().trim(), step.getText()));
        }
        return fullSteps;
    }

    public static List<String> getExamples(ScenarioDefinition scenarioDefinition) {
        List<String> fullExamples = new ArrayList<>();

        if (scenarioDefinition.getKeyword().equals("Scenario Outline")) {
            fullExamples.add("\nExamples:");
            for (Examples example : ((ScenarioOutline)scenarioDefinition).getExamples()) {
                //Print header ||cell1||cell2||cellN||
                String header = "||";
                for(TableCell examplesHeaderCell : example.getTableHeader().getCells()) {
                    header = header.concat(examplesHeaderCell.getValue()).concat("||");
                }
                fullExamples.add(header);

                //Print rows |cell1|cell2|cellN|
                for (TableRow examplesTableRow : example.getTableBody()) {
                    String row = "|";
                    for (TableCell examplesRowCell : examplesTableRow.getCells()) {
                        row = row.concat(examplesRowCell.getValue()).concat("|");
                    }
                    fullExamples.add(row);
                }
            }
        }
        return fullExamples;
    }
}
