/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.canews.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author PramUkesh
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        main project = new main();
        project.createProject();

    }

    public void createProject() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Project Name : ");
        String projectName = scanner.nextLine();

        System.out.print("Enter Package Name : ");
        String packageName = scanner.nextLine();

        String dir = System.getProperty("user.dir").replace("/", "//");
        String projectDir = dir + "//" + projectName;
        createDirs(projectName, packageName, projectDir);
        //String projectDir = System.getProperty("user.dir")+"";
        //javaPackage(packageName,packageName);

        System.out.print("Enter Gradle Android Plugin Version Example(3.0.1) : ");
        String gradleVersion = scanner.nextLine();

        createGradlefile(gradleVersion, dir, projectName, packageName);
        createGradleProperties(dir, projectName);
        createGradlew(dir, projectName);
        createGradlewbat(dir, projectName);
        createlocalProperties(dir, projectName);
        createsettingsGradle(dir, projectName);
        createGradleBuidforapp(projectDir, packageName);
//    createGradleProperties(dir,projectName);
    }

    public void createGradleBuidforapp(String projectDir, String packageName) throws IOException {
        File f = new File(projectDir + "//app//build.gradle");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minSdkVersion Here : ");
        String minSdkVersion = scanner.nextLine();

        String s = "apply plugin: 'com.android.application'\n"
                + "\n"
                + "android {\n"
                + "    compileSdkVersion 26\n"
                + "    defaultConfig {\n"
                + "        applicationId \""
                + packageName
                + "\"\n"
                + "        minSdkVersion "
                + minSdkVersion
                + "\n"
                + "        targetSdkVersion 26\n"
                + "        versionCode 1\n"
                + "        versionName \"1.0\"\n"
                + "    }\n"
                + "    buildTypes {\n"
                + "        release {\n"
                + "            minifyEnabled false\n"
                + "            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'\n"
                + "        }\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "dependencies {\n"
                + "    implementation fileTree(dir: 'libs', include: ['*.jar'])\n"
                + "	// add dependencies here\n"
                + "}";
        FileWriter w = new FileWriter(f);
        w.write(s);
        w.close();

        File fn = new File(projectDir + "//app//proguard-rules.pro");

        String st = "# Add project specific ProGuard rules here.\n"
                + "# You can control the set of applied configuration files using the\n"
                + "# proguardFiles setting in build.gradle.\n"
                + "#\n"
                + "# For more details, see\n"
                + "#   http://developer.android.com/guide/developing/tools/proguard.html\n"
                + "\n"
                + "# If your project uses WebView with JS, uncomment the following\n"
                + "# and specify the fully qualified class name to the JavaScript interface\n"
                + "# class:\n"
                + "#-keepclassmembers class fqcn.of.javascript.interface.for.webview {\n"
                + "#   public *;\n"
                + "#}\n"
                + "\n"
                + "# Uncomment this to preserve the line number information for\n"
                + "# debugging stack traces.\n"
                + "#-keepattributes SourceFile,LineNumberTable\n"
                + "\n"
                + "# If you keep the line number information, uncomment this to\n"
                + "# hide the original source file name.\n"
                + "#-renamesourcefileattribute SourceFile";
        FileWriter wr = new FileWriter(fn);
        wr.write(st);
        wr.close();
    }

    public void createDirs(String projectName, String packageName, String projectDir) throws IOException {

        Boolean a = new File(projectName).mkdir();
        Boolean b = new File(projectName + "/build").mkdir();
        Boolean c = new File(projectName + "/gradle").mkdir();
        Boolean d = new File(projectName + "/.gradle").mkdir();
        Boolean e = new File(projectName + "/app").mkdir();
        Boolean f = new File(projectName + "/app/build").mkdir();
        Boolean g = new File(projectName + "/app/libs").mkdir();
        Boolean h = new File(projectName + "/app/src").mkdir();
        Boolean s = new File(projectName + "/app/src/main").mkdir();

        Boolean i = new File(projectName + "/app/src/main/java").mkdir();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter MainActivity File Name(With out .java Eg:MainActivity) : ");
        String name = sc.nextLine();
        System.out.print("Enter MainLayoutName File Name(With out .xml Eg:activity_main) : ");
        String namexml = sc.nextLine();

        File fn = new File(projectDir + "//app//src//main//AndroidManifest.xml");
        String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n"
                + "    package=\""
                + packageName
                + "\">\n"
                + "\n"
                + "    <application\n"
                + "        android:allowBackup=\"true\"\n"
                + "        android:icon=\"@mipmap/ic_launcher\"\n"
                + "        android:label=\"@string/app_name\"\n"
                + "        android:roundIcon=\"@mipmap/ic_launcher_round\"\n"
                + "        android:supportsRtl=\"true\"\n"
                + "        android:theme=\"@style/AppTheme\">\n"
                + "        <activity android:name=\"."
                + name
                + "\">\n"
                + "            <intent-filter>\n"
                + "                <action android:name=\"android.intent.action.MAIN\" />\n"
                + "\n"
                + "                <category android:name=\"android.intent.category.LAUNCHER\" />\n"
                + "            </intent-filter>\n"
                + "        </activity>\n"
                + "    </application>\n"
                + "\n"
                + "</manifest>";

        FileWriter ab = new FileWriter(fn);
        ab.write(content);
        ab.close();

        if (packageName.contains(".")) {

            String[] packageNames = packageName.split(Pattern.quote("."));
            if (packageNames.length > 0) {
                String com = packageNames[0];
                Boolean t = new File(projectName + "/app/src/main/java/" + com).mkdir();
                if (packageNames.length == 1) {
                    String dir = projectDir + "//app//src//main//java//" + com + "//";
                    createMainActivity(dir, packageName, name, namexml);
                }
            }
            if (packageNames.length > 1) {
                String com = packageNames[0];
                String example = packageNames[1];
                Boolean u = new File(projectName + "/app/src/main/java/" + com + "/" + example).mkdir();
                if (packageNames.length == 2) {
                    String dir = projectDir + "//app//src//main//java//" + com + example + "//";
                    createMainActivity(dir, packageName, name, namexml);
                }
            }
            if (packageNames.length > 2) {
                String com = packageNames[0];
                String example = packageNames[1];
                String app = packageNames[2];
                Boolean v = new File(projectName + "/app/src/main/java/" + com + "/" + example + "/" + app).mkdir();
                if (packageNames.length == 3) {
                    String dir = projectDir + "//app//src//main//java//" + com + "//" + example + "//" + app + "//";
                    createMainActivity(dir, packageName, name, namexml);
                }
            }
            if (packageNames.length > 3) {
                String com = packageNames[0];
                String example = packageNames[1];
                String app = packageNames[2];
                String next = packageNames[3];
                Boolean w = new File(projectName + "/app/src/main/java/" + com + "/" + example + "/" + app + "/" + next).mkdir();
                if (packageNames.length == 4) {
                    String dir = projectDir + "//app//src//main//java//" + com + "//" + example + "//" + app + "//" + next + "//";
                    createMainActivity(dir, packageName, name, namexml);
                }
            }
            if (packageNames.length > 4) {
                String com = packageNames[0];
                String example = packageNames[1];
                String app = packageNames[2];
                String next = packageNames[3];
                String after = packageNames[4];
                Boolean x = new File(projectName + "/app/src/main/java/" + com + "/" + example + "/" + app + "/" + next + "/" + after).mkdir();
                if (packageNames.length == 5) {
                    String dir = projectDir + "//app//src//main//java//" + com + "//" + example + "//" + app + "//" + next + "//" + after + "//";
                    createMainActivity(dir, packageName, name, namexml);
                }
            }
            if (packageNames.length > 5) {
                String com = packageNames[0];
                String example = packageNames[1];
                String app = packageNames[2];
                String next = packageNames[3];
                String after = packageNames[4];
                String more = packageNames[5];
                Boolean y = new File(projectName + "/app/src/main/java/" + com + "/" + example + "/" + app + "/" + next + "/" + after + "/" + more).mkdir();
                if (packageNames.length == 6) {
                    String dir = projectDir + "//app//src//main//java//" + com + "//" + example + "//" + app + "//" + next + "//" + after + "//" + more + "//";
                    createMainActivity(dir, packageName, name, namexml);
                }
            }

        }
        Boolean t = new File(projectName + "/app/src/main/res").mkdir();
        Boolean u = new File(projectName + "/app/src/main/res/drawable").mkdir();
        Boolean j = new File(projectName + "/app/src/main/res/layout").mkdir();

        File fnn = new File(projectDir + "//app//src//main//res//layout//" + namexml + ".xml");
        String content2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n"
                + "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n"
                + "    xmlns:tools=\"http://schemas.android.com/tools\"\n"
                + "    android:layout_width=\"match_parent\"\n"
                + "    android:layout_height=\"match_parent\"\n"
                + "    tools:context=\""
                + packageName + "."
                + name
                + "\">\n"
                + "\n"
                + "    <TextView\n"
                + "        android:layout_width=\"wrap_content\"\n"
                + "        android:layout_height=\"wrap_content\"\n"
                + "        android:text=\"Hello World!\" />\n"
                + "\n"
                + "</LinearLayout>";

        FileWriter abc = new FileWriter(fnn);
        abc.write(content2);
        abc.close();

        Boolean k = new File(projectName + "/app/src/main/res/menu").mkdir();
        Boolean l = new File(projectName + "/app/src/main/res/mipmap-anydpi-v26").mkdir();
        Boolean m = new File(projectName + "/app/src/main/res/mipmap-hdpi").mkdir();

        File image = new File(projectDir + "//app//src//main//res//mipmap-hdpi//ic_launcher.svg");

        String str = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20010904//EN\" \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n"
                + "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"72px\" height=\"72px\" viewBox=\"0 0 720 720\" preserveAspectRatio=\"xMidYMid meet\">\n"
                + "<g id=\"layer101\" fill=\"#71c3bc\" stroke=\"none\">\n"
                + " <path d=\"M175 440 c-4 -6 -3 -16 3 -22 6 -6 12 -6 17 2 4 6 3 16 -3 22 -6 6 -12 6 -17 -2z\"/>\n"
                + " <path d=\"M525 440 c-4 -6 -3 -16 3 -22 6 -6 12 -6 17 2 4 6 3 16 -3 22 -6 6 -12 6 -17 -2z\"/>\n"
                + " <path d=\"M264 379 c-11 -18 26 -42 43 -27 13 10 10 23 -5 23 -4 0 -12 4 -19 8 -6 4 -15 2 -19 -4z\"/>\n"
                + " <path d=\"M435 381 c-3 -6 -10 -9 -15 -6 -15 9 -21 -12 -7 -24 19 -15 57 12 41 29 -9 9 -14 9 -19 1z\"/>\n"
                + " <path d=\"M197 363 c-4 -6 8 -27 27 -48 18 -21 35 -34 38 -29 3 5 -9 26 -27 48 -18 21 -35 34 -38 29z\"/>\n"
                + " <path d=\"M482 331 c-31 -38 -31 -39 -12 -56 18 -16 18 -19 3 -30 -11 -8 -13 -14 -5 -19 13 -8 52 10 52 23 0 14 -36 41 -46 34 -5 -5 0 3 11 17 41 49 46 58 37 64 -5 3 -23 -12 -40 -33z\"/>\n"
                + " <path d=\"M210 264 c-9 -10 -10 -18 -3 -25 8 -8 14 -5 22 9 13 25 -2 38 -19 16z\"/>\n"
                + " </g>\n"
                + "<g id=\"layer102\" fill=\"#fafcfc\" stroke=\"none\">\n"
                + " <path d=\"M184 435 c-9 -23 5 -66 33 -103 23 -31 24 -34 8 -58 -27 -42 2 -60 40 -24 18 17 26 18 58 9 25 -7 49 -7 74 0 32 9 40 8 60 -10 12 -12 29 -19 37 -16 21 8 20 20 -4 42 -19 17 -19 18 12 56 21 26 31 50 32 76 l1 38 -173 3 c-147 2 -173 0 -178 -13z\"/>\n"
                + " </g>\n"
                + "<g id=\"layer103\" fill=\"#33a89d\" stroke=\"none\">\n"
                + " <path d=\"M95 678 c-54 -29 -55 -33 -55 -319 l0 -265 28 -27 28 -27 265 0 265 0 27 28 27 28 0 265 c0 285 -1 289 -57 318 -30 15 -500 15 -528 -1z m422 -285 c-6 -21 -22 -53 -35 -72 l-25 -34 22 -16 c12 -9 19 -20 15 -24 -4 -4 -19 4 -34 18 -23 22 -29 24 -63 14 -25 -7 -49 -7 -74 0 -32 9 -40 8 -58 -9 -28 -26 -51 -26 -25 0 l21 21 -24 32 c-13 18 -28 49 -34 70 l-11 37 168 0 168 0 -11 -37z\"/>\n"
                + " <path d=\"M273 374 c-9 -23 11 -38 26 -20 13 16 7 36 -10 36 -5 0 -12 -7 -16 -16z\"/>\n"
                + " <path d=\"M415 380 c-10 -16 16 -43 28 -31 11 11 2 41 -12 41 -5 0 -13 -5 -16 -10z\"/>\n"
                + " </g>\n"
                + "\n"
                + "</svg>";
        FileWriter abcd = new FileWriter(image);
        abcd.write(str);
        abcd.close();

        File imager = new File(projectDir + "//app//src//main//res//mipmap-hdpi//ic_launcher_round.svg");
        String stri = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20010904//EN\" \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n"
                + "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"72px\" height=\"72px\" viewBox=\"0 0 720 720\" preserveAspectRatio=\"xMidYMid meet\">\n"
                + "<g id=\"layer101\" fill=\"#7bc6bf\" stroke=\"none\">\n"
                + " <path d=\"M157 463 c-4 -3 -7 -15 -7 -25 0 -11 -4 -17 -9 -13 -12 7 -24 -12 -16 -25 5 -8 12 -7 22 2 9 7 18 11 21 8 7 -7 21 47 14 54 -7 8 -17 7 -25 -1z\"/>\n"
                + " <path d=\"M547 464 c-3 -4 -2 -16 4 -26 13 -24 35 -9 26 16 -7 17 -19 21 -30 10z\"/>\n"
                + " <path d=\"M585 420 c-4 -6 -3 -16 3 -22 6 -6 12 -6 17 2 4 6 3 16 -3 22 -6 6 -12 6 -17 -2z\"/>\n"
                + " <path d=\"M285 390 c-4 -6 -10 -8 -15 -5 -5 3 -11 1 -14 -4 -4 -5 -2 -12 3 -15 5 -4 12 -2 16 4 4 6 10 8 15 5 5 -3 11 -1 14 4 4 5 2 12 -3 15 -5 4 -12 2 -16 -4z\"/>\n"
                + " <path d=\"M426 391 c-4 -5 -2 -12 4 -16 6 -4 8 -10 5 -15 -3 -5 -1 -11 4 -14 5 -4 12 -2 15 3 4 5 2 12 -4 16 -6 4 -8 10 -5 15 3 5 1 11 -4 14 -5 4 -12 2 -15 -3z\"/>\n"
                + " <path d=\"M125 330 c-4 -6 -3 -16 3 -22 6 -6 12 -6 17 2 4 6 3 16 -3 22 -6 6 -12 6 -17 -2z\"/>\n"
                + " <path d=\"M585 330 c-4 -6 -3 -16 3 -22 6 -6 12 -6 17 2 4 6 3 16 -3 22 -6 6 -12 6 -17 -2z\"/>\n"
                + " <path d=\"M284 269 c-3 -5 2 -12 11 -15 8 -4 14 -14 13 -23 -2 -9 3 -16 10 -16 6 0 11 3 10 7 -2 4 6 14 17 22 16 12 17 15 5 19 -37 12 -60 14 -66 6z\"/>\n"
                + " <path d=\"M415 273 c-47 -9 -51 -14 -30 -29 11 -8 19 -18 18 -22 -2 -4 3 -7 10 -7 6 0 11 6 9 14 -1 7 5 19 14 26 20 14 8 23 -21 18z\"/>\n"
                + " <path d=\"M305 141 c-7 -12 12 -24 25 -16 11 7 4 25 -10 25 -5 0 -11 -4 -15 -9z\"/>\n"
                + " <path d=\"M395 141 c-7 -12 12 -24 25 -16 11 7 4 25 -10 25 -5 0 -11 -4 -15 -9z\"/>\n"
                + " </g>\n"
                + "<g id=\"layer102\" fill=\"#fafcfb\" stroke=\"none\">\n"
                + " <path d=\"M172 458 c-21 -21 -13 -65 23 -118 l36 -53 -22 -23 c-22 -23 -20 -54 3 -54 6 0 24 11 38 25 25 24 31 25 71 16 29 -7 59 -7 88 0 41 9 46 8 72 -17 46 -44 81 -14 38 32 l-21 22 31 42 c17 23 33 58 37 76 11 61 3 64 -200 64 -128 0 -186 -4 -194 -12z\"/>\n"
                + " </g>\n"
                + "<g id=\"layer103\" fill=\"#31a79c\" stroke=\"none\">\n"
                + " <path d=\"M247 705 c-63 -17 -116 -53 -159 -107 -44 -54 -58 -80 -74 -143 -23 -85 -12 -193 26 -265 19 -36 98 -117 137 -141 74 -46 186 -60 280 -35 68 18 109 44 165 104 54 57 66 79 84 147 41 153 -24 326 -152 406 -76 46 -208 61 -307 34z m309 -271 c-3 -9 -9 -28 -13 -42 -3 -15 -19 -44 -36 -65 l-30 -38 23 -24 c33 -35 16 -44 -19 -11 -24 23 -32 26 -49 17 -27 -14 -107 -14 -134 0 -17 9 -25 6 -49 -17 -35 -33 -52 -24 -19 11 l23 24 -30 38 c-16 21 -34 51 -41 66 -25 58 -30 57 184 57 175 0 195 -2 190 -16z m-539 -91 c-3 -10 -5 -2 -5 17 0 19 2 27 5 18 2 -10 2 -26 0 -35z m690 0 c-3 -10 -5 -2 -5 17 0 19 2 27 5 18 2 -10 2 -26 0 -35z m-324 -330 c-13 -2 -33 -2 -45 0 -13 2 -3 4 22 4 25 0 35 -2 23 -4z\"/>\n"
                + " <path d=\"M263 384 c-3 -8 1 -22 9 -30 13 -13 16 -13 28 0 10 13 10 19 0 31 -16 19 -29 19 -37 -1z\"/>\n"
                + " <path d=\"M430 385 c-22 -27 12 -49 38 -23 8 8 9 15 1 25 -15 17 -24 16 -39 -2z\"/>\n"
                + " </g>\n"
                + "\n"
                + "</svg>";
        FileWriter abcde = new FileWriter(imager);
        abcde.write(stri);
        abcde.close();

        Boolean n = new File(projectName + "/app/src/main/res/mipmap-mdpi").mkdir();
        Boolean o = new File(projectName + "/app/src/main/res/mipmap-xhdpi").mkdir();
        Boolean p = new File(projectName + "/app/src/main/res/mipmap-xxhdpi").mkdir();
        Boolean q = new File(projectName + "/app/src/main/res/mipmap-xxxhdpi").mkdir();
        Boolean r = new File(projectName + "/app/src/main/res/values").mkdir();

        File sn = new File(projectDir + "//app//src//main//res//values//strings.xml");
        String content3 = "<resources>\n"
                + "    <string name=\"app_name\">My Application Java</string>\n"
                + "</resources>";

        FileWriter ae = new FileWriter(sn);
        ae.write(content3);
        ae.close();

        File sn1 = new File(projectDir + "//app//src//main//res//values//colors.xml");
        String content4 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<resources>\n"
                + "    <color name=\"colorPrimary\">#3F51B5</color>\n"
                + "    <color name=\"colorPrimaryDark\">#303F9F</color>\n"
                + "    <color name=\"colorAccent\">#FF4081</color>\n"
                + "</resources>";

        FileWriter ae1 = new FileWriter(sn1);
        ae1.write(content4);
        ae1.close();

        File sn2 = new File(projectDir + "//app//src//main//res//values//styles.xml");
        String content5 = "<resources>\n"
                + "\n"
                + "    <!-- Base application theme. -->\n"
                + "    <style name=\"AppTheme\" parent=\"Theme.AppCompat.Light.DarkActionBar\">\n"
                + "        <!-- Customize your theme here. -->\n"
                + "        <item name=\"colorPrimary\">@color/colorPrimary</item>\n"
                + "        <item name=\"colorPrimaryDark\">@color/colorPrimaryDark</item>\n"
                + "        <item name=\"colorAccent\">@color/colorAccent</item>\n"
                + "    </style>\n"
                + "\n"
                + "</resources>";

        FileWriter ae2 = new FileWriter(sn2);
        ae2.write(content5);
        ae2.close();

    }

    public void createDir(String name) {
        File dir = new File(name);
        if (!dir.exists()) {
            System.out.println("creating directory: " + dir.getName());
            boolean result = false;

            try {
                dir.mkdir();
                result = true;
            } catch (SecurityException e) {
                System.out.println("error: " + e.getMessage());
            }
            if (result) {
                System.out.println("Dir Created");
            }
        }
    }

    public void createFile(String dir, String name, String extension) throws FileNotFoundException, IOException {

        String data = "Test data";
        FileOutputStream out = new FileOutputStream(dir + name + "." + extension);
        //String currentDir = System.getProperty("user.dir");
        //System.out.print("CurrentDir: "+currentDir); 
        //out.write(data.getBytes());
        //out.close();

        File file = new File(dir + "/" + name + "." + extension);
        if (file.createNewFile()) {

        } else {
            System.out.println("error");
        }
    }

    private void createGradlefile(String buildGradleVersion, String dir, String projectName, String packageName)
            throws IOException {
        File f = new File(dir + "//" + projectName + "//build.gradle");
        String s = "// Top-level build file where you can add configuration options common to all sub-projects/modules.\n"
                + "\n"
                + "buildscript {\n"
                + "    \n"
                + "    repositories {\n"
                + "        google()\n"
                + "        jcenter()\n"
                + "    }\n"
                + "    dependencies {\n"
                + "        classpath 'com.android.tools.build:gradle:" + buildGradleVersion + "' \n"
                + "        \n"
                + "           //classpath 'com.google.gms:google-services:3.2.0'\n"
                + "\n"
                + "        // NOTE: Do not place your application dependencies here; they belong\n"
                + "        // in the individual module build.gradle files\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "\n"
                + "allprojects {\n"
                + "    repositories {\n"
                + "        google()\n"
                + "        jcenter()\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "\n"
                + "task clean(type: Delete) {\n"
                + "    delete rootProject.buildDir\n"
                + "}\n"
                + "task installDebug(type: Exec){\n"
                + "    commandLine 'cmd','installDebug'\n"
                + "}\n"
                + "\n"
                + "task openMain(type:Exec, dependsOn:installDebug){\n"
                + "    commandLine('cmd','/c','adb shell','am','start','-n','" + packageName + "/.MainActivity')\n"
                + "}	\n"
                + "\n"
                + "task cleanInstall(type:Exec, dependsOn:clean){\n"
                + "    commandLine 'cmd','build'\n"
                + "    commandLine 'cmd','installDebug'\n"
                + "}";
        FileWriter writer = new FileWriter(f);
        writer.write(s);
        writer.close();
    }

    private void createGradleProperties(String dir, String projectName) throws IOException {
        File f = new File(dir + "//" + projectName + "//gradle.properties");
        String s = "# Project-wide Gradle settings.\n"
                + "\n"
                + "# IDE (e.g. Android Studio) users:\n"
                + "# Gradle settings configured through the IDE *will override*\n"
                + "# any settings specified in this file.\n"
                + "\n"
                + "# For more details on how to configure your build environment visit\n"
                + "# http://www.gradle.org/docs/current/userguide/build_environment.html\n"
                + "\n"
                + "# Specifies the JVM arguments used for the daemon process.\n"
                + "# The setting is particularly useful for tweaking memory settings.\n"
                + "org.gradle.jvmargs=-Xmx1536m\n"
                + "\n"
                + "# When configured, Gradle will run in incubating parallel mode.\n"
                + "# This option should only be used with decoupled projects. More details, visit\n"
                + "# http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects\n"
                + "# org.gradle.parallel=true";
        FileWriter w = new FileWriter(f);
        w.write(s);
        w.close();
    }

    private void createGradlew(String dir, String projectName) throws IOException {
        File f = new File(dir + "//" + projectName + "//gradlew");
        String s = "#!/usr/bin/env bash\n"
                + "\n"
                + "##############################################################################\n"
                + "##\n"
                + "##  Gradle start up script for UN*X\n"
                + "##\n"
                + "##############################################################################\n"
                + "\n"
                + "# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.\n"
                + "DEFAULT_JVM_OPTS=\"\"\n"
                + "\n"
                + "APP_NAME=\"Gradle\"\n"
                + "APP_BASE_NAME=`basename \"$0\"`\n"
                + "\n"
                + "# Use the maximum available, or set MAX_FD != -1 to use that value.\n"
                + "MAX_FD=\"maximum\"\n"
                + "\n"
                + "warn ( ) {\n"
                + "    echo \"$*\"\n"
                + "}\n"
                + "\n"
                + "die ( ) {\n"
                + "    echo\n"
                + "    echo \"$*\"\n"
                + "    echo\n"
                + "    exit 1\n"
                + "}\n"
                + "\n"
                + "# OS specific support (must be 'true' or 'false').\n"
                + "cygwin=false\n"
                + "msys=false\n"
                + "darwin=false\n"
                + "case \"`uname`\" in\n"
                + "  CYGWIN* )\n"
                + "    cygwin=true\n"
                + "    ;;\n"
                + "  Darwin* )\n"
                + "    darwin=true\n"
                + "    ;;\n"
                + "  MINGW* )\n"
                + "    msys=true\n"
                + "    ;;\n"
                + "esac\n"
                + "\n"
                + "# Attempt to set APP_HOME\n"
                + "# Resolve links: $0 may be a link\n"
                + "PRG=\"$0\"\n"
                + "# Need this for relative symlinks.\n"
                + "while [ -h \"$PRG\" ] ; do\n"
                + "    ls=`ls -ld \"$PRG\"`\n"
                + "    link=`expr \"$ls\" : '.*-> \\(.*\\)$'`\n"
                + "    if expr \"$link\" : '/.*' > /dev/null; then\n"
                + "        PRG=\"$link\"\n"
                + "    else\n"
                + "        PRG=`dirname \"$PRG\"`\"/$link\"\n"
                + "    fi\n"
                + "done\n"
                + "SAVED=\"`pwd`\"\n"
                + "cd \"`dirname \\\"$PRG\\\"`/\" >/dev/null\n"
                + "APP_HOME=\"`pwd -P`\"\n"
                + "cd \"$SAVED\" >/dev/null\n"
                + "\n"
                + "CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar\n"
                + "\n"
                + "# Determine the Java command to use to start the JVM.\n"
                + "if [ -n \"$JAVA_HOME\" ] ; then\n"
                + "    if [ -x \"$JAVA_HOME/jre/sh/java\" ] ; then\n"
                + "        # IBM's JDK on AIX uses strange locations for the executables\n"
                + "        JAVACMD=\"$JAVA_HOME/jre/sh/java\"\n"
                + "    else\n"
                + "        JAVACMD=\"$JAVA_HOME/bin/java\"\n"
                + "    fi\n"
                + "    if [ ! -x \"$JAVACMD\" ] ; then\n"
                + "        die \"ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME\n"
                + "\n"
                + "Please set the JAVA_HOME variable in your environment to match the\n"
                + "location of your Java installation.\"\n"
                + "    fi\n"
                + "else\n"
                + "    JAVACMD=\"java\"\n"
                + "    which java >/dev/null 2>&1 || die \"ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.\n"
                + "\n"
                + "Please set the JAVA_HOME variable in your environment to match the\n"
                + "location of your Java installation.\"\n"
                + "fi\n"
                + "\n"
                + "# Increase the maximum file descriptors if we can.\n"
                + "if [ \"$cygwin\" = \"false\" -a \"$darwin\" = \"false\" ] ; then\n"
                + "    MAX_FD_LIMIT=`ulimit -H -n`\n"
                + "    if [ $? -eq 0 ] ; then\n"
                + "        if [ \"$MAX_FD\" = \"maximum\" -o \"$MAX_FD\" = \"max\" ] ; then\n"
                + "            MAX_FD=\"$MAX_FD_LIMIT\"\n"
                + "        fi\n"
                + "        ulimit -n $MAX_FD\n"
                + "        if [ $? -ne 0 ] ; then\n"
                + "            warn \"Could not set maximum file descriptor limit: $MAX_FD\"\n"
                + "        fi\n"
                + "    else\n"
                + "        warn \"Could not query maximum file descriptor limit: $MAX_FD_LIMIT\"\n"
                + "    fi\n"
                + "fi\n"
                + "\n"
                + "# For Darwin, add options to specify how the application appears in the dock\n"
                + "if $darwin; then\n"
                + "    GRADLE_OPTS=\"$GRADLE_OPTS \\\"-Xdock:name=$APP_NAME\\\" \\\"-Xdock:icon=$APP_HOME/media/gradle.icns\\\"\"\n"
                + "fi\n"
                + "\n"
                + "# For Cygwin, switch paths to Windows format before running java\n"
                + "if $cygwin ; then\n"
                + "    APP_HOME=`cygpath --path --mixed \"$APP_HOME\"`\n"
                + "    CLASSPATH=`cygpath --path --mixed \"$CLASSPATH\"`\n"
                + "    JAVACMD=`cygpath --unix \"$JAVACMD\"`\n"
                + "\n"
                + "    # We build the pattern for arguments to be converted via cygpath\n"
                + "    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`\n"
                + "    SEP=\"\"\n"
                + "    for dir in $ROOTDIRSRAW ; do\n"
                + "        ROOTDIRS=\"$ROOTDIRS$SEP$dir\"\n"
                + "        SEP=\"|\"\n"
                + "    done\n"
                + "    OURCYGPATTERN=\"(^($ROOTDIRS))\"\n"
                + "    # Add a user-defined pattern to the cygpath arguments\n"
                + "    if [ \"$GRADLE_CYGPATTERN\" != \"\" ] ; then\n"
                + "        OURCYGPATTERN=\"$OURCYGPATTERN|($GRADLE_CYGPATTERN)\"\n"
                + "    fi\n"
                + "    # Now convert the arguments - kludge to limit ourselves to /bin/sh\n"
                + "    i=0\n"
                + "    for arg in \"$@\" ; do\n"
                + "        CHECK=`echo \"$arg\"|egrep -c \"$OURCYGPATTERN\" -`\n"
                + "        CHECK2=`echo \"$arg\"|egrep -c \"^-\"`                                 ### Determine if an option\n"
                + "\n"
                + "        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition\n"
                + "            eval `echo args$i`=`cygpath --path --ignore --mixed \"$arg\"`\n"
                + "        else\n"
                + "            eval `echo args$i`=\"\\\"$arg\\\"\"\n"
                + "        fi\n"
                + "        i=$((i+1))\n"
                + "    done\n"
                + "    case $i in\n"
                + "        (0) set -- ;;\n"
                + "        (1) set -- \"$args0\" ;;\n"
                + "        (2) set -- \"$args0\" \"$args1\" ;;\n"
                + "        (3) set -- \"$args0\" \"$args1\" \"$args2\" ;;\n"
                + "        (4) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" ;;\n"
                + "        (5) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" ;;\n"
                + "        (6) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" ;;\n"
                + "        (7) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" ;;\n"
                + "        (8) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" \"$args7\" ;;\n"
                + "        (9) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" \"$args7\" \"$args8\" ;;\n"
                + "    esac\n"
                + "fi\n"
                + "\n"
                + "# Split up the JVM_OPTS And GRADLE_OPTS values into an array, following the shell quoting and substitution rules\n"
                + "function splitJvmOpts() {\n"
                + "    JVM_OPTS=(\"$@\")\n"
                + "}\n"
                + "eval splitJvmOpts $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS\n"
                + "JVM_OPTS[${#JVM_OPTS[*]}]=\"-Dorg.gradle.appname=$APP_BASE_NAME\"\n"
                + "\n"
                + "exec \"$JAVACMD\" \"${JVM_OPTS[@]}\" -classpath \"$CLASSPATH\" org.gradle.wrapper.GradleWrapperMain \"$@\"";
        FileWriter w = new FileWriter(f);
        w.write(s);
        w.close();
    }

    private void createGradlewbat(String dir, String projectName) throws IOException {
        File f = new File(dir + "//" + projectName + "//gradlew.bat");
        String s = "@if \"%DEBUG%\" == \"\" @echo off\n"
                + "@rem ##########################################################################\n"
                + "@rem\n"
                + "@rem  Gradle startup script for Windows\n"
                + "@rem\n"
                + "@rem ##########################################################################\n"
                + "\n"
                + "@rem Set local scope for the variables with windows NT shell\n"
                + "if \"%OS%\"==\"Windows_NT\" setlocal\n"
                + "\n"
                + "@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.\n"
                + "set DEFAULT_JVM_OPTS=\n"
                + "\n"
                + "set DIRNAME=%~dp0\n"
                + "if \"%DIRNAME%\" == \"\" set DIRNAME=.\n"
                + "set APP_BASE_NAME=%~n0\n"
                + "set APP_HOME=%DIRNAME%\n"
                + "\n"
                + "@rem Find java.exe\n"
                + "if defined JAVA_HOME goto findJavaFromJavaHome\n"
                + "\n"
                + "set JAVA_EXE=java.exe\n"
                + "%JAVA_EXE% -version >NUL 2>&1\n"
                + "if \"%ERRORLEVEL%\" == \"0\" goto init\n"
                + "\n"
                + "echo.\n"
                + "echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.\n"
                + "echo.\n"
                + "echo Please set the JAVA_HOME variable in your environment to match the\n"
                + "echo location of your Java installation.\n"
                + "\n"
                + "goto fail\n"
                + "\n"
                + ":findJavaFromJavaHome\n"
                + "set JAVA_HOME=%JAVA_HOME:\"=%\n"
                + "set JAVA_EXE=%JAVA_HOME%/bin/java.exe\n"
                + "\n"
                + "if exist \"%JAVA_EXE%\" goto init\n"
                + "\n"
                + "echo.\n"
                + "echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%\n"
                + "echo.\n"
                + "echo Please set the JAVA_HOME variable in your environment to match the\n"
                + "echo location of your Java installation.\n"
                + "\n"
                + "goto fail\n"
                + "\n"
                + ":init\n"
                + "@rem Get command-line arguments, handling Windowz variants\n"
                + "\n"
                + "if not \"%OS%\" == \"Windows_NT\" goto win9xME_args\n"
                + "if \"%@eval[2+2]\" == \"4\" goto 4NT_args\n"
                + "\n"
                + ":win9xME_args\n"
                + "@rem Slurp the command line arguments.\n"
                + "set CMD_LINE_ARGS=\n"
                + "set _SKIP=2\n"
                + "\n"
                + ":win9xME_args_slurp\n"
                + "if \"x%~1\" == \"x\" goto execute\n"
                + "\n"
                + "set CMD_LINE_ARGS=%*\n"
                + "goto execute\n"
                + "\n"
                + ":4NT_args\n"
                + "@rem Get arguments from the 4NT Shell from JP Software\n"
                + "set CMD_LINE_ARGS=%$\n"
                + "\n"
                + ":execute\n"
                + "@rem Setup the command line\n"
                + "\n"
                + "set CLASSPATH=%APP_HOME%\\gradle\\wrapper\\gradle-wrapper.jar\n"
                + "\n"
                + "@rem Execute Gradle\n"
                + "\"%JAVA_EXE%\" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% \"-Dorg.gradle.appname=%APP_BASE_NAME%\" -classpath \"%CLASSPATH%\" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%\n"
                + "\n"
                + ":end\n"
                + "@rem End local scope for the variables with windows NT shell\n"
                + "if \"%ERRORLEVEL%\"==\"0\" goto mainEnd\n"
                + "\n"
                + ":fail\n"
                + "rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of\n"
                + "rem the _cmd.exe /c_ return code!\n"
                + "if  not \"\" == \"%GRADLE_EXIT_CONSOLE%\" exit 1\n"
                + "exit /b 1\n"
                + "\n"
                + ":mainEnd\n"
                + "if \"%OS%\"==\"Windows_NT\" endlocal\n"
                + "\n"
                + ":omega";
        FileWriter w = new FileWriter(f);
        w.write(s);
        w.close();
    }

    private void createlocalProperties(String dir, String projectName) throws IOException {
        File f = new File(dir + "//" + projectName + "//local.properties");
        String sdk = System.getenv("ANDROID_HOME").replace("\\", "\\\\").replace(":", "\\:");
        String s = "## This file is automatically generated by Android Studio.\n"
                + "# Do not modify this file -- YOUR CHANGES WILL BE ERASED!\n"
                + "#\n"
                + "# This file should *NOT* be checked into Version Control Systems,\n"
                + "# as it contains information specific to your local configuration.\n"
                + "#\n"
                + "# Location of the SDK. This is only used by Gradle.\n"
                + "# For customization when using a Version Control System, please read the\n"
                + "# header note.\n"
                + "sdk.dir=" + sdk;
        FileWriter w = new FileWriter(f);
        w.write(s);
        w.close();
    }

    private void createsettingsGradle(String dir, String projectName) throws IOException {
        File f = new File(dir + "//" + projectName + "//settings.gradle");
        String s = "include ':app'";
        FileWriter w = new FileWriter(f);
        w.write(s);
        w.close();
    }

    private void javaPackage(String projectName, String packageName) {

    }

    private void createMainActivity(String dir, String packageName, String main, String layout) throws IOException {

        File f = new File(dir + main + ".java");
        String content = "package " + packageName + ";\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "import android.os.Bundle;\n"
                + "\n"
                + "public class " + main + " extends Activity {\n"
                + "\n"
                + "    @Override\n"
                + "    protected void onCreate(Bundle savedInstanceState) {\n"
                + "        super.onCreate(savedInstanceState);\n"
                + "        setContentView(R.layout." + layout + ");\n"
                + "    }\n"
                + "}";

        FileWriter a = new FileWriter(f);
        a.write(content);
        a.close();

    }
}
