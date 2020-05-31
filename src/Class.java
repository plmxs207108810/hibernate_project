public class Class {
    private int cid;
    private String classname;

    public int getCid() {
        return cid;
    }


    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    @Override
    public String toString() {
        return "Class{" +
                "cid=" + cid +
                ", classname='" + classname + '\'' +
                '}';
    }
}
