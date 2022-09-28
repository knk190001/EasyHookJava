package com.github.knk190001.easyhook_java;

import com.github.knk190001.easyhook_java.interop.Kernel32;
import com.github.knk190001.easyhook_java.interop.LHUnmanaged;
import jnr.ffi.ObjectReferenceManager;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.MemoryManager;

public class LocalHook {
    private final Pointer hookPointer;

    public static Pointer GetProcAddress(String module, String methodName) {
        return Kernel32.GetProcAddress(Kernel32.GetModuleHandleA(module), methodName);
    }

    private LocalHook(Pointer hookPointer) {
        this.hookPointer = hookPointer;
    }

    public static <T> LocalHook create(Pointer inTargetProc, T inNewProc, Class<T> inNewProcClass, Object inCallback) {
        Pointer lambdaPointer = JNRUtil.delegateToPointer(inNewProc, inNewProcClass);
        Runtime rt = Runtime.getRuntime(LHUnmanaged.getInstance());
        ObjectReferenceManager<Object> objectReferenceManager = rt.newObjectReferenceManager();
        MemoryManager mm = rt.getMemoryManager();
        Pointer inCallbackPointer;
        Pointer hookTrace = mm.allocate(rt.addressSize());
        if (inCallback != null) {
            inCallbackPointer = objectReferenceManager.add(inCallback);
        } else {
            inCallbackPointer = Pointer.wrap(rt, 0L);
        }
        int result = LHUnmanaged.lhInstallHook(inTargetProc, lambdaPointer, inCallbackPointer, hookTrace);
        return new LocalHook(hookTrace);
    }

    public void setExclusiveACL(long[] inACL) {
        Pointer threads = intArrayToNativeIntArray(inACL);

        LHUnmanaged.lhSetExclusiveACL(threads, inACL.length, hookPointer);
    }

    public void setInclusiveACL(long [] inACL) {
        Pointer threads = intArrayToNativeIntArray(inACL);;

        LHUnmanaged.lhSetInclusiveACL(threads, inACL.length, hookPointer);
    }

    private Pointer intArrayToNativeIntArray(long[] array) {
        Runtime rt = Runtime.getRuntime(LHUnmanaged.getInstance());
        MemoryManager mm = rt.getMemoryManager();
        Pointer nativeArray = mm.allocate(rt.longSize() * array.length);

        for (int i = 0; i < array.length; i++) {
            nativeArray.putNativeLong(i * 4L, array[i]);
        }
        return nativeArray;
    }

    public void uninstall() {
        LHUnmanaged.lhUninstallHook(hookPointer);
    }
}
