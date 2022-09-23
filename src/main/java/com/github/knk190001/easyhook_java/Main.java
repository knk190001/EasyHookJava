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

    public static LibC libc;

    public static void main(String[] args) {
        System.loadLibrary(Platform.getNativePlatform().getStandardCLibraryName());
        Pointer putsAddr = Kernel32.GetProcAddress(Kernel32.GetModuleHandleA("msvcrt"), "puts");
        MemoryManager memoryManager = Runtime.getSystemRuntime().getMemoryManager();
        Pointer out = memoryManager.allocate(Runtime.getSystemRuntime().addressSize());
        Pointer threads = memoryManager.allocate(Runtime.getSystemRuntime().longSize());
        threads.putNativeLong(0, 0);
        DelegatedPuts test = Main::putsRepl;
        Pointer replacementAddr = JNRUtil.DelegateToPointer(test, DelegatedPuts.class);

        int result = LHUnmanaged.lhInstallHook(putsAddr, replacementAddr, Pointer.wrap(Runtime.getSystemRuntime(), 0L), out);
        LHUnmanaged.lhSetInclusiveACL(threads, 1, out);
        LibC msvcrt = LibraryLoader.create(LibC.class).load("msvcrt");
        libc = msvcrt;

        System.out.println("Result: " + result);

        msvcrt.puts("Hello world");

        LHUnmanaged.lhUninstallHook(out);

        msvcrt.puts("Hello world 2");
    }

    public static int putsRepl(String s){
        libc.puts("Kono DIO da!");
        return 0;
    }
}
