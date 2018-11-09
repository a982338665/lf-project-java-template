package pers.li.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PageBean<T> {
    // 当前页数
    private int currPage;
    // 每页显示的记录数
    private int pageSize;
    // 总记录数
    private int totalCount;
    // 总页数
    private int totalPage;
    // 每页显示的数据
    private List<T> list;

}
