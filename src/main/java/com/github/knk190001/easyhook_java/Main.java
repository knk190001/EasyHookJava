package com.github.knk190001.easyhook_java;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.annotations.Delegate;

import java.io.IOException;

class Main {
    public interface LibC { // A representation of libC in Java
        int puts(String s); // mapping of the puts function, in C `int puts(const char *s);`
    }

    public interface DelegatedPuts {
        @Delegate
        int invoke(String s);
    }

    public static LibC msvcrt;

    public static void main(String[] args) throws IOException {
        Pointer putsAddr = LocalHook.GetProcAddress("msvcrt", "puts");

        msvcrt = LibraryLoader.create(LibC.class).load("msvcrt");
        LocalHook hook = LocalHook.create(putsAddr, Main::putsRepl, DelegatedPuts.class, null);
        hook.setInclusiveACL(new long[]{ 0 });

        msvcrt.puts("Hello world");
        hook.uninstall();
        msvcrt.puts("Hello world 2");
    }

    public static int putsRepl(String s) {
        msvcrt.puts("Kono DIO da!");
        return 0;
    }
}
