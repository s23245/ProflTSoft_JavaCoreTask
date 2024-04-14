package org.example.service;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.example.model.Hero;
import org.example.model.Item;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * The StatisticsCount class is responsible for maintaining and updating statistics related to Heroes.
 * It uses a HashMap to store the statistics, with the attribute value as the key and the count as the value.
 */
public class StatisticsCount
{

    private final Map<String, AtomicInteger> statistics;


    public StatisticsCount()
    {
        this.statistics = new HashMap<>();
    }

    /**
     * Updates the statistics based on the attribute of the Hero.
     * If the attribute is not "Abilities", it increments the count of the attribute value.
     * If the attribute is "Abilities", it increments the count of each ability.
     *
     * @param hero      The Hero whose attributes are to be processed.
     * @param attribute The attribute to be processed.
     */
    public synchronized void upgradeStatistics(Hero hero, String attribute)
    {
        if(!attribute.equals("Abilities"))
        {
            String attributeValue = checkAttribute(hero, attribute);
            statistics.computeIfAbsent(attributeValue, k -> new AtomicInteger(0)).incrementAndGet();

        }
        else
        {
            String[] abilities = hero.getAbilities().split(", ");
            Arrays.stream(abilities).forEach(
                    ability ->
                            statistics.computeIfAbsent(ability, k -> new AtomicInteger(0)).incrementAndGet());

        }

    }

    /**
     * Checks the attribute of the Hero and returns its value.
     * Throws an IllegalArgumentException if the attribute is invalid.
     *
     * @param hero      The Hero whose attribute is to be checked.
     * @param attribute The attribute to be checked.
     * @return The value of the attribute.
     */
    private String checkAttribute(Hero hero, String attribute)
    {
        return switch (attribute)
        {
            case "HeroClassName" -> hero.getHeroClassName();
            case "HeroLevel" -> String.valueOf(hero.getHeroLevel());
            case "ManaAmount" -> String.valueOf(hero.getManaAmount());
            case "HeroMainElement" -> hero.getHeroMainElement();
            default -> throw new IllegalArgumentException("Invalid attribute: " + attribute);
        };
    }

    /**
     * Returns the statistics as a List of Items, sorted in descending order of count.
     * Each Item represents an attribute value and its count.
     *
     * @return The statistics as a List of Items.
     */
    @JsonAnyGetter
    @JacksonXmlProperty(localName = "item")
    public List<Item> getStatistics()
    {
        return statistics.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().get(), e1.getValue().get()))
                .map(entry -> new Item(entry.getKey(), entry.getValue().get()))
                .collect(Collectors.toList());
    }
}
