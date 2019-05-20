package br.com.introcdc.mapmeelv4.serjao;
/*
  Written by IntroCDC, Bruno Coêlho at 29/08/2017 - 11:23
 */

import br.com.introcdc.mapmeelv4.utils.MapUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SerjaoInteract {

    /**
     * Get all messages files
     *
     * @param file the file to get all messages
     * @return List<String> with responses
     */
    static List<String> getAllMessages(File file) {
        List<String> replies = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null) {
                replies.add(str);
            }
            in.close();
        } catch (Exception ignored) {
        }

        return replies;
    }

    /**
     * Get SkypeBot folder
     *
     * @return the skypeBot file folder string
     */
    static String getFolder() {
        return System.getProperty("skypeBotInteract", "/home/SkypeBot");
    }

    /**
     * Get random file from folder with files responses
     *
     * @return random response file
     */
    static File getRandomMessageFile() {
        List<File> allMessagesFile = getAllMessagesFile();
        return allMessagesFile.get(MapUtils.random.nextInt(allMessagesFile.size()));
    }

    /**
     * List all messages files
     */
    static List<File> getAllMessagesFile() {
        return Arrays.asList(new File(getFolder() + "/words/").listFiles());
    }

    /**
     * Get random file from folder
     *
     * @return random response file
     */
    static File getRandomFile() {
        List<File> images = new ArrayList<>();
        for (File ss : new File(getFolder() + "/files/").listFiles()) {
            if (ss.isFile()) {
                images.add(ss);
            }
        }
        if (images.size() > 0) {
            return images.get(MapUtils.random.nextInt(images.size()));
        } else {
            return new File(getFolder() + "/SkypeBot.jar");
        }
    }

    /**
     * Get random image from folder
     *
     * @return Random image file from images folder
     */
    static File getRandomImage() {
        List<File> images = new ArrayList<>();
        for (File ss : new File(getFolder() + "/images/").listFiles()) {
            if (ss.isFile()) {
                images.add(ss);
            }
        }
        if (images.size() > 0) {
            return images.get(MapUtils.random.nextInt(images.size()));
        } else {
            return null;
        }
    }

    /**
     * Get random message from response file
     *
     * @param file the file to get random message
     * @return Random response from file response
     */
    static String getRandomMessage(File file) {
        List<String> messages = getAllMessages(file);
        return messages.get(MapUtils.random.nextInt(messages.size()));
    }

    static String removeEventTags(String message) {
        for (String lol : new String[]{"%user.username%", "%contact.name%", "%contact.username%", "%picture%",
                "%topic.new%", "%topic.old%", "%message%", "%action.sendimage%", "%action.sendfile%",
                "%action.sendcontact%", "%action.group.changetopic%", "%action.group.changeimage%",
                "%action.group.adduser%", "%action.group.leave%", "%action.group.requestcontact%",
                "%action.user.block%", "%action.user.unblock%", "%intro%"}) {
            message = message.replace(lol, "");
        }
        return message;
    }

}
