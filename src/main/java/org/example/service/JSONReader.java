package org.example.service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.example.model.Hero;
import org.example.model.Item;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSONReader is a class responsible for reading JSON files from a given directory and processing them.
 * It uses multithreading to process multiple files simultaneously.
 */
public class JSONReader
{
    private final StatisticsCount statisticsCount;
    private final ObjectMapper objectMapper;

    /**
     * Constructor for JSONReader.
     * @param statisticsCount An instance of StatisticsCount to keep track of statistics.
     */
    public JSONReader(StatisticsCount statisticsCount)
    {
        this.statisticsCount = statisticsCount;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Reads all JSON files in a given directory and processes them.
     * @param directoryPath The path to the directory containing the JSON files.
     * @param attribute The attribute to be processed in the JSON files.
     */
    public void readFiles(String directoryPath, String attribute)
    {
        File directory = new File(directoryPath);

        if (!directory.isDirectory())
        {
            System.err.println("Invalid directory: " + directoryPath);
            return;
        }

        List<Thread> threads = new ArrayList<>();

        try (var directoryStream = Files.newDirectoryStream(Path.of(directoryPath), "*.json"))
        {
            for (Path filePath : directoryStream)
            {
                System.out.println("Reading file: " + filePath);
                Thread thread = new Thread(() -> processFile(filePath.toFile(), attribute));

                threads.add(thread);
                thread.start();


            }
            joinThreads(threads);
        }
        catch (IOException e)
        {
            System.err.println("Error reading directory: " + directoryPath);
            e.printStackTrace();
        }
    }

    /**
     * Processes a single JSON file.
     * @param file The JSON file to be processed.
     * @param attribute The attribute to be processed in the JSON file.
     */
    public void processFile(File file, String attribute)
    {
        try
        {
            ObjectReader reader = objectMapper.readerFor(Hero.class);
            MappingIterator<Hero> iterator = reader.readValues(file);
            while (iterator.hasNext())
            {
                Hero hero = iterator.next();
                System.out.println("Processing: " + hero);
                statisticsCount.upgradeStatistics(hero, attribute);
            }
        }
        catch (IOException e)
        {
            System.err.println("Error processing file: " + file.getName());
            e.printStackTrace();
        }
    }

    private void joinThreads(List<Thread> threads)
    {
        for (Thread thread : threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


}