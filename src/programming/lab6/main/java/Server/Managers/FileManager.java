package Server.Managers;
import Common.Utility.ConsoleFormat;

import Server.ConsoleOperations.Console;
import Common.Exceptions.ExitPoint;
import Server.ConsoleOperations.Printable;
import Server.Managers.Converter.CompactQueueConverter;
import Common.Models.Organization;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.security.AnyTypePermission;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;

public class FileManager {
    private String text;
    private XStream xStream = new XStream();
    private Printable console;
    private String filepath;
    //private UserInput input;




    public FileManager(String filepath,Console console) {

        this.console = console;
        this.filepath = filepath;


        this.xStream.alias("Organization", Organization.class);
        this.xStream.alias("Collection",ArrayDeque.class);
        this.xStream.addPermission(AnyTypePermission.ANY);
        this.xStream.registerConverter(new CompactQueueConverter(xStream.getMapper()));


        //this.xStream.addImplicitCollection(CollectionManager.class, "collection");
        /*this.xStream.allowTypesByWildcard(new String[]{"java.util.ArrayDeque"});*/

    }



    public void findFile() throws ExitPoint {

        if (filepath == null || filepath.isBlank()){
            console.printErr("Отсутствует пользовательский ввод для указания пути к файлу!");
            throw new ExitPoint();
        }

        File file = new File(filepath);
        StringBuilder sb = new StringBuilder();
        int b;

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file) )){
        while ((b = isr.read())!=-1){
            sb.append((char) b);
        }
        console.println(ConsoleFormat.coloring("Путь к файлу успешно получен!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC));

        if(sb.isEmpty()){
            console.printErr("Файл пустой");
            this.text = "[]";
            return;
        }
        this.text=sb.toString();
        }catch (FileNotFoundException e){
            console.printErr("Файл не найден!");
            throw new ExitPoint();
        }catch (IOException e){
            console.printErr("Ошибка ввода/вывода: "+ e);
        }
    }



    public ArrayDeque<Organization> createObject(){
        try {
            @SuppressWarnings("unchecked")
            ArrayDeque<Organization> collectionFromXml = (ArrayDeque<Organization>) xStream.fromXML(this.text);
            return collectionFromXml;
        }catch (ConversionException| StreamException  e){
            console.printErr("Невалидные поля объекта");
        }
        return new ArrayDeque<>();
    }


    public void saveObjects(ArrayDeque<Organization> collection) {
        //var input = new ConsoleInput();
        //String filePath = ScannerManager.getScannerManager().getUserScanner().nextLine();
        if (filepath == null || filepath.isBlank()) {
            console.printErr("Отсутствует пользовательский ввод для указания пути к файлу!");

        }
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filepath),StandardCharsets.UTF_8)){
            osw.write((this.xStream.toXML(collection)));
            console.println(ConsoleFormat.coloring("Путь к файлу успешно получен!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
            console.println(ConsoleFormat.coloring("Коллекция сохранена успешно!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
        }catch (FileNotFoundException e){
            console.printErr("Файл не найден!");
        }catch (IOException e){
            console.printErr("Ошибка ввода/вывода");
        }
    }
}
