package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String uniencrypt(String message, int key){
        String encryptedmsg = new String();
        for(int i = 0; i < message.length(); i++){
            char encryptedchar = (char)((int)(message.charAt(i)) + key);
            encryptedmsg += encryptedchar;
            /*
            boolean isNotAlpha = true;
            for(int j = 97;j <= 122; j++ ) {
                if (message.charAt(i) == (char)j){
                    char encryptedchar;
                    if(j >= 122 - key){
                        int temp = 122 - 97 - key;
                        encryptedchar = (char)(j - temp - 1);
                    }else{
                        encryptedchar = (char)(j + key);
                    }
                    encryptedmsg += encryptedchar;
                    isNotAlpha = false;
                    break;
                }
            }
            if(isNotAlpha){
                encryptedmsg += message.charAt(i);
            }*/
        }
        return encryptedmsg;
    }
    static String unidecrypt(String message, int key){
        String decryptedmsg = new String();
        for(int i = 0; i < message.length(); i++){
            char decryptedchar = (char)((int)(message.charAt(i)) - key);
            decryptedmsg += decryptedchar;

        }
        return decryptedmsg;
    }
    static String shiftencrypt(String message, int key){
        String encryptedmsg = new String();
        for(int i = 0; i < message.length(); i++){
            boolean isNotAlpha = true;
            //for lowercase
            for(int j = 97;j <= 122; j++ ) {
                if (message.charAt(i) == (char)j){
                    char encryptedchar;
                    if(j >= 122 - key){
                        int temp = 122 - 97 - key;
                        encryptedchar = (char)(j - temp - 1);
                    }else{
                        encryptedchar = (char)(j + key);
                    }
                    encryptedmsg += encryptedchar;
                    isNotAlpha = false;
                    break;
                }
            }
            //for uppercase
            for(int j = 65; j <= 90; j++){
                if (message.charAt(i) == (char)j){
                    char encryptedchar;
                    if(j >= 90 - key){
                        int temp = 90 - 65 - key;
                        encryptedchar = (char)(j - temp - 1);
                    }else{
                        encryptedchar = (char)(j + key);
                    }
                    encryptedmsg += encryptedchar;
                    isNotAlpha = false;
                    break;
                }
            }
            if(isNotAlpha){
                encryptedmsg += message.charAt(i);
            }
        }
        return encryptedmsg;
    }
    static String shiftdecrypt(String message, int key){
        String decryptedmsg = new String();
        for(int i = 0; i < message.length(); i++){
            boolean isNotAlpha = true;
            //for lowercase
            for(int j = 97;j <= 122; j++ ) {
                if (message.charAt(i) == (char)j){
                    char encryptedchar;
                    if(j - key < 97){
                        int temp = 122 - (97 - (j - key));
                        encryptedchar = (char)(temp + 1);
                    }else{
                        encryptedchar = (char)(j - key);
                    }
                    decryptedmsg += encryptedchar;
                    isNotAlpha = false;
                    break;
                }
            }
            //for uppercase
            for(int j = 65; j <= 90; j++){
                if (message.charAt(i) == (char)j){
                    char encryptedchar;
                    if(j - key < 65){
                        int temp = 90 - (65 - (j - key));
                        encryptedchar = (char)(temp + 1);
                    }else{
                        encryptedchar = (char)(j - key);
                    }
                    decryptedmsg += encryptedchar;
                    isNotAlpha = false;
                    break;
                }
            }
            if(isNotAlpha){
                decryptedmsg += message.charAt(i);
            }
        }
        return decryptedmsg;
    }
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //Using CmdLine
        String options = "enc"; int key = 0; String message = ""; String infile = "", outfile = ""; String alg = "shift";
        boolean In = true; boolean usefile = false;

        for(int i = 0; i < args.length; i+=2){
            if(args[i].equalsIgnoreCase("-mode")){
                options = args[i+1];
            }
            if(args[i].equalsIgnoreCase("-key")){
                key = Integer.parseInt(args[i+1]);
            }
            if(args[i].equalsIgnoreCase("-data")){
                message = args[i+1];
                In = false;
            }
            if(In && args[i].equalsIgnoreCase("-in")){
                infile = args[i+1];
                try {
                    File myFile = new File(infile);
                    Scanner scanner = new Scanner(myFile);
                    while(scanner.hasNextLine()){
                        message = scanner.nextLine();
                    }
                    scanner.close();
                } catch(FileNotFoundException e){
                    System.out.println("An error occured.");
                    e.printStackTrace();
                }

            }
            if(args[i].equalsIgnoreCase("-out")){
                outfile = args[i+1];
                usefile = true;
            }
            if(args[i].equalsIgnoreCase("-alg")){
                alg = args[i+1];
            }
        }

        boolean enc = false, dec = false;
        if(options.equalsIgnoreCase("enc")){
            enc = true;
        }
        if(options.equalsIgnoreCase("dec")){
            dec = true;
        }

        if(!usefile) {
            if (enc && alg.equalsIgnoreCase("unicode")) {
                String encryptedmsg = uniencrypt(message, key);
                System.out.println(encryptedmsg);
            }
            if (dec && alg.equalsIgnoreCase("unicode")) {
                String decryptedmsg = unidecrypt(message, key);
                System.out.println(decryptedmsg);
            }
            if (enc && alg.equalsIgnoreCase("shift")) {
                String encryptedmsg = shiftencrypt(message, key);
                System.out.println(encryptedmsg);
            }
            if (dec && alg.equalsIgnoreCase("shift")) {
                String decryptedmsg = shiftdecrypt(message, key);
                System.out.println(decryptedmsg);
            }
        }else{
            try {
                File myFile = new File(outfile);
                FileWriter myWriter = new FileWriter(outfile);
                if (myFile.createNewFile()) {
                    //System.out.println("File created: " + myFile.getName());
                } else {
                    //System.out.println("File already exists.");
                }

                if (enc && alg.equalsIgnoreCase("unicode")) {
                    String encryptedmsg = uniencrypt(message, key);
                    myWriter.write(encryptedmsg);
                }
                if (dec && alg.equalsIgnoreCase("unicode")) {
                    String decryptedmsg = unidecrypt(message, key);
                    myWriter.write(decryptedmsg);
                }
                if (enc && alg.equalsIgnoreCase("shift")) {
                    String encryptedmsg = shiftencrypt(message, key);
                    myWriter.write(encryptedmsg);
                }
                if (dec && alg.equalsIgnoreCase("shift")) {
                    String decryptedmsg = shiftdecrypt(message, key);
                    myWriter.write(decryptedmsg);
                }
                myWriter.close();

            } catch(IOException e){
                System.out.println("An error occured.");
                e.printStackTrace();
            }
        }
    }
}
