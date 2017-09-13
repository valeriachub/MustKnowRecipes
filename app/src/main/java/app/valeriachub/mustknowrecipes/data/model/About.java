package app.valeriachub.mustknowrecipes.data.model;

public class About {
    private String info;
    private String version;

    public About(String info, String version) {
        this.info = info;
        this.version = version;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
