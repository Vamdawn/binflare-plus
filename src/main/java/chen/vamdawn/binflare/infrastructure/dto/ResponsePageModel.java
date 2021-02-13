package chen.vamdawn.binflare.infrastructure.dto;

import lombok.Data;

import java.util.List;

/**
 * Page result schema
 *
 * @param <T> page of data
 * @author chen
 */
@Data
public class ResponsePageModel<T> {

    private List<T> list;
    private long totalCount;
    private long totalPage;
    private long currentPage;

    public ResponsePageModel() {
    }

    public ResponsePageModel(List<T> list, long totalCount, long totalPage, long currentPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }
}
