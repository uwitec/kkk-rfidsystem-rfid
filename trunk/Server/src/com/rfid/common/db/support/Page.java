package com.rfid.common.db.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象，包含当前页数据及分页信息。
 * 
 * @author
 * @version 0.1 初始版本，征集意见
 */
@SuppressWarnings("serial")
public class Page<T> implements Serializable
{

    /**
     * 默认分页大小
     */
    private static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 当前页在总记录中的起始位置，从0开始
     */
    private int start;

    /**
     * 分页大小
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 当前页中存放的数据
     */
    private List<T> data;

    /**
     * 构造方法，只构造空页。
     */
    public Page()
    {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
    }

    /**
     * 构造方法。
     * 
     * @param totalCount
     *            总记录数
     * @param start
     *            当前页在总记录中的起始位置，从0开始
     * @param pageSize
     *            分页大小
     * @param data
     *            本页包含的数据
     */
    public Page(long totalCount, int start, int pageSize, List<T> data)
    {
        this.totalCount = totalCount;
        this.start = start;
        this.pageSize = pageSize;
        this.data = data;
    }

    /**
     * 获取默认分页大小时给定页码的起始位置。
     * 
     * @param pageNo
     *            从1开始的页码
     * @return 给定页码的起始位置
     * @see #getStartOfPage(int,int)
     */
    protected static int getStartOfPage(int pageNo)
    {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取给定分页大小时给定页码的起始位置。
     * 
     * @param pageNo
     *            从1开始的页码
     * @param pageSize
     *            分页大小
     * @return 给定页码的起始位置
     */
    public static int getStartOfPage(int pageNo, int pageSize)
    {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 取总记录数。
     * 
     * @return 总记录数
     */
    public long getTotalCount()
    {
        return this.totalCount;
    }

    /**
     * 取分页大小。
     * 
     * @return 分页大小
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * 取总页数。
     * 
     * @return 总页数
     */
    public long getTotalPageCount()
    {
        if (totalCount % pageSize == 0)
        {
            return totalCount / pageSize;
        } else
        {
            return totalCount / pageSize + 1;
        }
    }

    /**
     * 取当前页的页码。
     * 
     * @return 当前页的页码
     */
    public int getCurrentPageNo()
    {
        return start / pageSize + 1;
    }

    /**
     * 该页是否有上一页。
     * 
     * @return 有上一页返回true，否则返回false。
     */
    public boolean hasPreviousPage()
    {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 该页是否有下一页。
     * 
     * @return 有下一页返回true，否则返回false。
     */
    public boolean hasNextPage()
    {
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }

    /**
     * 取当前页的数据。
     * 
     * @return 当前页的数据
     */
    public List<T> getResult()
    {
        return data;
    }

}

