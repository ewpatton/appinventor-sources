package com.google.appinventor.buildserver;

import com.google.common.base.Joiner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JetifierTask {

    private static JetifierTask INSTANCE;

    private static final String JETIFIER_DIR = "/tools/jetifier-standalone/lib/";
    private static final String[] JETIFIER_CP = {
            "annotations-13.0.jar",
            "asm-6.0.jar",
            "asm-commons-6.0.jar",
            "asm-tree-6.0.jar",
            "asm-util-6.0.jar",
            "commons-cli-1.3.1.jar",
            "gson-2.8.0.jar",
            "jdom2-2.0.6.jar",
            "jetifier-core-1.0.0-beta09.jar",
            "jetifier-processor-1.0.0-beta09.jar",
            "jetifier-standalone.jar",
            "kotlin-stdlib-1.3.60.jar",
            "kotlin-stdlib-common-1.3.60.jar"
    };

    private String classpath;
    private int mChildProcessRamMb = 1024;

    private JetifierTask() {
        List<String> jetifierClasspath = new ArrayList<>();
        for (String path : JETIFIER_CP) {
            jetifierClasspath.add(Compiler.getResource(JETIFIER_DIR + path));
        }
        this.setClasspath(Joiner.on(File.pathSeparatorChar).join(jetifierClasspath));
    }

    public static JetifierTask getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JetifierTask();
        }
        return INSTANCE;
    }

    public void setClasspath(String cp) {
        classpath = cp;
    }

    public void setChildProcessRamMb(int mb) {
        mChildProcessRamMb = mb;
    }

    public boolean execute(String fileToJetify) {
        int mx = mChildProcessRamMb - 200;

//        java -cp <classpath> com.android.tools.build.jetifier.standalone.Main \
//        -i <fileToJetify> \
//        -o <output jetified lib path>

        List<String> cmd = new ArrayList<>();
        cmd.add(System.getProperty("java.home") + "/bin/java");
        cmd.add("-mx" + mx + "M");
        cmd.add("-cp");
        cmd.add(classpath);
        cmd.add("com.android.tools.build.jetifier.standalone.Main");
        cmd.add("-i");
        cmd.add(fileToJetify);
        cmd.add("-o");
        cmd.add(fileToJetify); // should we overwrite the original file?

        return Execution.execute(null, cmd.toArray(new String[0]), System.out, System.err);
    }

    private static String getJetifiedFilePath(String file) {
        return PathUtil.trimOffExtension(file) + "_jetified." + PathUtil.getFileExtension(file);
    }
}
