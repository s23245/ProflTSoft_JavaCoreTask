package org.example.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.example.enums.HeroElements;

/**
 * The Hero class represents a main class for project.
 * It includes properties such as class name, level, mana amount, abilities, and main element.
 * It also includes methods to get and set these properties, with validation checks.
 */
@JsonAutoDetect
@JsonPropertyOrder({"HeroClassName", "HeroLevel", "ManaAmount", "Abilities", "HeroMainElement"})
public class Hero
{

    private String HeroClassName;

    private int HeroLevel;

    private int ManaAmount;

    private String Abilities;

    private HeroElements HeroMainElement;


    public Hero()
    {
    }

    /**
     * Constructor for Hero with all properties.
     * @param HeroClassName The class name of the hero.
     * @param HeroLevel The level of the hero.
     * @param ManaAmount The mana amount of the hero.
     * @param Abilities The abilities of the hero.
     * @param HeroMainElement The main element of the hero.
     */
    @JsonCreator
    public Hero(@JsonProperty("heroClassName") String HeroClassName,
                @JsonProperty("heroLevel") int HeroLevel,
                @JsonProperty("manaAmount") int ManaAmount,
                @JsonProperty("abilities") String Abilities,
                @JsonProperty("heroMainElement") HeroElements HeroMainElement)
    {
        setHeroClassName(HeroClassName);
        setHeroLevel(HeroLevel);
        setManaAmount(ManaAmount);
        setAbilities(Abilities);
        setHeroMainElement(HeroMainElement);
    }


    public String getHeroClassName() {
        return HeroClassName;
    }


    public void setHeroClassName(String heroClassName)
    {
        if(heroClassName == null || heroClassName.isEmpty())
        {
            throw new IllegalArgumentException("Hero class name cannot be null.");
        }
        if(heroClassName.length() < 3)
        {
            throw new IllegalArgumentException("Hero class name must be at least 3 characters long.");
        }

        this.HeroClassName = heroClassName;
    }


    public int getHeroLevel() {
        return HeroLevel;
    }


    public void setHeroLevel(int heroLevel)
    {
        if(heroLevel < 1)
        {
            throw new IllegalArgumentException("Hero level must be at least 1.");
        }
        this.HeroLevel = heroLevel;
    }

    public int getManaAmount() {
        return ManaAmount;
    }


    public void setManaAmount(int manaAmount)
    {
        if(manaAmount < 0)
        {
            throw new IllegalArgumentException("Mana amount cannot be negative.");
        }
        this.ManaAmount = manaAmount;
    }


    public String getAbilities() {
        return Abilities;
    }


    public void setAbilities(String abilities)
    {
        if(abilities == null || abilities.isEmpty())
        {
            throw new IllegalArgumentException("Abilities cannot be null or empty.");
        }
        if(abilities.length() < 5)
        {
            throw new IllegalArgumentException("Abilities must be at least 10 characters long.");
        }
        this.Abilities = abilities;
    }


    public String getHeroMainElement() {
        return HeroMainElement.toString();
    }


    public void setHeroMainElement(HeroElements heroMainElement)
    {
        if(heroMainElement == null)
        {
            throw new IllegalArgumentException("Hero main element cannot be null.");
        }
        this.HeroMainElement = heroMainElement;
    }

    /**
     * Overrides the toString method to provide a string representation of the Hero object.
     * @return A string representation of the Hero object.
     */
    @Override
    public String toString() {
        return "{" +
                "HeroClassName='" + HeroClassName + '\'' +
                ", HeroLevel=" + HeroLevel +
                ", ManaAmount=" + ManaAmount +
                ", Abilities='" + Abilities + '\'' +
                ", HeroMainElement=" + HeroMainElement +
                '}' + '\n';
    }
}