package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlmanacRules {
    public List<Rule> seedToSoil;
    public List<Rule> soilToFert;
    public List<Rule> fertToWater;
    public List<Rule> waterToLight;
    public List<Rule> lightToTemp;
    public List<Rule> tempToHumid;
    public List<Rule> humidToLoc;

    // passed a scanner with the seeds already removed
    public AlmanacRules(Scanner sc) {
        initLists();

        List<String> seedToSoilGroup = getNextGroup(sc);
        for (String line : seedToSoilGroup) {
            seedToSoil.add(new Rule(line));
        }
        List<String> soilToFertGroup = getNextGroup(sc);
        for (String line : soilToFertGroup) {
            soilToFert.add(new Rule(line));
        }
        List<String> fertToWaterGroup = getNextGroup(sc);
        for (String line : fertToWaterGroup) {
            fertToWater.add(new Rule(line));
        }
        List<String> waterToLightGroup = getNextGroup(sc);
        for (String line : waterToLightGroup) {
            waterToLight.add(new Rule(line));
        }
        List<String> lightToTempGroup = getNextGroup(sc);
        for (String line : lightToTempGroup) {
            lightToTemp.add(new Rule(line));
        }
        List<String> tempToHumidGroup = getNextGroup(sc);
        for (String line : tempToHumidGroup) {
            tempToHumid.add(new Rule(line));
        }
        List<String> humidToLocGroup = getNextGroup(sc);
        for (String line : humidToLocGroup) {
            humidToLoc.add(new Rule(line));
        }
    }

    private void initLists() {
        seedToSoil = new ArrayList<>();
        soilToFert = new ArrayList<>();
        fertToWater = new ArrayList<>();
        waterToLight = new ArrayList<>();
        lightToTemp = new ArrayList<>();
        tempToHumid = new ArrayList<>();
        humidToLoc = new ArrayList<>();
    }

    private List<String> getNextGroup(Scanner sc) {
        List<String> group = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isBlank()) {
                return group;
            } else if (!Character.isAlphabetic(line.charAt(0))) {
                group.add(line);
            }
        }
        return group;
    }
}
