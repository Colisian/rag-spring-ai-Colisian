package edu.umd.innovationlab.ragspringai.util;

import java.util.ArrayList;
import java.util.List;

public class ListChunker {
    public static <T> List<List<T>> chunkList(List<T> inputList, int chunkSize) {
        // List to hold all the chunks
        List<List<T>> chunkedLists = new ArrayList<>();

        // Variable to track the number of elements processed
        int totalSize = inputList.size();

        for (int i = 0; i < totalSize; i += chunkSize) {
            // Get the min value between the start of the next chunk and the total size
            int end = Math.min(totalSize, i + chunkSize);

            // Add the sublist to chunked lists
            chunkedLists.add(new ArrayList<>(inputList.subList(i, end)));
        }

        return chunkedLists;
    }
}
