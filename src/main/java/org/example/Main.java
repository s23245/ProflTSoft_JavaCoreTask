package org.example;
import org.example.service.JSONReader;
import org.example.service.StatisticsCount;
import org.example.service.XMLWriter;

public class Main {
    public static void main(String[] args)
    {
        StatisticsCount statisticsCount = new StatisticsCount();
        JSONReader jsonReader = new JSONReader(statisticsCount);
        XMLWriter xmlWriter = new XMLWriter();

        jsonReader.readFiles(args[0],args[1]);

        xmlWriter.writeFile(args[1],statisticsCount.getStatistics());

    }
}