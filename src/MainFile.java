import java.io.File

public class MainFile {

    public static void main (String[] args){
    
        public printAllFileName(String path){
            File sourse = new File(source);
            for (File file : source.listFiles()){
                if (file.isDirectory()) printAllFileName(file.getPath())
                else System.out.println(file.getName);
             }
        }
    }
}
