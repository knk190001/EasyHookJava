package com.github.knk190001.easyhook_java;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.NativeRuntime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JNRUtil {
    public static <T> Pointer DelegateToPointer(T delegate, Class<T> delegateClass) {
        try {
            Class<?> ncmClass = Class.forName("jnr.ffi.provider.jffi.NativeClosureManager");
            Method getClosurePointer = ncmClass.getDeclaredMethod("getClosurePointer", Class.class,Object.class);
            getClosurePointer.setAccessible(true);
            Object ncm = NativeRuntime.getInstance().getClosureManager();

            return ((Pointer) getClosurePointer.invoke(ncm, delegateClass, delegate));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Unable to get pointer for delegate");
    }
}
