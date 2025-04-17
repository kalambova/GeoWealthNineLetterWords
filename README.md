I. Да се намерят всички 9 буквени думи в английския език, за които за всяка една дума от
решението, е вярно следното:
- възможно е да се премахне една буква от думата и да се получи валидна 8 буквена дума;
- от 8 буквената дума може да се премахне една буква и да се получи валидна 7 буквена дума;
- от 7 буквената дума може да се премахне една буква и да се получи валидна 6 буквена дума и
т.н., докато се получи еднобуквена валидна дума;
- валидните еднобуквени думи са „I“ и „A“

Може да видите пример за думата „STARTLING“ от тук: https://www.republicworld.com/whatsapp-
quizzes-and-puzzles/riddles/what-9-letter-word-is-still-a-word-after-removing-one-letter-each-time.html

Обобщение: да се намерят всички валидни 9 буквени думи, които могат да бъдат доведени до
еднобуквени, чрез поетапно премахване на една буква, като след всяко премахване ще получим
наново валидна дума.
II. Задачата трябва да е оптимизирана за скорост. Ако една дума е валидна при една комбинация
от премахване на букви, не е нужно да се проверява дали би била валидна, ако премахнем
други букви.
Списък с всички думи в английския език може да сe ползва от тук:
https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt
III. Желателно е решението да НЕ чете локален файл, а да зареди като URL, примерно по следния
начин:
   private static Set<String> loadAllWordsSet() throws IOException {

        URL wordsURL = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        try (BufferedReader br = new BufferedReader(new
                InputStreamReader(wordsURL.openConnection().getInputStream()))) {
            return br.lines().skip(2).collect(Collectors.toSet());

        }
    }
