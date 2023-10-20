package cn.gduf.commuterSystem.entities;

import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/18 21:08
 */
public class MyPage<T> {
    private List<T> records;
    private long total;
    private long current;
    private long size;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current = (current <= 0) ? 1 : current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
