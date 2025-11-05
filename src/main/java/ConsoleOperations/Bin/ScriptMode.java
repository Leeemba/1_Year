package ConsoleOperations.Bin;

import ConsoleOperations.Bin.UserInput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ScriptMode implements UserInput {

    private static final List<String> paths =new ArrayList<>();
    private static final List<BufferedReader> readers= new ArrayList<>();

    public File getFilePath(){
        return new File(paths.get(0));
    }
    public void addScript(String path) throws FileNotFoundException{
        paths.add(new File(path).getAbsolutePath());
        readers.add(new BufferedReader(new InputStreamReader(new FileInputStream(path))));
    }

    public String readLine()throws IOException {
        return readers.get(0).readLine();
    }

    public void remove()throws IOException{
        readers.get(0).close();
        readers.remove(0);
        paths.remove(0);
    }

    public  boolean fileRepeat(String path){
        return paths.contains(new File(path).getAbsolutePath());
    }
    @Override
    public String userInputNextLine() {
        try{
            return readLine();
        } catch (IOException e){
            return "";
        }
    }

}
