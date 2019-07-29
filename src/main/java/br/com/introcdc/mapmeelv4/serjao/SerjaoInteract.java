package br.com.introcdc.mapmeelv4.serjao;
/*
  Written by IntroCDC, Bruno Coêlho at 29/08/2017 - 11:23
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface SerjaoInteract {

    Random RANDOM = new Random();

    /**
     * Get Bot folder
     *
     * @return the bot file folder string
     */
    static String getFolder() {
        return System.getProperty("skypeBotInteract", "/home/SkypeBot");
    }

    /**
     * List all messages files
     */
    static List<File> getAllFiles(String folder) {
        return Arrays.asList(new File(getFolder() + "/" + folder + "/").listFiles());
    }

    /**
     * List all messages files
     */
    static List<File> getAllMessagesFiles() {
        return getAllFiles("words");
    }

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
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return replies;
    }

    /**
     * Get random file from folder with files responses
     *
     * @return random response file
     */
    static File getRandomMessageFile() {
        List<File> allMessagesFiles = getAllMessagesFiles();
        return allMessagesFiles.get(RANDOM.nextInt(allMessagesFiles.size()));
    }

    /**
     * Get random message from response file
     *
     * @param file the file to get random message
     * @return Random response from file response
     */
    static String getRandomMessage(File file) {
        List<String> messages = getAllMessages(file);
        return messages.get(RANDOM.nextInt(messages.size()));
    }

    /**
     * List all images files
     */
    static List<File> getAllImagesFiles() {
        return getAllFiles("images");
    }

    /**
     * Get random image file from folder
     *
     * @return random image file
     */
    static File getRandomImageFile() {
        List<File> allImagesFiles = getAllImagesFiles();
        return allImagesFiles.get(RANDOM.nextInt(allImagesFiles.size()));
    }

    /**
     * List all files files
     */
    static List<File> getAllFileFiles() {
        return getAllFiles("files");
    }

    /**
     * Get random files file from folder
     *
     * @return random files file
     */
    static File getRandomFileFile() {
        List<File> allFilesFiles = getAllImagesFiles();
        return allFilesFiles.get(RANDOM.nextInt(allFilesFiles.size()));
    }

    /**
     * Get reply to message
     */
    static String replyTo(String message) {
        message = Normalizer.normalize(message, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        File selected = null;
        for (File file : getAllMessagesFiles()) {
            if (message.contains(file.getName().replace(".txt", ""))) {
                if (selected == null || selected.getName().length() < file.getName().length()) {
                    selected = file;
                }
            }
        }
        if (selected != null) {
            return getRandomMessage(selected);
        }
        return null;
    }

    /**
     * Learn new reply to word
     */
    static void learn(String key, String newReply) {
        key = Normalizer.normalize(key, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        if (key.length() <= 3) {
            return;
        }

        File file = new File(new File(getFolder() + "/words"), key + ".txt");

        List<String> replies = new ArrayList<>();
        if (file.exists()) {
            replies.addAll(getAllMessages(file));
        }
        replies.add(newReply);

        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (String line : replies) {
                printWriter.println(line);
            }
            printWriter.flush();
            printWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * SUPER RANDOM
     */
    static String superRandomMessage() {
        return removeEventTags(getRandomMessage(getRandomMessageFile()));
    }

    /**
     * Remove events messages from message
     */
    static String removeEventTags(String message) {
        for (String lol : new String[]{"%user.username%", "%contact.name%", "%contact.username%", "%picture%",
                "%topic.new%", "%topic.old%", "%message%", "%action.sendimage%", "%action.sendfile%",
                "%action.sendcontact%", "%action.group.changetopic%", "%action.group.changeimage%",
                "%action.group.adduser%", "%action.group.leave%", "%action.group.requestcontact%",
                "%action.user.block%", "%action.user.unblock%", "%intro%", "%user.name%"}) {
            message = message.replace(lol, "");
        }
        return message;
    }

}
