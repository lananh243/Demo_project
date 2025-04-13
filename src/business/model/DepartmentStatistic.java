package business.model;

public class DepartmentStatistic {
    private String depId;
    private int total;

    public DepartmentStatistic() {
    }

    public DepartmentStatistic(String depId, int total) {
        this.depId = depId;
        this.total = total;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
