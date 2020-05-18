package br.com.introcdc.mapmeelv4.serjao;
/*
  Written by IntroCDC, Bruno Coêlho at 29/08/2017 - 11:23
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface SerjaoInteract {

    /**
     * Remove all bad and suggestive words
     */
    boolean FAMILY_FRIENDLY = true;

    /**
     * Log system
     */
    boolean LOG = false;

    /**
     * Just simple Random variable
     */
    Random RANDOM = new Random();

    /**
     * Configs
     */
    String PRIMARY_FOLDER = "/home/SkypeBot";
    String WORDS_FOLDER = System.getProperty("wordsFolder", "words");

    /**
     * Get Bot Primary Folder
     *
     * @return The bot file folder path string
     */
    static String getFolder() {
        return System.getProperty("skypeBotInteract", PRIMARY_FOLDER);
    }

    /**
     * List all files from bot primary folder
     */
    static List<File> getAllFiles(String folder) {
        return Arrays.asList(new File(getFolder() + "/" + folder + "/").listFiles());
    }

    /**
     * List all messages files
     */
    static List<File> getAllMessagesFiles() {
        return getAllFiles(WORDS_FOLDER);
    }

    /**
     * Get all messages files
     *
     * @param file the file to get all messages
     * @return List<String> with responses
     */
    static List<String> getAllMessages(File file, boolean filter) {
        List<String> replies = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null) {
                replies.add(str);
            }
            in.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return familyFriendlyFilter(replies, filter);
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
        List<String> messages = getAllMessages(file, true);
        if (messages.isEmpty()) {
            return null;
        }
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
        message = removeAcents(message).toLowerCase();
        message = removeMultipleChars(message);
        message = removeEventTags(message);

        if (LOG)
            System.out.println("Procurando resposta para '" + message + "'...");

        List<File> possible = new ArrayList<>();
        for (File file : getAllMessagesFiles()) {
            if (message.toLowerCase().contains(file.getName().replace(".txt", "").toLowerCase())) {
                possible.add(file);
            }
        }
        if (!possible.isEmpty()) {
            File selected = possible.get(RANDOM.nextInt(possible.size()));
            if (LOG)
                System.out
                        .println("Retornando resposta para a palavra '" + selected.getName().replace(".txt", "") + "'");
            return getRandomMessage(selected);
        }
        return superRandomMessage();
    }

    /**
     * Learn new reply to word
     */
    static void learn(String key, String newReply) {
        key = removeAcents(key).toLowerCase();
        key = removeMultipleChars(key);
        key = removeEventTags(key);
        key = removeOtherCharacters(key);
        if (key.length() <= 1) {
            return;
        }

        if (LOG)
            System.out.println("Aprendendo a resposta '" + newReply + "' para a palavra '" + key + "'...");

        File file = new File(new File(getFolder() + "/" + WORDS_FOLDER), key + ".txt");

        List<String> replies = new ArrayList<>();
        if (file.exists()) {
            replies.addAll(getAllMessages(file, false));
        }
        replies.add(newReply);

        replies.removeIf(s -> removeMultipleChars(s).equalsIgnoreCase("?"));

        try {
            Files.write(file.toPath(), replies, StandardCharsets.UTF_8);
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
        if (message != null) {
            for (String lol : new String[]{"%user.username%", "%contact.name%", "%contact.username%", "%picture%",
                    "%topic.new%", "%topic.old%", "%message%", "%action.sendimage%", "%action.sendfile%",
                    "%action.sendcontact%", "%action.group.changetopic%", "%action.group.changeimage%",
                    "%action.group.adduser%", "%action.group.leave%", "%action.group.requestcontact%",
                    "%action.user.block%", "%action.user.unblock%", "%intro%", "%user.name%"}) {
                message = message.replace(lol, "");
            }
        }
        return message;
    }

    /**
     * Remove multiple chars
     */
    static String removeMultipleChars(String message) {
        StringBuilder result = new StringBuilder();
        String lastChar = null;
        for (char Char : message.toCharArray()) {
            String string = String.valueOf(Char);
            if (lastChar == null) {
                result.append(string);
            } else {
                if (!lastChar.equals(string)) {
                    result.append(string);
                }
            }
            lastChar = string;
        }
        return result.toString();
    }

    /**
     * Remove acents from string
     */
    static String removeAcents(String message) {
        return Normalizer.normalize(message, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    /**
     * Remove other characters from string
     */
    static String removeOtherCharacters(String message) {
        return message.replace("!", "").replace(",", "").replace(".", "").replace("@", "").replace("?", "")
                .replace("#", "").replace("$", "").replace("%", "").replace("¨", "").replace("~", "").replace("&", "")
                .replace("/", "").replace("\\", "").replace("*", "").replace("+", "").replace("-", "").replace("'", "")
                .replace("\"", "").replace("^", "").replace("_", "").replace("=", "").replace(";", "").replace(":", "")
                .replace("(", "").replace(")", "").replace("[", "").replace("]", "").replace("{", "").replace("}", "");
    }

    /**
     * Remove all bad and suggestive words if FAMILY_FRIENDLY is enabled
     */
    @SuppressWarnings("unused")
    static List<String> familyFriendlyFilter(List<String> replies, boolean filter) {
        List<String> toReply = new ArrayList<>();
        if (FAMILY_FRIENDLY && filter) {
            List<String> blockedWords = Arrays.asList(

                    "gostos", "carai", "krl", "crl", "karai", "vsf", "pnc", "foda", "xvide", "porn", "xnx", "sex",
                    "khalifa", "buce", "red", "69", "fdm", "fdp", "fud", "cag", "banan", "pau", "arombad", "gay",
                    "viad", "bixa", "pora", "poha", "caral", "fela", ".com", ".br", ".me", "intro", "kindome", "lix",
                    "@", "ip", "deruba", "kid", "bota", "vara", "garai", "baicet", "pairo", "escro", "put", "bost",
                    "host", "mine", "menes", "servi", "serve", "pegad", "sombra", "http", "caindo", "dou", "cu", "toma",
                    "bumbum", "estupr", "estru", "mama", "trojan", "diabo", "pal", "flooda", "maconha", "pika", "pica",
                    "vagina", "penis", "piroca", "kick", "fumei", "bek", "wtf", "crack", "otario"

            );
            for (String rawMessage : replies) {

                String message = removeAcents(rawMessage.toLowerCase()).replace("1", "i").replace("3", "e")
                        .replace("4", "a").replace("5", "s").replace("0", "o").replace("!", "").replace("?", "")
                        .replace("&", "").replace("/", "").replace("\\", "").replace("*", "").replace("+", "")
                        .replace("-", "").replace("_", "").replace("=", "").replace(" ", "");

                for (String alphabet : alphabetList) {
                    while (message.contains(alphabet + alphabet)) {
                        message = message.replace(alphabet + alphabet, alphabet);
                    }
                }

                boolean ok = true;
                for (String blocked : blockedWords) {
                    if (message.contains(blocked)) {
                        if (LOG)
                            System.out.println("Bloqueado: " + rawMessage + " - " + blocked);
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    if (rawMessage.length() > 1) {
                        toReply.add(rawMessage.substring(0, 1).toUpperCase() + rawMessage.substring(1).toLowerCase());
                    }
                }

            }
        } else {
            toReply.addAll(replies);
        }
        return toReply;
    }

    String[] alphabetList = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

}
