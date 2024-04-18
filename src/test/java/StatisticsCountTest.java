import org.example.enums.HeroElements;
import org.example.model.Hero;
import org.example.model.Item;
import org.example.service.StatisticsCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsCountTest {

    private StatisticsCount statisticsCount;

    @BeforeEach
    void setUp() {
        statisticsCount = new StatisticsCount();
    }

    @Test
    void upgradeStatistics()
    {

        Hero hero = new Hero("Warrior", 1, 100, "Slash, ShieldBash", HeroElements.EARTH);
        String attribute = "HeroClassName";

        statisticsCount.upgradeStatistics(hero, attribute);


        List<Item> statistics = statisticsCount.getStatistics();
        assertEquals(1, statistics.size());
        assertEquals(new Item("Warrior", 1), statistics.get(0));
    }


}
