package br.com.introcdc.mapmeelv4.bases;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import br.com.introcdc.mapmeelv4.MapUtils;

public class MapClassGetter {

    public static ArrayList<Class<?>> getClassesForPackage(final String pkgname, final Class Class) {
        final ArrayList<Class<?>> classes = new ArrayList<>();
        final CodeSource src = Class.getProtectionDomain().getCodeSource();
        if (src != null) {
            final URL resource = src.getLocation();
            resource.getPath();
            MapClassGetter.processJarfile(resource, pkgname, classes);
        }
        final ArrayList<String> names = new ArrayList<>();
        final ArrayList<Class<?>> classi = new ArrayList<>();
        for (final Class<?> classy : classes) {
            names.add(classy.getSimpleName());
            classi.add(classy);
        }
        classes.clear();
        names.sort(String.CASE_INSENSITIVE_ORDER);
        for (final String s : names) {
            for (final Class<?> classy : classi) {
                if (classy.getSimpleName().equals(s)) {
                    classes.add(classy);
                    break;
                }
            }
        }
        return classes;
    }

    private static Class<?> loadClass(final String className) {
        try {
            return Class.forName(className);
        } catch (final ClassNotFoundException e) {
            throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        }
    }

    public static void loadCommands(final String packageName, final Class Class) {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fCarregando comandos...");
        for (final Class<?> classes : MapClassGetter.getClassesForPackage(packageName, Class)) {
            try {
                final CommandBase command = (CommandBase) classes.newInstance();
                ((org.bukkit.craftbukkit.v1_12_R1.CraftServer) Bukkit.getServer()).getCommandMap().register(command.getName(), command);
                Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fComando §a" + command.getName() + "§f carregado com sucesso!");
            } catch (final Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void loadListeners(final String packageName, final Class Class) {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fCarregando listeners...");
        for (final Class<?> classes : MapClassGetter.getClassesForPackage(packageName, Class)) {
            try {
                Bukkit.getPluginManager().registerEvents((Listener) classes.newInstance(), MapUtils.getPlugin());
                Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fListener §a" + classes.getName() + "§f carregado com sucesso!");
            } catch (final Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private static void processJarfile(final URL resource, final String pkgname, final ArrayList<Class<?>> classes) {
        final String relPath = pkgname.replace('.', '/');
        final String resPath = resource.getPath().replace("%20", " ");
        final String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile;
        try {
            jarFile = new JarFile(jarPath);
        } catch (final IOException e) {
            throw new RuntimeException("Unexpected IOException reading JAR File '" + jarPath + "'", e);
        }
        final Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            final JarEntry entry = entries.nextElement();
            final String entryName = entry.getName();
            String className = null;
            if (entryName.endsWith(".class") && entryName.startsWith(relPath) && entryName.length() > relPath.length() + "/".length()) {
                className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
            }
            if (className != null) {
                classes.add(MapClassGetter.loadClass(className));
            }
        }
        try {
            jarFile.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
