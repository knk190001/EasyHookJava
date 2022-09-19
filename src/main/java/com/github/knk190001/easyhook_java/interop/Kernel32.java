package com.github.knk190001.easyhook_java.interop;


import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;

public class Kernel32 {
    protected interface Kernel32Impl {
        Kernel32Impl INSTANCE = LibraryLoader.create(Kernel32Impl.class).load("kernel32");

        Pointer GetProcAddress(Pointer hModule, String procName);
        Pointer GetModuleHandleA(String lpModuleName);

    }

    public static Pointer GetProcAddress(Pointer hModule, String procName) {
        return Kernel32Impl.INSTANCE.GetProcAddress(hModule, procName);
    }

    public static Pointer GetModuleHandleA(String lpModuleName){
        return Kernel32Impl.INSTANCE.GetModuleHandleA(lpModuleName);
    }
}