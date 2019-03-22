import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatLineIndexStruct {
    int statWord;
    List<Pair<Integer, Integer>> indexesOnLines = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatLineIndexStruct that = (StatLineIndexStruct) o;
        return statWord == that.statWord &&
                Objects.equals(indexesOnLines, that.indexesOnLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statWord, indexesOnLines);
    }

}
