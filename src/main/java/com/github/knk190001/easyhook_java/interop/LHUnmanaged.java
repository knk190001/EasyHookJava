package com.github.knk190001.easyhook_java.interop;

import com.github.knk190001.easyhook_java.Main;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;

public class LHUnmanaged {
    protected interface LHUnmanagedImpl {
        LHUnmanagedImpl INSTANCE = LibraryLoader.create(LHUnmanagedImpl.class).load("EasyHook64.dll");

        int LhInstallHook(Pointer InEntryPoint, Pointer InHookProc, Pointer InCallback, Pointer OutHandle);

        int LhUninstallHook(Pointer InHandle);

        int LhSetInclusiveACL(Pointer InThreadIdList, long InThreadCount, Pointer InHandle);
    }

    public static int LhInstallHook(Pointer InEntryPoint, Pointer InHookProc, Pointer InCallback, Pointer OutHandle) {
        return LHUnmanagedImpl.INSTANCE.LhInstallHook(InEntryPoint, InHookProc, InCallback, OutHandle);
    }

    public static int LhUninstallHook(Pointer InHandle) {
        return LHUnmanagedImpl.INSTANCE.LhUninstallHook(InHandle);
    }

    public static int LhSetInclusiveACL(Pointer InThreadIdList, long InThreadCount, Pointer InHandle) {
        return LHUnmanagedImpl.INSTANCE.LhSetInclusiveACL(InThreadIdList, InThreadCount, InHandle);
    }
}
