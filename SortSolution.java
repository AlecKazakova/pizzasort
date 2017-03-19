import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Just sorts. This solution requires O(N log N) comparisons. */
class SortSolution implements Solution {
  @Override public <T> List<T> pizzaSort(List<T> inputs, Comparator<T> comparator) {
    Collections.sort(inputs, comparator);
    return inputs;
  }
}
