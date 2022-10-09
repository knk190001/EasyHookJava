package com.github.knk190001.easyhook_java;

import com.github.knk190001.easyhook_java.interop.LHUnmanaged;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.NativeRuntime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JNRUtil {
    public static <T> Pointer delegateToPointer(T delegate, Class<T> delegateClass) {
        Runtime rt = Runtime.getRuntime(LHUnmanaged.getInstance());
        return rt.getClosureManager().getClosurePointer(delegateClass, delegate);
    }
}
