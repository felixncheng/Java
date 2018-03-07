package com.chengmboy.other.blade;

import com.blade.Blade;

/**
 * @author cheng_mboy
 */
public class BladeMain {

    public static void main(String[] args) {
        Blade.me().get("/", (req, res) -> {
            res.text("Hello Blade");
        }).start();
    }
}
