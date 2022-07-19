package by.it_academy.users_service.dto;

import java.util.List;

public class MyPage<T> implements IMyPage<T> {

    private final int number;
    private final int size;
    private final int totalPages;
    private final long totalElements;
    private final boolean first;
    private final int numberOfElements;
    private final boolean last;
    private final List<T> content;

    public MyPage(int number, int size, int totalPages, long totalElements,
                  boolean first, int numberOfElements, boolean last, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public List<T> getContent() {
        return content;
    }
}
