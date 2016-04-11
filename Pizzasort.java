import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Pizzasort {
  private static final int ARRAY_LENGTH = 11;

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
      checkPermutation(arr.toArray(new Integer[ARRAY_LENGTH]));
    }
  }

  private void checkPermutation(Integer[] permutation) {
    permutations++;
    Arrays.sort(permutation, (left, right) -> {
      comparisons++;
      return right - left;
    });

    for (int i = 0; i < ARRAY_LENGTH; i++) {
      if (permutation[i] != ARRAY_LENGTH-i-1) {
        timesWrong[i]++;
      }
    }
  }
}
