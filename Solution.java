import java.util.Comparator;
import java.util.List;

public interface Solution {
  <T> List<T> pizzaSort(List<T> inputs, Comparator<T> comparator);
}
