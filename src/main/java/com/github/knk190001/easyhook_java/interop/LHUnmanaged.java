package com.github.knk190001.easyhook_java.interop;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;

public class LHUnmanaged {
    protected interface LHUnmanagedImpl {
        LHUnmanagedImpl INSTANCE = LibraryLoader.create(LHUnmanagedImpl.class).load("EasyHook64.dll");

        int LhInstallHook(Pointer InEntryPoint, Pointer InHookProc, Pointer InCallback, Pointer OutHandle);

        int LhUninstallHook(Pointer InHandle);

        int LhSetInclusiveACL(Pointer InThreadIdList, long InThreadCount, Pointer InHandle);

        int LhUninstallAllHooks();

        int LhWaitForPendingRemovals();

        int LhSetExclusiveACL(Pointer InThreadIdList, long InThreadCount, Pointer InHandle);

        int LhSetGlobalInclusiveACL(Pointer InThreadIdList, long InThreadCount);

        int LhSetGlobalExclusiveACL(Pointer InThreadIdList, long InThreadCount);

        int LhIsThreadIntercepted(Pointer InHook, long InThreadId, Pointer OutResult);

        int LhIsProcessIntercepted(Pointer InHook, long InProcessID, Pointer OutResult);

        int LhBarrierGetReturnAddress(Pointer OutValue);

        int LhBarrierGetAddressOfReturnAddress(Pointer OutValue);

        int LhBarrierGetCallback(Pointer OutValue);
    }

    public static int lhInstallHook(Pointer InEntryPoint, Pointer InHookProc, Pointer InCallback, Pointer OutHandle) {
        return LHUnmanagedImpl.INSTANCE.LhInstallHook(InEntryPoint, InHookProc, InCallback, OutHandle);
    }

    public static int lhUninstallHook(Pointer InHandle) {
        return LHUnmanagedImpl.INSTANCE.LhUninstallHook(InHandle);
    }

    public static int lhSetInclusiveACL(Pointer InThreadIdList, long InThreadCount, Pointer InHandle) {
        return LHUnmanagedImpl.INSTANCE.LhSetInclusiveACL(InThreadIdList, InThreadCount, InHandle);
    }

    public static int lhUninstallAllHooks() {
        return LHUnmanagedImpl.INSTANCE.LhUninstallAllHooks();
    }

    public static int lhWaitForPendingRemovals() {
        return LHUnmanagedImpl.INSTANCE.LhWaitForPendingRemovals();
    }

    public static int lhSetExclusiveACL(Pointer InThreadIdList, long InThreadCount, Pointer InHandle) {
        return LHUnmanagedImpl.INSTANCE.LhSetExclusiveACL(InThreadIdList, InThreadCount, InHandle);
    }

    public static int lhSetGlobalInclusiveACL(Pointer InThreadIdList, long InThreadCount) {
        return LHUnmanagedImpl.INSTANCE.LhSetGlobalInclusiveACL(InThreadIdList, InThreadCount);
    }

    public static int lhSetGlobalExclusiveACL(Pointer InThreadIdList, long InThreadCount) {
        return LHUnmanagedImpl.INSTANCE.LhSetGlobalExclusiveACL(InThreadIdList, InThreadCount);
    }

    public static int lhIsThreadIntercepted(Pointer InHook, long InThreadId, Pointer OutResult) {
        return LHUnmanagedImpl.INSTANCE.LhIsThreadIntercepted(InHook, InThreadId, OutResult);
    }

    public static int lhIsProcessIntercepted(Pointer InHook, long InProcessID, Pointer OutResult) {
        return LHUnmanagedImpl.INSTANCE.LhIsProcessIntercepted(InHook, InProcessID, OutResult);
    }

    public static int lhBarrierGetReturnAddress(Pointer OutValue) {
        return LHUnmanagedImpl.INSTANCE.LhBarrierGetReturnAddress(OutValue);
    }

    public static int lhBarrierGetAddressOfReturnAddress(Pointer OutValue) {
        return LHUnmanagedImpl.INSTANCE.LhBarrierGetAddressOfReturnAddress(OutValue);
    }

    public static int lhBarrierGetCallback(Pointer OutValue) {
        return LHUnmanagedImpl.INSTANCE.LhBarrierGetCallback(OutValue);
    }
}
