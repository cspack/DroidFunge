package us.exya.droidfunge.ui;

/**
 * Created by zearen on 10/07/13.
 */
public class BefungeDraw {
    public BefungeDraw() {
        info = "";
    }

    public BefungeDraw(String info) {
        this.info = info;
    }

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
