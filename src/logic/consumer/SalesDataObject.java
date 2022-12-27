package logic.consumer;

import java.util.List;

public class SalesDataObject {
    private List<SalesPerDay> salesPerDay;

    public SalesDataObject(List<SalesPerDay> salesPerDay){
        this.salesPerDay = salesPerDay;
    }

    public List<SalesPerDay> getArticlePerDay() {
        return salesPerDay;
    }

}
