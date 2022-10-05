package com.github.knk190001.easyhook_java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.List;

public class Easyhook {

    private static List<String> dlls = Arrays.asList(
            "EasyHook64.dll",
            "ComplexParameterInject.dll",
            "EasyHook.dll",
            "EasyLoad64.dll",
            "FileMonInject.dll",
            "ProcMonInject.dll"
    );
    ;

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
            return easyHookDLLsDir.resolve(dlls.get(0)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void loadJarDll(String name) throws IOException {
        InputStream in = Easyhook.class.getResourceAsStream("/" + name);
        byte[] buffer = new byte[1024];
        int read = -1;
        File temp = File.createTempFile(name, "");
        FileOutputStream fos = new FileOutputStream(temp);

        while ((read = in.read(buffer)) != -1) {
            fos.write(buffer, 0, read);
        }
        fos.close();
        in.close();

        System.load(temp.getAbsolutePath());
    }
}
