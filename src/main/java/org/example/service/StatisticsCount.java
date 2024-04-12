package org.example.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.model.Hero;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsCount
{
    private final Map<String,Integer> statistics;

    public StatisticsCount()
    {
        this.statistics = new HashMap<>();
    }

    public void upgradeStatistics(Hero hero, String attribute)
    {
        if(!attribute.equals("Abilities"))
        {
            String attributeValue = checkAttribute(hero, attribute);
            statistics.put(attributeValue, statistics.getOrDefault(attributeValue, 0) + 1);
        }
        else
        {
            String[] abilities = hero.getAbilities().split(", ");
            Arrays.stream(abilities).forEach(
                    ability -> statistics.put(ability, statistics.getOrDefault(ability, 0) + 1));
        }

    }

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

    public Map<String, Integer> getStatistics()
    {
        return sortStatistics(statistics);
    }

    private Map<String,Integer> sortStatistics(Map<String,Integer> statistics)
    {
        return statistics.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }
}
