package br.com.introcdc.mapmeelv4.bases;

import br.com.introcdc.mapmeelv4.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MapClassGetter {




    public static void loadCommands(final String packageName, final Class Class) {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fCarregando comandos...");
        for (final Class<?> theClass : MapClassGetter.getClassesForPackage(packageName, Class)) {
            if (CommandBase.class.isAssignableFrom(theClass)) {
                try {
                    final CommandBase command = (CommandBase) theClass.newInstance();
                    ((org.bukkit.craftbukkit.v1_12_R1.CraftServer) Bukkit.getServer()).getCommandMap().register(command.getName(), command);
                    Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fComando §a" + command.getName() + "§f carregado com sucesso!");
                } catch (final Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void loadListeners(final String packageName, final Class Class) {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fCarregando listeners...");
        for (final Class<?> theClass : MapClassGetter.getClassesForPackage(packageName, Class)) {
            if (Listener.class.isAssignableFrom(theClass)) {
                try {
                    Bukkit.getPluginManager().registerEvents((Listener) theClass.newInstance(), MapUtils.getPlugin());
                    Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fListener §a" + theClass.getName() + "§f carregado com sucesso!");
                } catch (final Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<Class<?>> getClassesForPackage(String pkgname, Class clazz) {
        ArrayList<Class<?>> classes = new ArrayList<>();
        // String relPath = pkgname.replace('.', '/');

        // Get a File object for the package
        CodeSource src = clazz.getProtectionDomain().getCodeSource();
        if (src != null) {
            URL resource = src.getLocation();
            resource.getPath();
            processJarfile(resource, pkgname, classes);
        }
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Class<?>> classi = new ArrayList<>();
        for (Class<?> classy : classes) {
            names.add(classy.getSimpleName());
            classi.add(classy);
        }
        classes.clear();
        names.sort(String.CASE_INSENSITIVE_ORDER);
        for (String s : names)
            for (Class<?> classy : classi) {
                if (classy.getSimpleName().equals(s)) {
                    classes.add(classy);
                    break;
                }
            }
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        }
    }

    private static void processJarfile(URL resource, String pkgname, ArrayList<Class<?>> classes) {
        String relPath = pkgname.replace('.', '/');
        String resPath = resource.getPath().replace("%20", " ");
        String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile;
        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException reading JAR File '" + jarPath + "'", e);
        }
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            String className = null;
            if (entryName.endsWith(".class") && entryName.startsWith(relPath)
                    && entryName.length() > (relPath.length() + "/".length())) {
                className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
            }
            if (className != null) {
                classes.add(loadClass(className));
            }
        }
    }

}
