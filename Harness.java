import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Harness {
  public static void main(String[] args) {
    List<Solution> solutions = new ArrayList<>();
    solutions.add(new AlecSolution());
    solutions.add(new SortSolution());

    for (Solution solution : solutions) {
      Harness harness = new Harness(solution, 10);
      harness.checkAllPermutations();
      harness.printReport();
    }

    for (Solution solution : solutions) {
      Harness harness = new Harness(solution, 100);
      harness.checkRandomPermutations(10_000);
      harness.printReport();
    }
  }

  private final Solution solution;
  private final int arrayLength;
  private final int[] timesWrong;
  private final long[] sumOfSquaredDifferences;
  private final List<Integer> originals;

  private int comparisons;
  private int permutations;

  private final Comparator<Integer> comparator = (a, b) -> {
    comparisons++;
    return -Integer.compare(a, b);
  };

  private Harness(Solution solution, int arrayLength) {
    this.solution = solution;
    this.arrayLength = arrayLength;
    this.timesWrong = new int[arrayLength];
    this.sumOfSquaredDifferences = new long[arrayLength];

    originals = new ArrayList<>(arrayLength);
    for (int i = 0; i < arrayLength; i++) {
      originals.add(i);
    }
  }

  private void checkRandomPermutations(int trials) {
    Random random = new Random(0);

    for (int i = 0; i < trials; i++) {
      Collections.sort(originals);
      Collections.shuffle(originals, random);
      measurePermutation(originals);
    }
  }

  private void checkAllPermutations() {
    permute(originals, 0);
  }

  private void permute(List<Integer> list, int k){
    for(int i = k; i < list.size(); i++){
      Collections.swap(list, i, k);
      permute(list, k+1);
      Collections.swap(list, k, i);
    }

    if (k == list.size() - 1) {
      measurePermutation(list);
    }
  }

  private void measurePermutation(List<Integer> permutation) {
    permutations++;
    List<Integer> sorted = solution.pizzaSort(new ArrayList<>(permutation), comparator);

    for (int i = 0; i < permutation.size(); i++) {
      int actual = sorted.get(i);
      int expected = arrayLength - i - 1;
      if (actual != expected) {
        int difference = actual - expected;
        timesWrong[i]++;
        sumOfSquaredDifferences[i] += (difference * difference);
      }
    }
  }

  private void printReport() {
    System.out.printf("%s%n", solution.getClass().getSimpleName());
    for (int i = 0; i < arrayLength; i++) {
      double standardDeviation = Math.sqrt((double) sumOfSquaredDifferences[i] / permutations);
      System.out.printf("Got %dth place wrong %.2f%% of the time (σ=%.2f)%n",
          i, ((double)timesWrong[i]/permutations)*100, standardDeviation);
    }
    System.out.printf("%.2f comparisons per element (%d total comparisons)%n",
        ((double) comparisons/permutations/arrayLength), comparisons);
    System.out.println();
  }
}
