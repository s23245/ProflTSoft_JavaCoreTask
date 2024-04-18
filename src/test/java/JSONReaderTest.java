
import org.example.service.JSONReader;
import org.example.service.StatisticsCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class JSONReaderTest
{

    private JSONReader jsonReader;
    private StatisticsCount statisticsCountMock;

    @BeforeEach
    public void setup() {
        statisticsCountMock = Mockito.mock(StatisticsCount.class);
        jsonReader = new JSONReader(statisticsCountMock);
    }

    @Test
    public void testReadValidFiles()
    {
        String directoryPath = "src/test/java/";
        String attribute = "HeroClassName";

        jsonReader.readFiles(directoryPath, attribute);

        verify(statisticsCountMock, times(2)).upgradeStatistics(any(), eq(attribute));
    }

    @Test
    public void testReadInvalidDirectory()
    {
        String directoryPath = "invalid_directory";
        String attribute = "HeroClassName";

        jsonReader.readFiles(directoryPath, attribute);

        verify(statisticsCountMock, times(0)).upgradeStatistics(any(), any());
    }


}
