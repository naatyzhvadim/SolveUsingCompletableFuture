import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Solution {
    private double aRe, aIm = 0, bRe, bIm = 0;
    Solution (double a, double b) {
        aRe = a;
        bRe = b;
    }
    Solution(double aRe, double aIm, double bRe, double bIm) {
        this.aRe = aRe;
        this.aIm = aIm;
        this.bRe = bRe;
        this.bIm = bIm;
    }
    public void printSolution(){
        if (aIm == 0) {
            System.out.println(aRe + "  " + bRe);
        } else {
            System.out.println(aRe + " + " + aIm + " i  " + bRe +
                    " + " + bIm + " i");
        }
    }

    public double getARe() {
        return aRe;
    }
    public double getBRe() {
        return bRe;
    }
    public double getaIm() {
        return aIm;
    }
    public double getbIm() {
        return bIm;
    }
    public void setaIm(double aIm) {
        this.aIm = aIm;
    }
    public void setaRe(double aRe) {
        this.aRe = aRe;
    }
    public void setbIm(double bIm) {
        this.bIm = bIm;
    }
    public void setbRe(double bRe) {
        this.bRe = bRe;
    }

    public static CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            // Код загрузки и возврата содержимого веб-страницы
            return "CompletableFuture   ";
        });
    }

    public static void main(String[] args){

        String[] tmp = new String[2];
        tmp[0] = "  CompletableFuture  ";
        tmp[1] = "1561";
        List<String> webPageLinks = Arrays.asList(tmp);

        // Асинхронно загружаем содержимое всех веб-страниц
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());

// Создаём комбинированный Future, используя allOf()
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[0])
        );

        // Когда все задачи завершены, вызываем future.join(), чтобы получить результаты и собрать их в список
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures.stream()
                    .map(pageContentFuture -> pageContentFuture.join())
                    .collect(Collectors.toList());
        });


        // Подсчитываем количество веб-страниц, содержащих ключевое слово "CompletableFuture"
        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
            return pageContents.stream()
                    .filter(pageContent -> pageContent.contains("CompletableFuture"))
                    .count();
        });

        try {
            System.out.println("Количество веб-страниц с ключевым словом CompletableFuture - " +
                    countFuture.get());
        } catch (Exception e){
            System.out.print("OOOPS");
        }
    }
}
