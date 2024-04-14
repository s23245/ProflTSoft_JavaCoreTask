package org.example.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Item;

import java.io.File;
import java.util.List;

/**
 * The XMLWriter class is responsible for writing the statistics to an XML file.
 * It uses the Jackson library to serialize the statistics to XML format.
 */
public class XMLWriter
{
    private final String FILE_NAME = "./result/statistics_by_";

    /**
     * Writes the statistics to an XML file.
     * The file name is determined by the argument provided.
     * The statistics are serialized to XML format using the Jackson library.
     *
     * @param arg The argument that determines the file name.
     * @param statistics The statistics to be written to the file.
     */
    public void writeFile(String arg, List<Item> statistics)
    {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println("Writing to file: " + FILE_NAME + arg + ".xml");
        try
        {
            xmlMapper.writeValue(new File(FILE_NAME + arg+ ".xml"), statistics);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
