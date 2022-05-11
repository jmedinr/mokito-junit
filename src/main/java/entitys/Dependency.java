package entitys;

public class Dependency {

    private final SubDependency subDependency;

    public Dependency(SubDependency subDependency) {
        super();
        this.subDependency = subDependency;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public String getSubdepedencyClassName() {
        return subDependency.getClassName();
    }

    public int addTwo(int i) {
        return i + 2;
    }

    public String getClassNameUpperCase() {
        return getClassName().toUpperCase();
    }

}