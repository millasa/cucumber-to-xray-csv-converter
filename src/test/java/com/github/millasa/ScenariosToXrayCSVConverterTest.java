package com.github.millasa;

import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalField;
import java.util.*;

import static com.github.millasa.ScenariosToXrayCSVConverter.generateCSVFileName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScenariosToXrayCSVConverterTest {
    @Test
    public void shouldCreateFileWithNamePassedAsArgument() throws Exception {
        String fileName = "test_file123.csv";
        List<CucumberScenario> scenarios = new ArrayList<>();

        String csvFilePath = ScenariosToXrayCSVConverter.convertScenariosToCSVFile(scenarios, fileName);
        File csvFile = new File(csvFilePath);
        assertEquals(fileName, csvFile.getName());
        assertTrue(csvFile.exists());
    }

    @Test
    public void shouldGenerateFileNameAccordingToSpecifiedFormat() {
        GregorianCalendar calendar = new GregorianCalendar(2017, 10, 25, 12, 00);
        Date currentDateTime = calendar.getTime();

        String fileName = generateCSVFileName(currentDateTime);
        assertEquals("cucumber_scenarios_20171125_1200.csv", fileName);
    }
}