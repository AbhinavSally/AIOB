package com.abhi.aiob;

import android.graphics.Bitmap;
import android.net.Uri;

public class news {
    String id;
    String ss;
    String sti;

    public news() {
    }


    public news(String id, String ss, String sti) {
        this.id = id;
        this.ss = ss;
        this.sti = sti;

    }


    public String getId() {
        return id;
    }

    public String getSs() {
        return ss;
    }

    public String getSti() {
        return sti;
    }
}
