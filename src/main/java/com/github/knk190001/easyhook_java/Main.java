package com.github.knk190001.easyhook_java;

import com.github.knk190001.easyhook_java.interop.Kernel32;
import com.github.knk190001.easyhook_java.interop.LHUnmanaged;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Platform;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.provider.MemoryManager;

public class Main {
    public interface LibC { // A representation of libC in Java
        int puts(String s); // mapping of the puts function, in C `int puts(const char *s);`
    }

    public interface DelegatedPuts {
        @Delegate
        int invoke(String s);
    }

    public static native int puts(String s);

    public static LibC msvcrt;

    public static void main(String[] args) {
        System.loadLibrary(Platform.getNativePlatform().getStandardCLibraryName());
        Pointer putsAddr = Kernel32.GetProcAddress(Kernel32.GetModuleHandleA("msvcrt"), "puts");

        msvcrt = LibraryLoader.create(LibC.class).load("msvcrt");
        LocalHook hook = LocalHook.create(putsAddr, Main::putsRepl, DelegatedPuts.class, null);
        hook.setInclusiveACL(new long[]{ 0 });

//        System.out.println("Result: " + );

        msvcrt.puts("Hello world");
//        LHUnmanaged.lhUninstallHook(out);
        hook.uninstall();
        msvcrt.puts("Hello world 2");



    }

    public static int putsRepl(String s) {
        msvcrt.puts("Kono DIO da!");
        return 0;
    }
}
