package com.chou.spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Administrator on 2017/4/11.
 */
public class WordCounter {

    private final int counter; //字符串数量
    private final boolean lastSpace;//前一个字符是否为空格
    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter,
                wordCounter.lastSpace);
    }
    public int getCounter() {
        return counter;
    }


    public static void main(String[] args) {
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream) + " words");

//        System.out.println("Found " + countWords(stream.parallel()) + " words");

        System.out.println("countWordsIteratively Found " + countWordsIteratively(SENTENCE) + " worlds");



        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        final Stream<Character> char_stream = StreamSupport.stream(spliterator, true);

        System.out.println("use WordCounterSpliterator contruct parallel stream Found " + countWords(char_stream) + " words");


    }

    private static  int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
    private static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }



}



