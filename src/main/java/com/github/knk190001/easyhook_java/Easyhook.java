package com.github.knk190001.easyhook_java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.List;

public class Easyhook {

    private static List<String> dlls = Arrays.asList(
            "ComplexParameterInject.dll",
            "EasyHook.dll",
            "EasyHook32.dll",
            "EasyHook64.dll",
            "EasyLoad32.dll",
            "EasyLoad64.dll",
            "FileMonInject.dll",
            "ProcMonInject.dll"
    );

    public static String install() {
        Path easyHookDLLsDir = null;
        try {
            easyHookDLLsDir = Files.createTempDirectory("EasyHookDLLs");

            for (String dll : dlls) {
                Path dllTargetPath = easyHookDLLsDir.resolve(dll);
                InputStream in = Easyhook.class.getResourceAsStream("/" + dll);
                if (in != null) {
                    Files.copy(in, dllTargetPath);
                }
            }
            return easyHookDLLsDir.resolve("EasyHook64.dll").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
