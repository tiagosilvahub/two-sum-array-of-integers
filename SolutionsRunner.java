import java.util.Arrays;

public class SolutionsRunner {
    public static void main(String[] args) {
        SolutionStrategy[] strategies =
                new SolutionStrategy[]{
                        new bruteForceStrategy(),
                        new complementsGoBackStrategy(),
                        new complementsHashMapStrategy()
                };

        var input = new int[][]{ new int[] {2, 7, 11, 15}};
        var input2 = new int[]{ 9 };
        var output = new int[][]{ new int[] {0, 1} };

        int[] result;
        int errors = 0;

        int nTestCases = input.length;
        for (SolutionStrategy s : strategies) {
            for (int i = 0; i < nTestCases; i++) {
                result = s.solve(input[i], input2[i]);
                if(!Arrays.equals(result, output[i])) {
                    System.out.println("Solution " + s.getClass().getName() + " wrong for input "
                            + Arrays.toString(input[i]) + " " + input2[i]);
                    System.out.println("Expected: " + Arrays.toString(output[i]) + " but got: " + Arrays.toString(result));
                    System.out.println();
                    errors++;
                }
            }
        }
        printResults(errors, nTestCases);
    }

    private static void printResults(int errors, int nTestCases) {
        if(errors == 0) {
            System.out.println("All tests passed!");
        } else {
            System.out.println(errors + " tests failed.");
            System.out.println(nTestCases - errors + " tests passed.");
            System.out.println((0.0 + nTestCases - errors) / nTestCases * 100  + "% of tests passed.");
        }
    }
}

