package com.github.millasa;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ast.GherkinDocument;

import java.io.IOException;

public class GherkinParser {
    public GherkinDocument parse(String filePath) throws IOException {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        String file = FileReader.readFile(filePath);

        return parser.parse(file);
    }
}
