package com.github.knk190001.easyhook_java;

import com.github.knk190001.easyhook_java.interop.Kernel32;
import jnr.ffi.Pointer;

public class LocalHook {
    public static Pointer GetProcAddress(String module, String methodName){
        return Kernel32.GetProcAddress(Kernel32.GetModuleHandleA(module),methodName);
    }

}
