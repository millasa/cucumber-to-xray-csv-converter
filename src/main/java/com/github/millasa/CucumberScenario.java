package com.github.millasa;

import java.util.List;

public class CucumberScenario {

    public String type;
    public String feature;
    public String name;
    public List<String> tags;
    public List<String> steps;
    public List<String> examples;

    public CucumberScenario(String type, String feature, String name, List<String> tags, List<String> steps, List<String> examples) {
        this.type = type;
        this.feature = feature;
        this.name = name;
        this.tags = tags;
        this.steps = steps;
        this.examples = examples;
    }
}
