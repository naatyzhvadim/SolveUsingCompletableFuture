import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class GeneratorOfQuadrEq {

    public QuadraticEquation getEquation (double radius){
        double[] a = new double[3];
        final Random random = new Random();
        for (int i = 0; i < 3; ++i) {
            a[i] = random.nextDouble() * radius - radius / 2;
        }
        return new QuadraticEquation(a[0], a[1], a[2]);
    }


    public static CompletableFuture<Solution> futureSolution(QuadraticEquation equation, Executor executors){
        return CompletableFuture.supplyAsync(() -> {
            if (equation == null) {
                return null;
            }
            double a = equation.getA(), b = equation.getB(), c = equation.getC();
            double d = b * b - 4 * a * c;
            if (d < 0) {
                return new Solution(- b / (2 * a), Math.sqrt(-d) / (2 * a),
                        - b / (2 * a), - Math.sqrt(-d) / (2 * a));
            } else {
                return new Solution((-b + Math.sqrt(d))/(2 * a), (-b - Math.sqrt(d))/(2 * a));
            }
        }, executors);
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();

        GeneratorOfQuadrEq generator = new GeneratorOfQuadrEq();
        double radius = 200;
        generator.getEquation(radius);

        int numberOfEquations = 50;
        QuadraticEquation[] equations = new QuadraticEquation[numberOfEquations];
        for (int i = 0; i < numberOfEquations; ++i) {
            equations[i] = generator.getEquation(radius);
        }

        int numberOfExecutors = 8;

        //Creating thread pool
        Executor executor = Executors.newFixedThreadPool(numberOfExecutors);

        //Creating list of equations from array
        List<QuadraticEquation> listEquations = Arrays.asList(equations);

        //Creating list of CompletableFuture<Solution>
        //list of equations -> stream of equations -> stream of solutions -> list of solutions
        List<CompletableFuture<Solution>> listSolutions = listEquations.stream().map(equation ->
                futureSolution(equation, executor)).collect(Collectors.toList());

        //allOf returns void
        CompletableFuture<Void> allFutures =
                CompletableFuture.allOf(listSolutions.toArray(new CompletableFuture[0]));

        CompletableFuture<List<Solution>> allSolutions = allFutures.thenApply(v ->
                listSolutions.stream().map(oneSolution ->
                        oneSolution.join()).collect(Collectors.toList()));

        List<Solution> resultList = allSolutions.join();
        Iterator<Solution> iterator = resultList.iterator();
        while (iterator.hasNext()){
            Solution solution = iterator.next();
            solution.printSolution();
        }

        long timeSpent = System.currentTimeMillis() - startTime;

        System.out.println("программа выполнялась " + timeSpent + " миллисекунд  ");
    }
}
