import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class Pizzasort {
  private static final int ARRAY_LENGTH = 10;

  public static void main(String[] args) {
    new Pizzasort().checkPermutations();
  }

  private final Integer[] timesWrong = new Integer[ARRAY_LENGTH];

  private int comparisons;
  private int permutations;

  private Pizzasort() {
    for (int i = 0; i < ARRAY_LENGTH; i++) {
      timesWrong[i] = 0;
    }
  }

  private void checkPermutations() {
    List<Integer> originals = new ArrayList<>(ARRAY_LENGTH);
    for (int i = 0; i < ARRAY_LENGTH; i++) {
      originals.add(i);
    }
    permute(originals, 0);

    for (int i = 0; i < ARRAY_LENGTH; i++) {
      if (((double)timesWrong[i])/permutations> ((double)i)/ARRAY_LENGTH) {
        System.out.println("Got " + i + "th place wrong " + ((double)timesWrong[i]/permutations) + "% of the time");
      }
    }
    System.out.println(comparisons + " comparisons");
  }

  private void permute(List<Integer> arr, int k){
    for(int i = k; i < arr.size(); i++){
      java.util.Collections.swap(arr, i, k);
      permute(arr, k+1);
      java.util.Collections.swap(arr, k, i);
    }
    if (k == arr.size() -1){
      permutations++;
      Integer[] sorted = alecSolution(arr.toArray(new Integer[ARRAY_LENGTH]));

      for (int i = 0; i < ARRAY_LENGTH; i++) {
        if (sorted[i] != ARRAY_LENGTH-i-1) {
          timesWrong[i]++;
        }
      }
    }
  }

  private Integer[] alecSolution(Integer[] permutation) {
    Integer[] promoted = new Integer[ARRAY_LENGTH/2];
    Integer[] losers = new Integer[ARRAY_LENGTH/2];
    for (int i = 0; i < ARRAY_LENGTH; i+=2) {
      comparisons++;
      if (permutation[i] > permutation[i+1]) {
        promoted[i/2] = permutation[i];
        losers[i/2] = permutation[i+1];
      } else {
        promoted[i/2] = permutation[i+1];
        losers[i/2] = permutation[i];
      }
    }

    Arrays.sort(promoted, (left, right) -> {
      comparisons++;
      return right - left;
    });

    List<Integer> result = new ArrayList<>(Arrays.asList(promoted));
    result.addAll(Arrays.asList(losers));
    return result.toArray(permutation);
  }

  private void checkPermutation(Integer[] permutation) {
    Arrays.sort(permutation, (left, right) -> {
      comparisons++;
      return right - left;
    });
  }
}
