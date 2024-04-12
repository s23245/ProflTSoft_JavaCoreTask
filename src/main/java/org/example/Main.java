package org.example;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Hero;
import org.example.enums.HeroElements;
import org.example.service.StatisticsCount;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args)
    {
        StatisticsCount statisticsCount = new StatisticsCount();

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);

       try{
           Path directoryPath = Path.of(args[0]);
           Files.list(directoryPath)
                   .filter(file -> file.toString().endsWith(".json"))
                   .forEach(jsonFile ->
                   {
                       try
                       {
                           Hero[] heroes = jsonMapper.readValue(jsonFile.toFile(), Hero[].class);

                           for(Hero hero : heroes)
                               statisticsCount.upgradeStatistics(hero, args[1]);


                       }
                          catch (Exception e)
                          {
                            e.printStackTrace();
                          }
                   });
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }



        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            xmlMapper.writeValue(new File("./result/statistics_by_"+ args[1]+ ".xml"), statisticsCount.getStatistics());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}