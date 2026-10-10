public class Main3 {
    
    public static String findBook(String[][] catalog, String targetISBN){
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right){
            int middle = (left + right) / 2;

            if (catalog[middle][0].equals(targetISBN)){
                return catalog[middle][1];
            }
            else if (catalog[middle][0].compareTo(targetISBN) < 0){
                left = middle + 1;
            }
            else{
                right = middle - 1;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
            {"0001112223", "Introduction to Algebra"},
            {"0002223334", "Beginning Python"},
            {"0003334445", "Classic Mythology"},
            {"0004445556", "Data and Society"},
            {"0005556667", "European History"}
        };

        String targetISBN = "0003334445";

        System.out.println(findBook(catalog, targetISBN));

        String targetISBN2 = "0009998887";

        System.out.println(findBook(catalog, targetISBN2));
    }
}