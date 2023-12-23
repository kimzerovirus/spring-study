package me.kzv.reactspring.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> dtoList;

    private List<Integer> pageNumList;

    private PageRequest pageRequest;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageResponse(List<T> dtoList, PageRequest request, long totalCount) {
        this.dtoList = dtoList;
        this.pageRequest = request;
        this.totalCount = (int) totalCount;

        int end = (int) (Math.ceil(request.getPage() / 10.0)) * 10;
        int start = end - 9;
        int last = (int) (Math.ceil(totalCount / (double) request.getSize()));

//        end = end > last ? last : end;
        end = Math.min(end, last);
        this.prev = start > 1;

        this.next = totalCount > (long) end * request.getSize();
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().toList();

        if(prev){
            this.prevPage = start - 1;
        }

        if(next){
            this.nextPage = end + 1;
        }

        this.totalPage = this.pageNumList.size();

        this.current = request.getPage();
    }
}
