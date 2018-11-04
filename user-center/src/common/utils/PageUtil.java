package utils;

public class PageUtil {

    //通过数据总数、每页数据条数计算总页数
    public static int totalPage(int totalCount, int pageSize) {
        int totalPage = totalCount / pageSize;
        int temp = totalPage * pageSize;
        if (temp < totalCount) {
            totalPage += 1;
        }
        return totalPage;
    }
}
