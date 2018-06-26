package com.cheng.other;

import com.cheng.core.Cookie;

public class ChocolateChip extends Cookie {

    public ChocolateChip() {
        System.out.println("ChocolateChip constructor");
    }

    public void chomp() {
        bite();  // error, the method bite() from the type Cookie is not visible
    }
}