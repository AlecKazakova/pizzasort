import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Does a pairwise tournament into promoted elements and losers, then sorts the promoted elements.
 * This requires O(N log N) comparisons.
 */
class AlecSolution implements Solution {
  @Override public <T> List<T> pizzaSort(List<T> inputs, Comparator<T> comparator) {
    List<T> promoted = new ArrayList<>(inputs.size()/2);
    List<T> losers = new ArrayList<>(inputs.size()/2);
    for (int i = 0; i < inputs.size(); i+=2) {
      T a = inputs.get(i);
      T b = inputs.get(i + 1);
      if (comparator.compare(a, b) < 0) {
        promoted.add(a);
        losers.add(b);
      } else {
        promoted.add(b);
        losers.add(a);
      }
    }

    Collections.sort(promoted, comparator);

    List<T> result = new ArrayList<>();
    result.addAll(promoted);
    result.addAll(losers);
    return result;
  }
}
